import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleEngine {

    public static void startBattle(ArrayList<Pokemon> pokemons, Scanner scanner) {

        if (pokemons.isEmpty()) {
            System.out.println("You do not own any Pokemon.");
            return;
        }

        System.out.println("Choose your Pokemon:");

        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);

            System.out.println(
                    (i + 1) + ". " +
                            pokemon.getName() +
                            " - HP: " +
                            pokemon.getCurrentHp() +
                            "/" +
                            pokemon.getMaxHp()
            );
        }

        int choice = InputHelper.readIntInRange(
                scanner,
                "Pokemon: ",
                1,
                pokemons.size()
        );

        Pokemon playerPokemon = pokemons.get(choice - 1);

        if (playerPokemon.getAttacks().isEmpty()) {
            System.out.println("This Pokemon has no attacks.");
            return;
        }

        if (playerPokemon.getCurrentHp() <= 0) {
            System.out.println("This Pokemon has no HP left.");
            return;
        }

        System.out.println(
                "You chose " + playerPokemon.getName() + "!"
        );

        Random random = new Random();

        Pokemon wildPokemon =
                pokemons.get(random.nextInt(pokemons.size()));

        System.out.println(
                "A wild " + wildPokemon.getName() + " appeared!"
        );

        System.out.println("Battle started!");
    }
}
