package com.budg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String password = "pass";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM tempdb";
            runthisquery(conn, query);
        }
        catch (Exception exception) {
            System.out.println(exception);
        }
        
        
    }

    public static void runthisquery(Connection conn, String query) {
        Statement stmt = null;
        ResultSet rs = null;
        
        
        try {
            
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }

            // Now do something with the ResultSet ....
        }
        catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
