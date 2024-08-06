package example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {

    public static String url = "";
    public static String userName = "";
    public static String passowrd = "";
    
    public static void main(String[] args) {
        
        try {
            Connection connection = DriverManager.getConnection(url, userName, passowrd);
            
            // Insetring data
            InsertData(connection, "New-1","Inserted", 0);
            InsertData(connection, "New-2", "Inserted", 0);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void InsertData(Connection connection, String firstname, String lastname, int age){
        String query = "INSERT INTO details (firstName, lastName, age) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setInt(3, age);
            int rowsEffected = stmt.executeUpdate();
            System.out.println("No of rows effected are: "+rowsEffected);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}