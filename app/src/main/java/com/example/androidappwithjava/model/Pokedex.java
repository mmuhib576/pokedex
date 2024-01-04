package com.example.androidappwithjava.model;

import java.util.List;

public class Pokedex {
    private List<Pokemon> pokemon;

    public List<Pokemon> getPokemon() {
        return this.pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}

