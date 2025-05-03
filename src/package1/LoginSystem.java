package package1;

import java.sql.*;
import java.util.Scanner;

public class LoginSystem {
    private static Connection conn;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDB", "root", "ishajaswal");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n1. Login 2.\n Register\nEnter your choice (1/2)- ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Username: ");
                    String username = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    if (validateLogin(username, password)) {
                        System.out.println("Login successful! Welcome, " + username);
                        break;
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                } else if (choice == 2) {
                    System.out.print("New username: ");
                    String username = scanner.nextLine();
                    System.out.print("New password: ");
                    String password = scanner.nextLine();
                    registerUser(username, password);
                } else {
                    System.out.println("Invalid choice");
                }
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean validateLogin(String username, String password) {
        try (PreparedStatement pstmt = conn.prepareStatement("SELECT 1 FROM users WHERE username = ? AND password = ?")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void registerUser(String username, String password) {
        try (PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Registration successful!");
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
}
