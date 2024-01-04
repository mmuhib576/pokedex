package com.example.androidappwithjava.data;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidappwithjava.R;
import com.example.androidappwithjava.data.db.PokemonDao;
import com.example.androidappwithjava.data.db.PokemonDatabase;
import com.example.androidappwithjava.data.remote.api.RetrofitClient;
import com.example.androidappwithjava.model.Pokedex;
import com.example.androidappwithjava.model.Pokemon;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonRepository {

    private PokemonDao pokemonDao;
    private PokemonDatabase pokemonDatabase;

    private List<Pokemon> pokemons;

    private MutableLiveData<List<Pokemon>> searchResult = new MutableLiveData<>();

    public MutableLiveData<List<Pokemon>> getSearchResult() {
        return searchResult;
    }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            searchResult.setValue(pokemons);
        }
    };


    public PokemonRepository(Application application) {
        pokemonDatabase = PokemonDatabase.getDatabase(application);
        pokemonDao = pokemonDatabase.pokemonDao();
        retrievePokemonData(application);
    }

    public boolean isConnectivity(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private void loadRemotePokemon(Context context) {
        Call<Pokedex> call = RetrofitClient.getInstance().getPokemonApi()
                .getAllPokemon();

        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                if (response.isSuccessful()) {
                    pokemons = response.body().getPokemon();

                    // insert the data into the database
                    insertPokemon(pokemons);
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                Toast.makeText(context, "Error " + t, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void retrievePokemonData(Context context) {
        if (isConnectivity(context)) {
            loadRemotePokemon(context);
        } else {
            Toast.makeText(context, R.string.error_network, Toast.LENGTH_LONG).show();
        }
    }

    public LiveData<List<Pokemon>> loadLocalPokemonData() {
        return pokemonDao.getAllPokemon();
    }

    public void insertPokemon(List<Pokemon> pokemonList) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> pokemonDao.insertPokemon(pokemonList));
        executor.shutdown();
    }

    public void findPokemon(String name) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            pokemons = pokemonDao.findPokemon(name);
        }); handler.sendEmptyMessage(0);
        executor.shutdown();
    }
}
