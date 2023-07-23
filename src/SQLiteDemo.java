import java.sql.*;

public class SQLiteDemo {
    public static void main(String[] args) {
        // Step 1: Connect to the database
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/praween/Desktop/SQLite/ex1.db");
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return;
        }

        // Step 2: Create a table (if not exists)
        try {
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "age INTEGER NOT NULL)";
            statement.executeUpdate(createTableSQL);
            System.out.println("Table 'employees' created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Step 3: Insert data into the table
        try {
            String insertDataSQL = "INSERT INTO employees (name, age) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL);
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setInt(2, 30);
            preparedStatement.executeUpdate();
            System.out.println("Data inserted into the table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Step 4: Query the data from the table
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Step 5: Close the connection
        try {
            connection.close();
            System.out.println("Database connection closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


