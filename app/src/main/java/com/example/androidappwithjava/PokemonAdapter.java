package com.example.androidappwithjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidappwithjava.model.Pokemon;

import java.util.List;
import java.util.Locale;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    public List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }


    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        public  LinearLayout containerView;
        public TextView pokemonName;

        public ImageView pokemonImg;

        PokemonViewHolder(View view) {
            super(view);
            containerView = view.findViewById(R.id.pokedex_row);
            pokemonName = view.findViewById(R.id.pokemon_name);
            pokemonImg = view.findViewById(R.id.pokemon_img);
        }
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokodex_row, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon current = pokemonList.get(position);
        holder.pokemonName.setText(current.getName());
        // A tag is an object associated with a view
        holder.containerView.setTag(current);

        Glide.with(holder.itemView)
                .load(current.getImg())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.pokemonImg);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
