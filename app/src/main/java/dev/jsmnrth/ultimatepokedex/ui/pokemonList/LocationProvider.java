package dev.jsmnrth.ultimatepokedex.ui.pokemonList;

import java.util.List;
import java.util.Random;

public class LocationProvider {
    private static final List<Location> LOCATIONS = List.of(
            new Location("Manila", 14.5995, 120.9842),
            new Location("Quezon City", 14.6760, 121.0437),
            new Location("Makati", 14.5547, 121.0244),
            new Location("Pasig", 14.5807, 121.0690),
            new Location("Taguig", 14.5549, 121.0402)
    );

    public static Location getRandomLocation() {
        Random random = new Random();
        return LOCATIONS.get(random.nextInt(LOCATIONS.size()));
    }
}
