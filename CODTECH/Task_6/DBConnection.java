import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Change DB URL if needed
    private static final String USER = "your_username"; // DB Username
    private static final String PASS = "your_password"; // DB Password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver"); // Load Oracle Driver
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

