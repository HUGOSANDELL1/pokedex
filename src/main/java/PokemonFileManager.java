import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PokemonFileManager {

    // Saves all Pokemon to a JSON file
    public static void savePokemon(ArrayList<Pokemon> pokemons) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            mapper.writeValue(
                    Path.of("pokemon.json").toFile(),
                    pokemons
            );

            System.out.println("Pokemon saved.");

        } catch (IOException e) {
            System.out.println("Could not save Pokemon.");
        }
    }

    // Loads Pokemon from the JSON file
    public static void loadPokemon(ArrayList<Pokemon> pokemons) {
        Path path = Path.of("pokemon.json");

        if (!Files.exists(path)) {
            System.out.println("No saved Pokemon file found.");
            return;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();

            Pokemon[] loadedPokemon = mapper.readValue(
                    path.toFile(),
                    Pokemon[].class
            );

            pokemons.clear();

            for (Pokemon pokemon : loadedPokemon) {
                PokemonService.validatePokemon(pokemon);
                pokemons.add(pokemon);
            }

            System.out.println("Pokemon loaded.");

        } catch (IOException | InvalidPokemonException e) {
            System.out.println("Could not load Pokemon. The file may be corrupted.");
        }
    }
}
