package com.example.androidappwithjava.data.remote.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static RetrofitClient instance = null;
    private IPokemonApi pokemonApi;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(pokemonApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokemonApi = retrofit.create(IPokemonApi.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public IPokemonApi getPokemonApi() {
        return pokemonApi;
    }
}
