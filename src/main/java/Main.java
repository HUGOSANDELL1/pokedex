import model.Pokemon;
import repository.PokemonFileManager;
import service.PokemonSeeder;
import ui.PokedexMenu;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        // Stores all Pokemon objects in the Pokedex
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        Path savePath = Path.of("pokemon.json");

        if (Files.exists(savePath)) {
            PokemonFileManager.loadPokemon(pokemons);

            if (pokemons.isEmpty()) {
                PokemonSeeder.addSeedPokemon(pokemons);
            }
        } else {
            PokemonSeeder.addSeedPokemon(pokemons);
        }

        System.out.println("Pokedex Started");

        PokedexMenu.start(pokemons);
    }
}