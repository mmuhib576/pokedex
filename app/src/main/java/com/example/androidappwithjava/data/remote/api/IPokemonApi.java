package com.example.androidappwithjava.data.remote.api;

import com.example.androidappwithjava.model.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPokemonApi {
    String BASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/";

    @GET("pokedex.json")
    Call<Pokedex> getAllPokemon();
}
