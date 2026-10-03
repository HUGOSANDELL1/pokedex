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

        ArrayList<Pokemon> wildPokemons =
                createWildPokemonPool();

        Random random = new Random();

        Pokemon wildPokemon =
                wildPokemons.get(random.nextInt(wildPokemons.size())
                );


        System.out.println(
                "A wild " + wildPokemon.getName() + " appeared!"
        );

        System.out.println("Choose attack:");

        for (int i = 0; i < playerPokemon.getAttacks().size(); i++) {
            Attack attack = playerPokemon.getAttacks().get(i);

            System.out.println(
                    (i + 1) + ". " +
                            attack.getName() +
                            " - Damage: " + attack.getBaseDamage() +
                            " - Accuracy: " + attack.getAccuracy() +
                            " - Type: " + attack.getType()
            );
        }

        int attackChoice = InputHelper.readIntInRange(
                scanner,
                "Attack: ",
                1,
                playerPokemon.getAttacks().size()
        );

        Attack playerAttack =
                playerPokemon.getAttacks().get(attackChoice - 1);

        System.out.println(
                "You chose " + playerAttack.getName() + "!"
        );

        int hitRoll = random.nextInt(100) + 1;

        if (hitRoll <= playerAttack.getAccuracy()) {
            System.out.println(
                    playerPokemon.getName() +
                            "'s " +
                            playerAttack.getName() +
                            " hit!"
            );


            int damage = calculateDamage(playerAttack);

            wildPokemon.setCurrentHp(
                    Math.max(
                            0,
                            wildPokemon.getCurrentHp() - damage
                    )
            );

            System.out.println(
                    wildPokemon.getName() +
                            " has " +
                            wildPokemon.getCurrentHp() +
                            "/" +
                            wildPokemon.getMaxHp() +
                            " HP left."
            );

        } else {
            System.out.println(
                    playerPokemon.getName() +
                            "'s " +
                            playerAttack.getName() +
                            " missed!"
            );
        }

        if (wildPokemon.getCurrentHp() <= 0) {
            System.out.println(
                    wildPokemon.getName() + " was defeated!"
            );
            return;
        }

        Attack cpuAttack =
                wildPokemon.getAttacks().get(
                        random.nextInt(wildPokemon.getAttacks().size())
                );

        System.out.println(
                wildPokemon.getName() +
                        " chose " +
                        cpuAttack.getName() + "!"
        );

        int cpuHitRoll = random.nextInt(100) + 1;

        if (cpuHitRoll <= cpuAttack.getAccuracy()) {
            System.out.println(
                    wildPokemon.getName() +
                            "'s " +
                            cpuAttack.getName() +
                            " hit!"
            );

            int cpuDamage = calculateDamage(cpuAttack);

            playerPokemon.setCurrentHp(
                    Math.max(
                            0,
                            playerPokemon.getCurrentHp() - cpuDamage
                    )
            );

            System.out.println(
                    playerPokemon.getName() +
                            " has " +
                            playerPokemon.getCurrentHp() +
                            "/" +
                            playerPokemon.getMaxHp() +
                            " HP left."
            );

        } else {
            System.out.println(
                    wildPokemon.getName() +
                            "'s " +
                            cpuAttack.getName() +
                            " missed!"
            );
        }

        if (playerPokemon.getCurrentHp() <= 0) {
            System.out.println(
                    playerPokemon.getName() + " was defeated!"
            );
            return;
        }

        System.out.println("Battle started!");
    }

    private static ArrayList<Pokemon> createWildPokemonPool() {

        ArrayList<Pokemon> wildPokemons = new ArrayList<>();

        Pokemon wildBushRat = new Pokemon(
                "Bush Rat",
                Type.FIRE,
                90
        );

        wildBushRat.addAttack(
                new Attack(
                        "Cheese",
                        32,
                        90,
                        Type.FIRE
                )
        );

        wildPokemons.add(wildBushRat);

        Pokemon wildRoffo = new Pokemon(
                "Roffo",
                Type.WATER,
                95
        );

        wildRoffo.addAttack(
                new Attack(
                        "Splash",
                        31,
                        93,
                        Type.WATER
                )
        );

        wildPokemons.add(wildRoffo);

        Pokemon wildRausabulb = new Pokemon(
                "Rausabulb",
                Type.GRASS,
                92
        );

        wildRausabulb.addAttack(
                new Attack(
                        "Whip vine",
                        35,
                        91,
                        Type.GRASS
                )
        );

        wildPokemons.add(wildRausabulb);

        return wildPokemons;
    }
    private static int calculateDamage(Attack attack) {
        return attack.getBaseDamage();
    }
}
