package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

    private static Connection connection;
    private static Statement statement;

    // Connect to MySQL (use your sandbox/local credentials)
    public static void connectToDB() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/saucedemo_db";  // Local example; change to sandbox URL if online (e.g. jdbc:mysql://db4free.net:3306/your_db)
        String username = "root";  // Your MySQL root/user
        String password = "Mypassword@26";  // Your password

        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    // Execute query and return ResultSet
    public static ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    // Close connection
    public static void closeDB() throws SQLException {
        if (statement != null) statement.close();
        if (connection != null) connection.close();
    }
}
