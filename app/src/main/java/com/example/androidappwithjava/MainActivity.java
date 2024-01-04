package com.example.androidappwithjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.androidappwithjava.viewmodels.PokemonViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private SearchView searchView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private PokemonViewModel pokeVM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokeVM = new ViewModelProvider(this).get(PokemonViewModel.class);

        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.pokemon_search_view);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        displayPokemonList();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pokeVM.findPokemon(query);
                displaySearchResult();
                searchView.clearFocus();
                searchView.setIconified(true);
                searchView.onActionViewCollapsed();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            displayPokemonList();
            return false;
        });

    }

    public void displaySearchResult() {
        pokeVM.getPokemonResults().observe(this, pokemon -> {
            if (pokemon.isEmpty()) {
                Toast.makeText(this, "No Pokemon found", Toast.LENGTH_LONG).show();
            }
            adapter = new PokemonAdapter(pokemon);
            recyclerView.setAdapter(adapter);
        });
    }

    public void displayPokemonList() {
        pokeVM.getPokemonList().observe(this, pokemon -> {
            adapter = new PokemonAdapter(pokemon);
            recyclerView.setAdapter(adapter);
        });
    }
}