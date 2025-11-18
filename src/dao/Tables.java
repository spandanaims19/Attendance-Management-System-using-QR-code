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
import javax.swing.JOptionPane;

public class Tables
{
    private static boolean tableExists(Statement statement, String tableName) throws Exception
    {
        ResultSet resultSet=statement.executeQuery("SHOW TABLES LIKE '"+tableName+"'");
        return resultSet.next();
    }
    
    public static void main(String args[])
    {
        Connection c=null;
        Statement statement=null;
        
        try
        {
            c=ConnectionProvider.getConn();
            statement=c.createStatement();
            
            if(!tableExists(statement, "userdetails"))
            {
                statement.executeUpdate("CREATE TABLE userdetails (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NOT NULL, gender VARCHAR(50) NOT NULL, email VARCHAR(255) NOT NULL, contact VARCHAR(20) NOT NULL, address VARCHAR(500), state VARCHAR(100), city VARCHAR(100), regid VARCHAR(100) NOT NULL, imagename VARCHAR(100));");
            }
            
            if(!tableExists(statement, "userattendance"))
            {
                statement.executeUpdate("CREATE TABLE userattendance (userid INT NOT NULL, date DATE NOT NULL, checkin DATETIME, checkout DATETIME, workduration VARCHAR(100));");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        finally
        {
            try
            {
                if(c!=null)
                {
                    c.close();
                }
                
                if(statement!=null)
                {
                    statement.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
