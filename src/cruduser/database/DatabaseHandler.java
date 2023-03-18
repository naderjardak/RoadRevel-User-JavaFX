package cruduser.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public final class DatabaseHandler {
  
    private Connection cnx;
    
    private static DatabaseHandler instance;
    
    private final String URL = "jdbc:mysql://127.0.0.1/roadrevel";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DatabaseHandler() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecting !!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DatabaseHandler getInstance() {
        if(instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
