import java.util.ArrayList;

public class PokedexService {

    // Returns all Pokemon with the selected type
    public static ArrayList<Pokemon> filterByType(
            ArrayList<Pokemon> pokemons,
            Type type
    ) {
        ArrayList<Pokemon> filteredPokemon = new ArrayList<>();

        for (Pokemon pokemon : pokemons) {
            if (pokemon.getType() == type) {
                filteredPokemon.add(pokemon);
            }
        }

        return filteredPokemon;
    }

    // Sorts Pokemon alphabetically by name
    public static void sortByName(ArrayList<Pokemon> pokemons) {
        pokemons.sort(
                (p1, p2) ->
                        p1.getName().compareToIgnoreCase(p2.getName())
        );
    }

    // Calculates the average max HP
    public static double getAverageHp(ArrayList<Pokemon> pokemons) {

        if (pokemons.isEmpty()) {
            return 0;
        }

        int totalHp = 0;

        for (Pokemon pokemon : pokemons) {
            totalHp += pokemon.getMaxHp();
        }

        return (double) totalHp / pokemons.size();
    }

    // Finds the Pokemon with the highest max HP
    public static Pokemon getHighestHpPokemon(
            ArrayList<Pokemon> pokemons
    ) {
        if (pokemons.isEmpty()) {
            return null;
        }

        Pokemon highestHpPokemon = pokemons.getFirst();

        for (Pokemon pokemon : pokemons) {
            if (pokemon.getMaxHp() > highestHpPokemon.getMaxHp()) {
                highestHpPokemon = pokemon;
            }
        }

        return highestHpPokemon;
    }
}