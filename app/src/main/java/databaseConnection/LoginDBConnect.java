//package databaseConnection;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginDBConnect {
//
//    public static boolean validate(String username, String password)
//    {
//        Connection connection = null;
//        PreparedStatement ps;
//
//        try {
//            connection = DataConnect.getConnection();
//            ps = connection.prepareStatement("Select username, password from Users"); // TODO ustawic dialekt na psql w ubuntu
//            ps.setString(1, username);
//            ps.setString(2, password);
//
//            ResultSet resultSet = ps.executeQuery();
//
//            if(resultSet.next()) return true;
//        }
//        catch (SQLException e) {
//            System.out.println("Login error: " + e.getMessage());
//            return false;
//        }
//        finally{ DataConnect.close(connection);}
//
//        return false;
//    }
//
//}
