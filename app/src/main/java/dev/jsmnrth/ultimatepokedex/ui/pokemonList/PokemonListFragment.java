package dev.jsmnrth.ultimatepokedex.ui.pokemonList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.jsmnrth.ultimatepokedex.R;
import dev.jsmnrth.ultimatepokedex.adapter.PokemonAdapter;
import dev.jsmnrth.ultimatepokedex.api.PokemonAPI;
import dev.jsmnrth.ultimatepokedex.api.PokemonService;
import dev.jsmnrth.ultimatepokedex.model.dto.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class PokemonListFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<Pokemon> pokemonList = new ArrayList<>();
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pokemonlist, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_pokemon);
        progressBar = view.findViewById(R.id.progress_bar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        pokemonAdapter = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(pokemonAdapter);

        fetchPokemonData();
    }

    private void fetchPokemonData() {
        progressBar.setVisibility(View.VISIBLE);

        PokemonService pokemonService = PokemonAPI.getPokemonService();
        pokemonService.getPokemonList().enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pokemonList.clear();
                    pokemonList.addAll(response.body());
                    pokemonAdapter.notifyDataSetChanged();
                    recyclerView.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                // Handle error
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}




