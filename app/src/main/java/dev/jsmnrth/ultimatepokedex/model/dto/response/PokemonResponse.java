package dev.jsmnrth.ultimatepokedex.model.dto.response;

import java.util.List;

import dev.jsmnrth.ultimatepokedex.model.dto.Pokemon;

public class PokemonResponse {
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }

    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}