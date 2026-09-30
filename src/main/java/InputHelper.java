import java.util.Scanner;

public class InputHelper {

    public static int readIntInRange(
            Scanner scanner,
            String message,
            int min,
            int max
    ) {
        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Enter a number between " + min + " and " + max + "."
                );

            } catch (NumberFormatException e) {
                System.out.println(
                        "Invalid input. Enter a whole number."
                );
            }
        }
    }

    public static String readValidName(
            Scanner scanner,
            String message
    ) {
        while (true) {
            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }

            if (input.length() > 50) {
                System.out.println(
                        "Name is too long. Maximum 50 characters."
                );
                continue;
            }

            if (input.contains(";")) {
                System.out.println("Name cannot contain ;");
                continue;
            }

            return input;
        }
    }
}
