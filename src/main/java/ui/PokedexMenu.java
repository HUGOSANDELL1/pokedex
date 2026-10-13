package ui;

import model.Attack;
import model.BattleStats;
import model.Pokemon;
import model.Type;
import repository.BattleStatsFileManager;
import repository.PokemonFileManager;
import service.*;
import model.DamageAttack;
import service.PokemonNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PokedexMenu {

    public static void start(ArrayList<Pokemon> pokemons) {
       // Scanner is used to read input from the user
        Scanner scanner = new Scanner(System.in);
        BattleStats battleStats = BattleStatsFileManager.loadBattleStats();

        boolean running = true;
        while (running) {

            // Main menu
            System.out.println();
            System.out.println("===Pokedex===");
            System.out.println("1. View all Pokemon");
            System.out.println("2. Add Pokemon");
            System.out.println("3. Edit Pokemon");
            System.out.println("4. Remove Pokemon");
            System.out.println("5. Save");
            System.out.println("6. Load");
            System.out.println("7. Reset");
            System.out.println("8. Search Pokemon");
            System.out.println("9. Filter by type");
            System.out.println("10. Sort by name");
            System.out.println("11. Statistics");
            System.out.println("12. Start Battle");
            System.out.println("13. Battle Results");
            System.out.println("14. Exit");

            // Reads a valid menu choice between 1 and 12
            int choice = InputHelper.readIntInRange(scanner, "Choice: ", 1, 14);

            switch (choice) {

                // Show all Pokemon currently stored in the Pokedex
                case 1:
                    System.out.println("View all Pokemon");

                    for (int i = 0; i < pokemons.size(); i++) {
                        Pokemon pokemon = pokemons.get(i);

                        System.out.println(
                                (i + 1) + ". " + pokemon.getName() +
                                        " - Type: " + pokemon.getType() +
                                        " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                        );

                        for (Attack attack : pokemon.getAttacks()) {
                            System.out.println(
                                    "   - " + attack.getName() +
                                            " | Damage: " + attack.getBaseDamage() +
                                            " | Accuracy: " + attack.getAccuracy() +
                                            " | Type: " + attack.getType()
                            );
                        }
                    }
                    break;

                // Add new Pokemon
                case 2:
                    System.out.println("Add Pokemon");
                    String name = InputHelper.readValidName(scanner, "Pokemon name: ");

                    int hp = InputHelper.readIntInRange(scanner, "HP: ", 1, 1000);

                    // Let the user choose the Pokemon type
                    System.out.println("Choose Pokemon type:");
                    Type type =
                            InputHelper.readType(scanner, "Type: ");

                    // Create a new Pokemon object and add it to the Pokedex
                    Pokemon newPokemon = new Pokemon(name, type, hp);

                    int numberOfAttacks = InputHelper.readIntInRange(
                            scanner,
                            "How many attacks? (1-4): ",
                            1,
                            4
                    );

                    for (int i = 0; i < numberOfAttacks; i++) {
                        String attackName = InputHelper.readValidName(
                                scanner,
                                "Attack " + (i + 1) + " name: "
                        );

                        int baseDamage = InputHelper.readIntInRange(
                                scanner,
                                "Base damage: ",
                                1,
                                1000
                        );

                        int accuracy = InputHelper.readIntInRange(
                                scanner,
                                "Accuracy (1-100): ",
                                1,
                                100
                        );

                        System.out.println("Choose attack type:");
                        Type attackType =
                                InputHelper.readType(scanner, "Type: ");
                        Attack attack = new DamageAttack(
                                attackName,
                                baseDamage,
                                accuracy,
                                attackType
                        );

                        newPokemon.addAttack(attack);
                    }

                    pokemons.add(newPokemon);
                    System.out.println(name + " added!");
                    break;
                case 3:
                    System.out.println("Edit Pokemon");

                    // Prevents trying to edit when there are no Pokemon in the list
                    if (pokemons.isEmpty()) {
                        System.out.println("No Pokemon to edit.");
                        break;
                    }

                    for (int i = 0; i < pokemons.size(); i++) {
                        Pokemon pokemon = pokemons.get(i);
                        System.out.println(
                                (i + 1) + ". " + pokemon.getName() +
                                        " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                        );
                    }

                    int editChoice = InputHelper.readIntInRange(
                            scanner,
                            "Choose Pokemon to edit: ",
                            1,
                            pokemons.size()
                    );

                    // Get the Pokemon the user selected
                    Pokemon pokemonToEdit = pokemons.get(editChoice - 1);

                    System.out.println("Current attacks:");

                    for (int i = 0; i < pokemonToEdit.getAttacks().size(); i++) {
                        Attack attack = pokemonToEdit.getAttacks().get(i);

                        System.out.println(
                                (i + 1) + ". " + attack.getName() +
                                        " | Damage: " + attack.getBaseDamage() +
                                        " | Accuracy: " + attack.getAccuracy() +
                                        " | Type: " + attack.getType()
                        );
                    }

                    System.out.println("1. Add attack");
                    System.out.println("2. Remove attack");
                    System.out.println("3. Keep attacks");

                    int attackEditChoice = InputHelper.readIntInRange(
                            scanner,
                            "Choice: ",
                            1,
                            3
                    );

                    if (attackEditChoice == 1) {

                        if (pokemonToEdit.getAttacks().size() >= 4) {
                            System.out.println("This Pokemon already has 4 attacks.");
                        } else {
                            String newAttackName = InputHelper.readValidName(
                                    scanner,
                                    "Attack name: "
                            );

                            int newBaseDamage = InputHelper.readIntInRange(
                                    scanner,
                                    "Base damage: ",
                                    1,
                                    1000
                            );

                            int newAccuracy = InputHelper.readIntInRange(
                                    scanner,
                                    "Accuracy (1-100): ",
                                    1,
                                    100
                            );

                            System.out.println("Choose attack type:");
                            Type newAttackType =
                                    InputHelper.readType(scanner, "Type: ");

                            Attack newAttack = new DamageAttack(
                                    newAttackName,
                                    newBaseDamage,
                                    newAccuracy,
                                    newAttackType
                            );

                            pokemonToEdit.addAttack(newAttack);

                            System.out.println(newAttackName + " added!");
                        }
                    }
                    if (attackEditChoice == 2) {

                        if (pokemonToEdit.getAttacks().size() <= 1) {
                            System.out.println("A Pokemon must have at least 1 attack.");
                        } else {
                            int removeAttackChoice = InputHelper.readIntInRange(
                                    scanner,
                                    "Choose attack to remove: ",
                                    1,
                                    pokemonToEdit.getAttacks().size()
                            );

                            Attack removedAttack =
                                    pokemonToEdit.getAttacks().remove(removeAttackChoice - 1);

                            System.out.println(removedAttack.getName() + " removed!");
                        }
                    }

                    String newName = InputHelper.readValidName(scanner, "New Pokemon name: ");

                    int newHp = InputHelper.readIntInRange(scanner, "New HP: ", 1, 1000);

                    // Let the user choose the new Pokemon type
                    System.out.println("Choose Pokemon type:");
                    Type newType =
                            InputHelper.readType(scanner, "Type: ");


                    pokemonToEdit.setName(newName);
                    pokemonToEdit.setMaxHp(newHp);
                    pokemonToEdit.setCurrentHp(newHp);
                    pokemonToEdit.setType(newType);
                    System.out.println(newName + " updated!");
                    break;

                // Remove a Pokemon
                case 4:
                    System.out.println("Remove Pokemon");

                    // Prevents trying to remove when there are no Pokemon in the list
                    if (pokemons.isEmpty()) {
                        System.out.println("No Pokemon to remove.");
                        break;
                    }

                    // Show the Pokemon before the user chooses one
                    for (int i = 0; i < pokemons.size(); i++) {
                        Pokemon pokemon = pokemons.get(i);

                        System.out.println(
                                (i + 1) + ". " + pokemon.getName() +
                                        " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                        );
                    }

                    int removeChoice = InputHelper.readIntInRange(
                            scanner,
                            "Choose Pokemon to remove: ",
                            1,
                            pokemons.size()
                    );

                    Pokemon removedPokemon = pokemons.remove(removeChoice - 1);

                    System.out.println(removedPokemon.getName() + " was removed from Pokedex!");
                    break;
                case 5:
                    System.out.println("Save");
                    PokemonFileManager.savePokemon(pokemons);
                    break;
                case 6:
                    System.out.println("Load");
                    PokemonFileManager.loadPokemon(pokemons);
                    break;
                case 7:
                    System.out.println("Reset");
                    System.out.println("Are you sure you want to reset the Pokedex?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");

                    int resetChoice = InputHelper.readIntInRange(
                            scanner,
                            "Choice: ",
                            1,
                            2
                    );

                    if (resetChoice == 1) {
                        pokemons.clear();
                        PokemonSeeder.addSeedPokemon(pokemons);
                        System.out.println("Pokedex reset to seed Pokemon.");
                    } else {
                        System.out.println("Reset cancelled.");
                    }

                    break;

                case 8:
                    System.out.println("Search Pokemon");

                    String searchName = InputHelper.readValidName(
                            scanner,
                            "Enter Pokemon name: "
                    );


                    try {
                        Pokemon pokemon =
                                PokemonService.findPokemonByName(
                                        pokemons,
                                        searchName)
                                        .orElseThrow(
                                        () -> new PokemonNotFoundException("Pokemon not found.")
                                );
                        System.out.println(
                                pokemon.getName() +
                                        " - Type: " + pokemon.getType() +
                                        " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                        );

                        for (Attack attack : pokemon.getAttacks()) {
                            System.out.println(
                                    "   - " + attack.getName() +
                                            " | Damage: " + attack.getBaseDamage() +
                                            " | Accuracy: " + attack.getAccuracy() +
                                            " | Type: " + attack.getType()
                            );
                        }
                    } catch (PokemonNotFoundException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 9:
                    System.out.println("Filter Pokemon by type");

                    Type filterType =
                            InputHelper.readType(scanner, "Type: ");

                    ArrayList<Pokemon> filteredPokemon =
                            PokedexService.filterByType(pokemons, filterType);

                    if (filteredPokemon.isEmpty()) {
                        System.out.println("No Pokemon found with that type.");
                    } else {
                        for (Pokemon pokemon : filteredPokemon) {
                            System.out.println(
                                    pokemon.getName() +
                                            " - Type: " + pokemon.getType() +
                                            " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                            );
                        }
                    }

                    break;

                case 10:
                    System.out.println("Sort Pokemon by name");

                    PokedexService.sortByName(pokemons);

                    System.out.println("Pokemon sorted by name.");
                    break;

                case 11:
                    System.out.println("Pokemon Statistics");

                    if (pokemons.isEmpty()) {
                        System.out.println("No Pokemon available.");
                        break;
                    }

                    double averageHp =
                            PokedexService.getAverageHp(pokemons);

                    Pokemon highestHpPokemon =
                            PokedexService.getHighestHpPokemon(pokemons);


                    System.out.println("Total Pokemon: " + pokemons.size());
                    System.out.println("Average HP: " + averageHp);

                    if (highestHpPokemon != null) {
                        System.out.println(
                                "Highest HP: " +
                                        highestHpPokemon.getName() +
                                        " (" + highestHpPokemon.getMaxHp() + ")"
                        );
                    }

                    break;

                case 12:
                    System.out.println("Start Battle");
                    BattleEngine.startBattle(pokemons, scanner, battleStats);
                    break;

                    case 13:
                    System.out.println("Battle Results");
                    System.out.println("Wins: " + battleStats.getTotalWins());
                    System.out.println("Losses: " + battleStats.getTotalLosses());
                    System.out.println("Most used Pokemon: " + battleStats.getMostUsedPokemon());
                    System.out.println("Most used attack: " + battleStats.getMostUsedAttack());

                        System.out.println("Pokemon Results:");

                        for (Pokemon pokemon : pokemons) {
                            System.out.println(
                                    pokemon.getName() +
                                            " - Wins: " +
                                            battleStats.getPokemonWins(pokemon.getName()) +
                                            ", Losses: " +
                                            battleStats.getPokemonLosses(pokemon.getName())+
                                            ", Win ratio: " +
                                            String.format(
                                                    "%.1f%%",
                                                    battleStats.getPokemonWinRatio(pokemon.getName())
                                            )
                            );
                        }
                        break;

                // Stop the while loop and exit the program
                case 14:
                    System.out.println("Exit");
                    PokemonFileManager.savePokemon(pokemons);

                    BattleStatsFileManager.saveBattleStats(battleStats);
                    running = false;
                    System.out.println("Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
}

