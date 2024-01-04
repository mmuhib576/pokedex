package com.example.androidappwithjava.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidappwithjava.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

    private static PokemonDatabase INSTANCE;

    public static PokemonDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PokemonDatabase.class,
                    "pokemon_database"
            )
                    .build();
        }
        return INSTANCE;
    }
}
