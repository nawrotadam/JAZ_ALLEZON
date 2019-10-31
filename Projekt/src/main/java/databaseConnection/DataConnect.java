package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection()
    {
        try{
            Class.forName("");
            Connection connection = DriverManager.getConnection(
                    "","",""); // TODO dodac dane polaczenia z baza
            return connection;
        } catch (Exception e) {
            System.out.println("Database.getConnection() Error -->"
            + e.getMessage());
            return null;
        }
    }

    public static void close(Connection connection) {
        try{
            connection.close();
        } catch(Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

}
