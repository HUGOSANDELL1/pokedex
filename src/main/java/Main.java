import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        // Scanner is used to read input from the user
        Scanner scanner = new Scanner(System.in);

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
            System.out.println("12. Exit");

            // Reads a valid menu choice between 1 and 12
            int choice = InputHelper.readIntInRange(scanner, "Choice: ", 1, 12);

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
                    System.out.println("1. FIRE");
                    System.out.println("2. WATER");
                    System.out.println("3. GRASS");
                    System.out.println("4. ELECTRIC");
                    System.out.println("5. NORMAL");

                    int typeChoice = InputHelper.readIntInRange(scanner, "Type: ", 1, 5);

                    Type type = switch (typeChoice) {
                        case 1 -> Type.FIRE;
                        case 2 -> Type.WATER;
                        case 3 -> Type.GRASS;
                        case 4 -> Type.ELECTRIC;
                        default -> Type.NORMAL;
                    };

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
                        System.out.println("1. FIRE");
                        System.out.println("2. WATER");
                        System.out.println("3. GRASS");
                        System.out.println("4. ELECTRIC");
                        System.out.println("5. NORMAL");

                        int attackTypeChoice = InputHelper.readIntInRange(
                                scanner,
                                "Type: ",
                                1,
                                5
                        );

                        Type attackType = switch (attackTypeChoice) {
                            case 1 -> Type.FIRE;
                            case 2 -> Type.WATER;
                            case 3 -> Type.GRASS;
                            case 4 -> Type.ELECTRIC;
                            default -> Type.NORMAL;
                        };

                        // Create the attack and add it to the Pokemon
                        Attack attack = new Attack(
                                attackName,
                                baseDamage,
                                accuracy,
                                attackType
                        );

                        newPokemon.getAttacks().add(attack);
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
                            System.out.println("1. FIRE");
                            System.out.println("2. WATER");
                            System.out.println("3. GRASS");
                            System.out.println("4. ELECTRIC");
                            System.out.println("5. NORMAL");

                            int newAttackTypeChoice = InputHelper.readIntInRange(
                                    scanner,
                                    "Type: ",
                                    1,
                                    5
                            );

                            Type newAttackType = switch (newAttackTypeChoice) {
                                case 1 -> Type.FIRE;
                                case 2 -> Type.WATER;
                                case 3 -> Type.GRASS;
                                case 4 -> Type.ELECTRIC;
                                default -> Type.NORMAL;
                            };

                            Attack newAttack = new Attack(
                                    newAttackName,
                                    newBaseDamage,
                                    newAccuracy,
                                    newAttackType
                            );

                            pokemonToEdit.getAttacks().add(newAttack);

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
                    System.out.println("1. FIRE");
                    System.out.println("2. WATER");
                    System.out.println("3. GRASS");
                    System.out.println("4. ELECTRIC");
                    System.out.println("5. NORMAL");

                    int newTypeChoice = InputHelper.readIntInRange(scanner, "Type: ", 1, 5);

                    Type newType = switch (newTypeChoice) {
                        case 1 -> Type.FIRE;
                        case 2 -> Type.WATER;
                        case 3 -> Type.GRASS;
                        case 4 -> Type.ELECTRIC;
                        default -> Type.NORMAL;
                    };

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
                        Pokemon pokemon = findPokemonByName(pokemons, searchName);
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

                    System.out.println("1. FIRE");
                    System.out.println("2. WATER");
                    System.out.println("3. GRASS");
                    System.out.println("4. ELECTRIC");
                    System.out.println("5. NORMAL");

                    int filterTypeChoice = InputHelper.readIntInRange(
                            scanner,
                            "Type: ",
                            1,
                            5
                    );

                    Type filterType = switch (filterTypeChoice) {
                        case 1 -> Type.FIRE;
                        case 2 -> Type.WATER;
                        case 3 -> Type.GRASS;
                        case 4 -> Type.ELECTRIC;
                        default -> Type.NORMAL;
                    };

                    boolean foundType = false;

                    for (Pokemon pokemon : pokemons) {
                        if (pokemon.getType() == filterType) {
                            System.out.println(
                                    pokemon.getName() +
                                            " - Type: " + pokemon.getType() +
                                            " - HP: " + pokemon.getCurrentHp() + "/" + pokemon.getMaxHp()
                            );

                            foundType = true;
                        }
                    }

                    if (!foundType) {
                        System.out.println("No Pokemon found with that type.");
                    }

                    break;

                case 10:
                    System.out.println("Sort Pokemon by name");

                    pokemons.sort((p1, p2) ->
                            p1.getName().compareToIgnoreCase(p2.getName())
                    );

                    System.out.println("Pokemon sorted by name.");
                    break;

                case 11:
                    System.out.println("Pokemon Statistics");

                    if (pokemons.isEmpty()) {
                        System.out.println("No Pokemon available.");
                        break;
                    }

                    int totalPokemon = pokemons.size();
                    int totalHp = 0;

                    Pokemon highestHpPokemon = pokemons.getFirst();

                    for (Pokemon pokemon : pokemons) {
                        totalHp += pokemon.getMaxHp();

                        if (pokemon.getMaxHp() > highestHpPokemon.getMaxHp()) {
                            highestHpPokemon = pokemon;
                        }
                    }

                    double averageHp = (double) totalHp / totalPokemon;

                    System.out.println("Total Pokemon: " + totalPokemon);
                    System.out.println("Average HP: " + averageHp);
                    System.out.println(
                            "Highest HP: " +
                                    highestHpPokemon.getName() +
                                    " (" + highestHpPokemon.getMaxHp() + ")"
                    );

                    break;

                    // Stop the while loop and exit the program
                case 12:
                    System.out.println("Exit");
                    PokemonFileManager.savePokemon(pokemons);
                    running = false;
                    System.out.println("Goodbye!");
                    break;
            }
        }
        scanner.close();
    }

    public static Pokemon findPokemonByName(ArrayList<Pokemon> pokemons, String name) {

        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().equalsIgnoreCase(name)) {
                return pokemon;
            }
        }

        throw new PokemonNotFoundException("Pokemon not found.");
    }

    // Validates Pokemon data loaded from the JSON file
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
            throw new InvalidPokemonException("Pokemon must have between 1 and 4 attacks.");
        }
    }
}
