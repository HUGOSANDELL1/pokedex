import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        // Scanner is used to read input from the user
        Scanner scanner = new Scanner(System.in);
        // These two lists store Pokemon names and HP values
        ArrayList<Integer> hps = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
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
                // Show all Pokemon currently stored in the lists
                case 1:
                    System.out.println("View all Pokemon");


                    for (int i = 0; i < names.size(); i++) {
                        System.out.println(
                                (i + 1) + ". " + names.get(i) + " - HP: " + hps.get(i)
                        );
                    }
                    break;
                // Add a new Pokemon
                case 2:
                    System.out.println("Add Pokemon");
                    System.out.print("Pokemon name: ");
                    String name = scanner.nextLine();

                    int hp = readIntInRange(scanner, "HP: ", 1, 1000);
                    // Store name and HP at the same position in both lists
                    names.add(name);
                    hps.add(hp);
                    System.out.println(name + " added!");
                    break;
                case 3:
                    System.out.println("Edit Pokemon");
                    // Prevents trying to edit when there are no Pokemon in the list
                    if (names.isEmpty()) {
                        System.out.println("No Pokemon to edit.");
                        break;
                    }

                    for (int i = 0; i < names.size(); i++) {
                        System.out.println(
                                (i + 1) + ". " + names.get(i) + " - HP: " + hps.get(i)
                        );
                    }

                    int editChoice = readIntInRange(
                            scanner,
                            "Choose Pokemon to edit: ",
                            1,
                            names.size()
                    );

                    System.out.print("New Pokemon name: ");
                    String newName = scanner.nextLine();

                    int newHp = readIntInRange(scanner, "New HP: ", 1, 1000);
                    // User chooses Pokemon starting at 1, but ArrayList index starts at 0
                    names.set(editChoice - 1, newName);
                    hps.set(editChoice - 1, newHp);

                    System.out.println(newName + " updated!");
                    break;
                // Remove a Pokemon
                case 4:
                    System.out.println("Remove Pokemon");
                    // Prevents trying to remove from an empty list
                    if (names.isEmpty()) {
                        System.out.println("No Pokemon to remove.");
                        break;
                    }
                    // Show the Pokemon before the user chooses one
                    for (int i = 0; i < names.size(); i++) {
                        System.out.println((i + 1) + ". " + names.get(i) + " - HP: " + hps.get(i));
                    }

                    int removeChoice = readIntInRange(
                            scanner,
                            "Choose Pokemon to remove: ",
                            1,
                            names.size()
                    );
                    // User sees Pokemon starting at 1,
                    // but ArrayList indexes start at 0
                    String removedName = names.remove(removeChoice - 1);
                    hps.remove(removeChoice - 1);

                    System.out.println(removedName + " was removed from Pokedex!");
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
