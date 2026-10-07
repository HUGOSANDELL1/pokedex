package service;

import model.Attack;
import model.BattleStats;
import model.Pokemon;
import model.Type;
import model.DamageAttack;
import model.DoubleStrikeAttack;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class BattleEngine {

    public static void startBattle(ArrayList<Pokemon> pokemons, Scanner scanner, BattleStats battleStats) {

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

        battleStats.recordPokemonUse(
                playerPokemon.getName()
        );

        System.out.println(
                "You chose " + playerPokemon.getName() + "!"
        );

        ArrayList<Pokemon> wildPokemons =
                createWildPokemonPool();

        Random random = new Random();

        boolean playerStarts = random.nextBoolean();

        Pokemon wildPokemon =
                wildPokemons.get(
                        random.nextInt(wildPokemons.size())
                );

        System.out.println(
                "A wild " + wildPokemon.getName() + " appeared!"
        );

        if (playerStarts) {
            System.out.println(
                    playerPokemon.getName() + " will attack first!"
            );
        } else {
            System.out.println(
                    wildPokemon.getName() + " will attack first!"
            );
        }

        System.out.println("Battle started!");

        while (playerPokemon.getCurrentHp() > 0 &&
                wildPokemon.getCurrentHp() > 0) {

            if (playerStarts) {

                boolean wildDefeated = playerTurn(
                        playerPokemon,
                        wildPokemon,
                        scanner,
                        random,
                        battleStats
                );

                if (wildDefeated) {
                    return;
                }

                boolean playerDefeated = cpuTurn(
                        playerPokemon,
                        wildPokemon,
                        random,
                        battleStats
                );

                if (playerDefeated) {
                    return;
                }

            } else {

                boolean playerDefeated = cpuTurn(
                        playerPokemon,
                        wildPokemon,
                        random,
                        battleStats
                );

                if (playerDefeated) {
                    return;
                }

                boolean wildDefeated = playerTurn(
                        playerPokemon,
                        wildPokemon,
                        scanner,
                        random,
                        battleStats
                );

                if (wildDefeated) {
                    return;
                }
            }
        }
    }

    private static boolean playerTurn(
            Pokemon playerPokemon,
            Pokemon wildPokemon,
            Scanner scanner,
            Random random,
            BattleStats battleStats)
    {

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

        battleStats.recordAttackUse(
                playerAttack.getName()
        );

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

            printTypeEffectiveness(
                    playerAttack,
                    wildPokemon
            );

            int damage = calculateDamage(
                    playerAttack,
                    wildPokemon,
                    random
            );

            System.out.println(
                    wildPokemon.getName() +
                            " took " +
                            damage +
                            " damage."
            );

            playerAttack.execute(
                    playerPokemon,
                    wildPokemon,
                    damage
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

            System.out.println("You won the battle!");

            battleStats.addWin();

            battleStats.addPokemonWin(
                    playerPokemon.getName()
            );

            return true;
        }

        return false;
    }

    private static boolean cpuTurn(
            Pokemon playerPokemon,
            Pokemon wildPokemon,
            Random random,
            BattleStats battleStats
    ) {

        Attack cpuAttack =
                wildPokemon.getAttacks().get(
                        random.nextInt(
                                wildPokemon.getAttacks().size()
                        )
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

            printTypeEffectiveness(
                    cpuAttack,
                    playerPokemon
            );

            int cpuDamage = calculateDamage(
                    cpuAttack,
                    playerPokemon,
                    random
            );

            System.out.println(
                    playerPokemon.getName() +
                            " took " +
                            cpuDamage +
                            " damage."
            );

            cpuAttack.execute(
                    wildPokemon,
                    playerPokemon,
                    cpuDamage
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

            System.out.println("You lost the battle.");

            battleStats.addLoss();
            battleStats.addPokemonLoss(
                    playerPokemon.getName()
            );
            return true;
        }

        return false;
    }

    private static ArrayList<Pokemon> createWildPokemonPool() {

        ArrayList<Pokemon> wildPokemons = new ArrayList<>();

        Pokemon wildBushRat = new Pokemon(
                "Bush Rat",
                Type.FIRE,
                90
        );

        wildBushRat.addAttack(
                new DoubleStrikeAttack(
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
                new DamageAttack(
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
                new DamageAttack(
                        "Whip vine",
                        35,
                        91,
                        Type.GRASS
                )
        );

        wildPokemons.add(wildRausabulb);

        return wildPokemons;
    }

    private static int calculateDamage(
            Attack attack,
            Pokemon defender,
            Random random
    ) {

        double effectiveness =
                getTypeEffectiveness(attack, defender);

        double randomFactor =
                0.85 + (random.nextDouble() * 0.15);

        boolean criticalHit =
                random.nextInt(16) == 0;

        double criticalMultiplier = 1.0;

        if (criticalHit) {
            criticalMultiplier = 2.0;
            System.out.println("Critical hit!");
        }

        return (int) (
                attack.getBaseDamage() *
                        effectiveness *
                        randomFactor *
                        criticalMultiplier
        );
    }

    private static double getTypeEffectiveness(
            Attack attack,
            Pokemon defender
    ) {

        if (attack.getType() == Type.FIRE &&
                defender.getType() == Type.GRASS) {
            return 2.0;

        } else if (attack.getType() == Type.WATER &&
                defender.getType() == Type.FIRE) {
            return 2.0;

        } else if (attack.getType() == Type.GRASS &&
                defender.getType() == Type.WATER) {
            return 2.0;

        } else if (attack.getType() == Type.ELECTRIC &&
                defender.getType() == Type.WATER) {
            return 2.0;

        } else if (attack.getType() == Type.FIRE &&
                defender.getType() == Type.WATER) {
            return 0.5;

        } else if (attack.getType() == Type.WATER &&
                defender.getType() == Type.GRASS) {
            return 0.5;

        } else if (attack.getType() == Type.GRASS &&
                defender.getType() == Type.FIRE) {
            return 0.5;
        }

        return 1.0;
    }

    private static void printTypeEffectiveness(
            Attack attack,
            Pokemon defender
    ) {

        double effectiveness =
                getTypeEffectiveness(attack, defender);

        if (effectiveness == 2.0) {
            System.out.println("It's super effective!");
        } else if (effectiveness == 0.5) {
            System.out.println("It's not very effective.");
        }
    }
}