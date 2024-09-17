package dev.jsmnrth.ultimatepokedex.api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonAPI {
    private static final String BASE_URL = "https://pokemon-api-nssw.onrender.com/";

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static PokemonService getPokemonService() {
        return getClient().create(PokemonService.class);
    }
}