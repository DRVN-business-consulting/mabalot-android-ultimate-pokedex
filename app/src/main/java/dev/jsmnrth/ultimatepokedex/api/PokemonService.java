package dev.jsmnrth.ultimatepokedex.api;

import dev.jsmnrth.ultimatepokedex.model.dto.Pokemon;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PokemonService {
    @GET("pokemon")
    Call<List<Pokemon>> getPokemonList();
}