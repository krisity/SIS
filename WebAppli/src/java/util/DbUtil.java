/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class that is used for initiating database connection. 
 * @author User
 * 
 */
public class DbUtil {
    
    // Declare a static variable from type Connection and set it to null
    private static Connection connection = null;

    // Method getConnection which establishes connection with the database
    public static Connection getConnection() {

        // if statement checking if the connection is not null
        if (connection != null)

            // Return the connection.
            return connection;

        // If the connection is null establish a connection
        else {

            try {

                // Name of the driver
                String driver = "com.mysql.jdbc.Driver";

                // Name of the url which includes the localhost, port and schema name
                String url = "jdbc:mysql://localhost:3306/datab";

                // User of the mysql database
                String user = "root";

                // Mysql password name
                String password = "root";

                // Returns the Class object associated with the driver.
                Class.forName(driver);

                // Attempts to establish a connection to the given database URL. 
                // The DriverManager attempts to select an appropriate driver from
                // the set of registered JDBC drivers.
                connection = DriverManager.getConnection(url, user, password);

                // Catch a possible exception.
            } catch (ClassNotFoundException | SQLException e) {
            }

            // Return the connection variable.
            return connection;
        }
    }
}