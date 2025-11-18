/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author spand
 */

import java.sql.*;

public class ConnectionProvider
{
    private static final String dbName="attendanceSystem";
    private static final String dbUrl="jdbc:mysql://localhost:3306/";
    private static final String dbUsername="root";
    private static final String dbPassword="MySQL@1919";
    
    
    public static Connection getConn()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c=DriverManager.getConnection(dbUrl+"?useSSL=false", dbUsername, dbPassword);
            
            if(!databaseExists(c, dbName))
            {
                createDatabase(c, dbName);
            }
            c.close();
            
            c=DriverManager.getConnection(dbUrl+dbName+"?useSSL=false", dbUsername, dbPassword);
            
            return c;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    private static boolean databaseExists(Connection con, String dbName) throws Exception
    {
        Statement statement=con.createStatement();
        return statement.executeQuery("SHOW DATABASES LIKE '"+dbName+"'").next();
    }
    
    private static void createDatabase(Connection con, String dbName) throws Exception
    {
        Statement statement=con.createStatement();
        statement.executeUpdate("CREATE DATABASE "+dbName);
        System.out.println("Database "+dbName+" created successfully");
    }
}
