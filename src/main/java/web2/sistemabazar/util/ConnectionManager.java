package web2.sistemabazar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistemabazar";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    private static Connection currentConnection = null;


    public static Connection getCurrentConnection() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");

        if(currentConnection == null){
            currentConnection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return currentConnection;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
