import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe"; // Replace with your DB URL
    private static final String USER = "username"; // Replace with your DB username
    private static final String PASS = "password"; // Replace with your DB password

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver"); // Load driver
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
