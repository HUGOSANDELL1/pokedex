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
                addSeedPokemon(pokemons);
            }
        } else {
            addSeedPokemon(pokemons);
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
            int choice = readIntInRange(scanner, "Choice: ", 1, 12);

            switch (choice) {

                // Show all Pokemon currently stored in the Pokedex
                case 1:
                    System.out.println("View all Pokemon");

                    for (int i = 0; i < pokemons.size(); i++) {
                        Pokemon pokemon = pokemons.get(i);

                        System.out.println(
                                (i + 1) + ". " + pokemon.name +
                                        " - Type: " + pokemon.type +
                                        " - HP: " + pokemon.currentHp + "/" + pokemon.maxHp
                        );

                        for (Attack attack : pokemon.attacks) {
                            System.out.println(
                                    "   - " + attack.name +
                                            " | Damage: " + attack.baseDamage +
                                            " | Accuracy: " + attack.accuracy +
                                            " | Type: " + attack.type
                            );
                        }
                    }
                    break;

                    // Add new Pokemon
                case 2:
                    System.out.println("Add Pokemon");
                    String name = readValidName(scanner, "Pokemon name: ");

                    int hp = readIntInRange(scanner, "HP: ", 1, 1000);

                    // Let the user choose the Pokemon type
                    System.out.println("Choose Pokemon type:");
                    System.out.println("1. FIRE");
                    System.out.println("2. WATER");
                    System.out.println("3. GRASS");
                    System.out.println("4. ELECTRIC");
                    System.out.println("5. NORMAL");

                    int typeChoice = readIntInRange(scanner, "Type: ", 1, 5);

                    Type type = switch (typeChoice) {
                        case 1 -> Type.FIRE;
                        case 2 -> Type.WATER;
                        case 3 -> Type.GRASS;
                        case 4 -> Type.ELECTRIC;
                        default -> Type.NORMAL;
                    };

                    // Create a new Pokemon object and add it to the Pokedex
                    Pokemon newPokemon = new Pokemon(name, type, hp);

                    int numberOfAttacks = readIntInRange(
                            scanner,
                            "How many attacks? (1-4): ",
                            1,
                            4
                    );

                    for (int i = 0; i < numberOfAttacks; i++) {
                        String attackName = readValidName(
                                scanner,
                                "Attack " + (i + 1) + " name: "
                        );

                        int baseDamage = readIntInRange(
                                scanner,
                                "Base damage: ",
                                1,
                                1000
                        );

                        int accuracy = readIntInRange(
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

                        int attackTypeChoice = readIntInRange(
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

                        newPokemon.attacks.add(attack);
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
                                (i + 1) + ". " + pokemon.name +
                                        " - HP: " + pokemon.currentHp + "/" + pokemon.maxHp
                        );
                    }

                    int editChoice = readIntInRange(
                            scanner,
                            "Choose Pokemon to edit: ",
                            1,
                            pokemons.size()
                    );

                    // Get the Pokemon the user selected
                    Pokemon pokemonToEdit = pokemons.get(editChoice - 1);

                    System.out.println("Current attacks:");

                    for (int i = 0; i < pokemonToEdit.attacks.size(); i++) {
                        Attack attack = pokemonToEdit.attacks.get(i);

                        System.out.println(
                                (i + 1) + ". " + attack.name +
                                        " | Damage: " + attack.baseDamage +
                                        " | Accuracy: " + attack.accuracy +
                                        " | Type: " + attack.type
                        );
                    }

                    System.out.println("1. Add attack");
                    System.out.println("2. Remove attack");
                    System.out.println("3. Keep attacks");

                    int attackEditChoice = readIntInRange(
                            scanner,
                            "Choice: ",
                            1,
                            3
                    );

                    if (attackEditChoice == 1) {

                        if (pokemonToEdit.attacks.size() >= 4) {
                            System.out.println("This Pokemon already has 4 attacks.");
                        } else {
                            String newAttackName = readValidName(
                                    scanner,
                                    "Attack name: "
                            );

                            int newBaseDamage = readIntInRange(
                                    scanner,
                                    "Base damage: ",
                                    1,
                                    1000
                            );

                            int newAccuracy = readIntInRange(
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

                            int newAttackTypeChoice = readIntInRange(
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

                            pokemonToEdit.attacks.add(newAttack);

                            System.out.println(newAttackName + " added!");
                        }
                    }
                    if (attackEditChoice == 2) {

                        if (pokemonToEdit.attacks.size() <= 1) {
                            System.out.println("A Pokemon must have at least 1 attack.");
                        } else {
                            int removeAttackChoice = readIntInRange(
                                    scanner,
                                    "Choose attack to remove: ",
                                    1,
                                    pokemonToEdit.attacks.size()
                            );

                            Attack removedAttack =
                                    pokemonToEdit.attacks.remove(removeAttackChoice - 1);

                            System.out.println(removedAttack.name + " removed!");
                        }
                    }

                    String newName = readValidName(scanner, "New Pokemon name: ");

                    int newHp = readIntInRange(scanner, "New HP: ", 1, 1000);

                    // Let the user choose the new Pokemon type
                    System.out.println("Choose Pokemon type:");
                    System.out.println("1. FIRE");
                    System.out.println("2. WATER");
                    System.out.println("3. GRASS");
                    System.out.println("4. ELECTRIC");
                    System.out.println("5. NORMAL");

                    int newTypeChoice = readIntInRange(scanner, "Type: ", 1, 5);

                    Type newType = switch (newTypeChoice) {
                        case 1 -> Type.FIRE;
                        case 2 -> Type.WATER;
                        case 3 -> Type.GRASS;
                        case 4 -> Type.ELECTRIC;
                        default -> Type.NORMAL;
                    };

                    pokemonToEdit.name = newName;
                    pokemonToEdit.maxHp = newHp;
                    pokemonToEdit.currentHp = newHp;
                    pokemonToEdit.type = newType;
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
                                (i + 1) + ". " + pokemon.name +
                                        " - HP: " + pokemon.currentHp + "/" + pokemon.maxHp
                        );
                    }

                    int removeChoice = readIntInRange(
                            scanner,
                            "Choose Pokemon to remove: ",
                            1,
                            pokemons.size()
                    );

                    Pokemon removedPokemon = pokemons.remove(removeChoice - 1);

                    System.out.println(removedPokemon.name + " was removed from Pokedex!");
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

                    int resetChoice = readIntInRange(
                            scanner,
                            "Choice: ",
                            1,
                            2
                    );

                    if (resetChoice == 1) {
                        pokemons.clear();
                        addSeedPokemon(pokemons);
                        System.out.println("Pokedex reset to seed Pokemon.");
                    } else {
                        System.out.println("Reset cancelled.");
                    }

                    break;

                case 8:
                    System.out.println("Search Pokemon");

                    String searchName = readValidName(
                            scanner,
                            "Enter Pokemon name: "
                    );


                    try {
                        Pokemon pokemon = findPokemonByName(pokemons, searchName);
                            System.out.println(
                                    pokemon.name +
                                            " - Type: " + pokemon.type +
                                            " - HP: " + pokemon.currentHp + "/" + pokemon.maxHp
                            );

                            for (Attack attack : pokemon.attacks) {
                                System.out.println(
                                        "   - " + attack.name +
                                                " | Damage: " + attack.baseDamage +
                                                " | Accuracy: " + attack.accuracy +
                                                " | Type: " + attack.type
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

                    int filterTypeChoice = readIntInRange(
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
                        if (pokemon.type == filterType) {
                            System.out.println(
                                    pokemon.name +
                                            " - Type: " + pokemon.type +
                                            " - HP: " + pokemon.currentHp + "/" + pokemon.maxHp
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
                            p1.name.compareToIgnoreCase(p2.name)
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
                        totalHp += pokemon.maxHp;

                        if (pokemon.maxHp > highestHpPokemon.maxHp) {
                            highestHpPokemon = pokemon;
                        }
                    }

                    double averageHp = (double) totalHp / totalPokemon;

                    System.out.println("Total Pokemon: " + totalPokemon);
                    System.out.println("Average HP: " + averageHp);
                    System.out.println(
                            "Highest HP: " +
                                    highestHpPokemon.name +
                                    " (" + highestHpPokemon.maxHp + ")"
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

    // Reads an integer and keeps asking until the value is inside the allowed range
    public static int readIntInRange(Scanner scanner, String message, int min, int max) {

        while (true) {
            System.out.print(message);

            // Read input as text first so invalid input does not crash the program
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    public static String readValidName(Scanner scanner, String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }

            if (input.length() > 50) {
                System.out.println("Name is too long. Maximum 50 characters.");
                continue;
            }

            if (input.contains(";")) {
                System.out.println("Name cannot contain ;");
                continue;
            }

            return input;
        }
    }

    public static Pokemon findPokemonByName(ArrayList<Pokemon> pokemons, String name) {

        for (Pokemon pokemon : pokemons) {
            if (pokemon.name.equalsIgnoreCase(name)) {
                return pokemon;
            }
        }

        throw new PokemonNotFoundException("Pokemon not found.");
    }

    // Validates Pokemon data loaded from the JSON file
    public static void validatePokemon(Pokemon pokemon) {

        if (pokemon.name == null || pokemon.name.isBlank()) {
            throw new InvalidPokemonException("Pokemon name is invalid.");
        }

        if (pokemon.maxHp <= 0) {
            throw new InvalidPokemonException("Pokemon HP is invalid.");
        }

        if (pokemon.type == null) {
            throw new InvalidPokemonException("Pokemon type is invalid.");
        }

        if (pokemon.attacks == null
                || pokemon.attacks.isEmpty()
                || pokemon.attacks.size() > 4) {
            throw new InvalidPokemonException("Pokemon must have between 1 and 4 attacks.");
        }
    }

    public static void addSeedPokemon(ArrayList<Pokemon> pokemons) {

        Pokemon frallis = new Pokemon(
                "Frallis",
                Type.FIRE,
                1000
        );

        Attack klovenCurse = new Attack(
                "Kloven Curse",
                33,
                67,
                Type.FIRE
        );

        Attack frallisCharm = new Attack(
                "Frallis Charm",
                39,
                69,
                Type.ELECTRIC
        );

        Attack mysschock = new Attack(
                "Mys schock",
                97,
                99,
                Type.ELECTRIC
        );

        Attack snailMode = new Attack(
                "Snail Mode",
                10,
                100,
                Type.NORMAL
        );

        frallis.attacks.add(snailMode);
        frallis.attacks.add(mysschock);
        frallis.attacks.add(frallisCharm);
        frallis.attacks.add(klovenCurse);

        pokemons.add(frallis);

        Pokemon charmander = new Pokemon(
                "Charmander",
                Type.FIRE,
                90
        );

        Attack ember = new Attack(
                "Ember",
                35,
                90,
                Type.FIRE
        );

        charmander.attacks.add(ember);

        pokemons.add(charmander);

        Pokemon squirtle = new Pokemon(
                "Squirtle",
                Type.WATER,
                95
        );

        Attack waterGun = new Attack(
                "Water Gun",
                35,
                95,
                Type.WATER
        );

        squirtle.attacks.add(waterGun);

        pokemons.add(squirtle);

        Pokemon snorlax = new Pokemon(
                "Snorlax",
                Type.NORMAL,
                160
        );

        Attack bodySlam = new Attack(
                "Body Slam",
                50,
                85,
                Type.NORMAL
        );

        snorlax.attacks.add(bodySlam);

        pokemons.add(snorlax);

        Pokemon pikachu = new Pokemon(
                "Pikachu",
                Type.ELECTRIC,
                100
        );

        Attack thunderShock = new Attack(
                "Thunder Shock",
                40,
                90,
                Type.ELECTRIC
        );

        pikachu.attacks.add(thunderShock);

        pokemons.add(pikachu);

        Pokemon bulbasaur = new Pokemon(
                "Bulbasaur",
                Type.GRASS,
                90
        );

        Attack vineWhip = new Attack(
                "Vine Whip",
                35,
                95,
                Type.GRASS
        );

        bulbasaur.attacks.add(vineWhip);

        pokemons.add(bulbasaur);

        Pokemon roffeMannen = new Pokemon(
                "RoffeMannen",
                Type.NORMAL,
                120
        );

        Attack roffeSmash = new Attack(
                "Roffe Smash",
                45,
                90,
                Type.NORMAL
        );

        Attack sockSteal = new Attack(
                "Sock Steal",
                30,
                95,
                Type.NORMAL
        );

        Attack zoomies = new Attack(
                "Zoomies",
                25,
                100,
                Type.NORMAL
        );

        Attack stinkBomb = new Attack(
                "Stink Bomb",
                40,
                85,
                Type.NORMAL
        );

        roffeMannen.attacks.add(roffeSmash);
        roffeMannen.attacks.add(sockSteal);
        roffeMannen.attacks.add(zoomies);
        roffeMannen.attacks.add(stinkBomb);

        pokemons.add(roffeMannen);

        Pokemon ivyCow = new Pokemon(
                "Ivy Cow",
                Type.GRASS,
                110
        );

        Attack grassAttack = new Attack(
                "Grass Attack",
                35,
                95,
                Type.GRASS
        );

        Attack salamanderAttack = new Attack(
                "Salamander Attack",
                40,
                90,
                Type.FIRE
        );

        Attack cinnamonbunRoll = new Attack(
                "Cinnamonbun Roll",
                30,
                100,
                Type.NORMAL
        );

        ivyCow.attacks.add(grassAttack);
        ivyCow.attacks.add(salamanderAttack);
        ivyCow.attacks.add(cinnamonbunRoll);

        pokemons.add(ivyCow);
    }
}
