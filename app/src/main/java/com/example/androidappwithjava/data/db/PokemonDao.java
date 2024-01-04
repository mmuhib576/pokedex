package com.example.androidappwithjava.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androidappwithjava.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Query("SELECT * from pokemons")
    LiveData<List<Pokemon>> getAllPokemon();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPokemon(List<Pokemon> pokemon);

    @Query("SELECT * from pokemons where pokemonName = :name")
    List<Pokemon> findPokemon(String name);

}
