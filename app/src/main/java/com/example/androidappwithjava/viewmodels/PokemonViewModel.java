package com.example.androidappwithjava.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androidappwithjava.data.PokemonRepository;
import com.example.androidappwithjava.model.Pokemon;

import java.util.List;

public class PokemonViewModel extends AndroidViewModel {

    private PokemonRepository pokemonRepository;

    private LiveData<List<Pokemon>> pokemonList;

    private MutableLiveData<List<Pokemon>> pokemonResults;

    public PokemonViewModel(Application application) {
        super(application);
        pokemonRepository = new PokemonRepository(application);
        pokemonList = pokemonRepository.loadLocalPokemonData();
        pokemonResults = pokemonRepository.getSearchResult();
    }

    public LiveData<List<Pokemon>> getPokemonList() {
        return pokemonList;
    }

    public MutableLiveData<List<Pokemon>> getPokemonResults() {
        return pokemonResults;
    }

    public void findPokemon(String name) {
        pokemonRepository.findPokemon(name);
    }
}
