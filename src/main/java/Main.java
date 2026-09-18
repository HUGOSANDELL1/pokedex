import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Scanner is used to read input from the user
        Scanner scanner = new Scanner(System.in);
        // Stores all Pokemon objects in the Pokedex
        ArrayList<Pokemon> pokemons = new ArrayList<>();
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
            System.out.println("8. Exit");
            // Reads a valid menu choice between 1 and 8
            int choice = readIntInRange(scanner, "Choice: ", 1, 8);

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
                    }
                    break;
                    // Add new Pokemon
                case 2:
                    System.out.println("Add Pokemon");
                    System.out.print("Pokemon name: ");
                    String name = scanner.nextLine();

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

                    System.out.print("New Pokemon name: ");
                    String newName = scanner.nextLine();

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
                    break;
                case 6:
                    System.out.println("Load");
                    break;
                case 7:
                    System.out.println("Reset");
                    break;
                // Stop the while loop and exit the program
                case 8:
                    System.out.println("Exit");
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
}
