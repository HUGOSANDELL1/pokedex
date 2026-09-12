import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Pokedex Started");

        boolean running = true;
        while (running) {

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

            int choice = readIntInRange(scanner, "Choice: ", 1, 8);

            switch (choice) {
                case 1:
                    System.out.println("View all Pokemon");
                    break;
                case 2:
                    System.out.println("Add");
                    break;
                case 3:
                    System.out.println("Edit");
                    break;
                case 4:
                    System.out.println("Remove");
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
                case 8:
                    System.out.println("Exit");
                    running = false;
                    System.out.println("Goodbye!");
                    break;
            }
        }
        scanner.close();
    }
    public static int readIntInRange(Scanner scanner, String message, int min, int max) {

        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max)  {
                    return value;
                }
                System.out.println("Enter a number between " + min + " and " + max + ".");

            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }

        }






    }


}
