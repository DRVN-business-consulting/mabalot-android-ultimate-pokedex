package dev.jsmnrth.ultimatepokedex.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.jsmnrth.ultimatepokedex.R;
import dev.jsmnrth.ultimatepokedex.model.dto.Pokemon;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);
        holder.bind(pokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_pokemon);
            textView = itemView.findViewById(R.id.text_view_pokemon_name);
        }

        public void bind(Pokemon pokemon) {
            textView.setText(pokemon.getName().getEnglish());
            String imageUrl = pokemon.getImage().getSprite();
            Glide.with(itemView.getContext())
                    .load("https://pokemon-api-nssw.onrender.com" + imageUrl)
                    .into(imageView);
        }
    }
}