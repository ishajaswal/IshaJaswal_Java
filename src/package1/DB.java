package package1;



import java.sql.*;

public class DB {
    private Connection connection;

    public DB() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/moviesDB",
                "root",
                "ishajaswal"
            );
        } catch (SQLException e) {
            System.out.println("Could not connect to the database");
            e.printStackTrace();
        }
    }

    public void addMovie(String name, int rating) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Movie name cannot be empty");
            return;
        }

        try {
            String query = "INSERT INTO movies (name, rating) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setInt(2, rating);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while adding the movie.");
            e.printStackTrace();
        }
    }

    public void showTopMovies() {
        try {
            String query = "SELECT name, rating FROM movies ORDER BY rating DESC LIMIT 3";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);

            System.out.println("Top 3 Movies:");
            while (result.next()) {
                String movieName = result.getString("name");
                int movieRating = result.getInt("rating");
                System.out.println("- " + movieName + " (Rating: " + movieRating + ")");
            }

            result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while fetching top movies");
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error while closing the connection");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DB db = new DB();

        db.addMovie("Vampire Diaries", 9);
        db.addMovie("Friends", 10); 
        db.addMovie("The Dark Knight", 8);
        db.addMovie("Inception", 9);

        db.showTopMovies();
        db.closeConnection();
    }
}
