package package1;
import java.util.Scanner;



class InvalidLoginException extends Exception {
 public InvalidLoginException(String message) {
     super(message);
 }
}



public class User {

    private static final String validUsername = "isha";
    private static final String validPassword = "Isha123";
    private static int failedLoginAttempts = 0;

    public static void authenticate(String inputUsername, String inputPassword) throws InvalidLoginException {
        if (failedLoginAttempts >= 3) {
            throw new InvalidLoginException("You have exceeded the maximum number of login attempts.");
        }

        if (validUsername.equals(inputUsername) && validPassword.equals(inputPassword)) {
            System.out.println("Login successful!");
            failedLoginAttempts = 0;
        } else {
            failedLoginAttempts++;
            System.out.println("Invalid credentials. Attempt " + failedLoginAttempts + " of 3.");
            if (failedLoginAttempts >= 3) {
                throw new InvalidLoginException("You have exceeded the maximum number of login attempts");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (failedLoginAttempts < 3) {
                System.out.print("Enter username- ");
                String inputUsername = scanner.nextLine();

                System.out.print("Enter password: ");
                String inputPassword = scanner.nextLine();

                authenticate(inputUsername, inputPassword);
            }
        } catch (InvalidLoginException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}



