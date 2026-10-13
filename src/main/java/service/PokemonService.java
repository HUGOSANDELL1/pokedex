package service;

import model.Pokemon;
import java.util.Optional;
import java.util.ArrayList;

public class PokemonService {

    public static Optional<Pokemon> findPokemonByName(
            ArrayList<Pokemon> pokemons,
            String name
    ) {
                return pokemons.stream()
                        .filter(pokemon ->
                                pokemon.getName().equalsIgnoreCase(name)
                        )
                        .findFirst();
    }

    public static void validatePokemon(Pokemon pokemon) {

        if (pokemon.getName() == null || pokemon.getName().isBlank()) {
            throw new InvalidPokemonException("Pokemon name is invalid.");
        }

        if (pokemon.getMaxHp() <= 0) {
            throw new InvalidPokemonException("Pokemon HP is invalid.");
        }

        if (pokemon.getType() == null) {
            throw new InvalidPokemonException("Pokemon type is invalid.");
        }

        if (pokemon.getAttacks() == null
                || pokemon.getAttacks().isEmpty()
                || pokemon.getAttacks().size() > 4) {

            throw new InvalidPokemonException(
                    "Pokemon must have between 1 and 4 attacks."
            );
        }
    }
}
