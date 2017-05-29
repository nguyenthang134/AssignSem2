package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLibConnection {
	private static final String URL = "jdbc:mysql:";
    private static final String DATABASE = "library";
    private static final String HOST_PORT = "//localhost/";
    private static final String USERNAME = "?user=root";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        String url = URL + HOST_PORT + DATABASE + USERNAME + PASSWORD;
        Connection connect = DriverManager.getConnection(url);
        return connect;
    }
}
