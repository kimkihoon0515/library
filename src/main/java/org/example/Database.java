package org.example;

import java.sql.*;
public class Database {
    public void DatabaseMethod()
    {
        Connection conn = null;
        Statement st = null;

        String url = "jdbc:postgresql://127.0.0.1:5432/library";
        String user = "postgres";
        String password = "kw1996";
        try{
            System.out.println("SQL connecting");
            conn =DriverManager.getConnection(url,user,password);
            st =conn.createStatement();

        }catch (SQLException sqlEX) {
            System.out.println(sqlEX);
        }
    }
}
