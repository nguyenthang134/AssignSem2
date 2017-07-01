package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseLibConnection {
	private static final String URL = "jdbc:mysql:";
    private static final String DATABASE = "library";
    private static final String HOST_PORT = "//localhost/";
    private static final String USERNAME = "?useUnicode=true&characterEncoding=utf-8&user=root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
    	Connection connect = null;
//    	String url = "jdbc:mysql:library//localhost/?useUnicode=true&characterEncoding=utf-8&user=root";
    	String url = URL + HOST_PORT + DATABASE + USERNAME + PASSWORD;
    	try{
            connect = DriverManager.getConnection(url);
        }
    	catch(SQLException e){
    		e.printStackTrace();
    	}
        return connect;
    }
}