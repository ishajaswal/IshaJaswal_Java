package package1;



import java.sql.*;
import java.util.Scanner;

public class BookInventory {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookDB",
                "root",
                "ishajaswal"
            );
            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.print("1. Add  2. Delete  3. View  4. Exit: ");
                int choice = input.nextInt();
                input.nextLine(); 

                if (choice == 1) {
                    System.out.print("Title: ");
                    String title = input.nextLine();

                    System.out.print("Author: ");
                    String author = input.nextLine();

                    String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, title);
                    stmt.setString(2, author);
                    stmt.executeUpdate();
                    System.out.println("Book added.");
                    stmt.close();

                } else if (choice == 2) {
                    System.out.print("Book ID to delete: ");
                    int id = input.nextInt();

                    String sql = "DELETE FROM books WHERE id = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, id);
                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Book deleted.");
                    } else {
                        System.out.println("Book not found.");
                    }
                    stmt.close();

                } else if (choice == 3) {
                    String sql = "SELECT * FROM books";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String author = rs.getString("author");
                        System.out.println(id + "- " + title + " by " + author);
                    }

                    rs.close();
                    stmt.close();

                } else if (choice == 4) {
                    break;

                } else {
                    System.out.println("Invalid choice");
                }
            }

            conn.close();
            input.close();

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
