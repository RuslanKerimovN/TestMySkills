package ConnectionForDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionForDataBase {
    private static final String PASSWORD = "postgres";
    private static final String USER = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private Connection connection;

    public ConnectionForDataBase() {
        try {
            this.connection = DriverManager.getConnection(URL,USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
