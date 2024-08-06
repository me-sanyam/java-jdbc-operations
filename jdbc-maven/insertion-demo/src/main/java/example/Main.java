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

            // Updating Data
            UpdateData(connection, "Test","Updated", 22, 2);

            // Deleting Data
            DeleteData(connection, 3);

            connection.close(); // close the connection
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void InsertData(Connection connection, String firstname, String lastname, int age){
        String query = "INSERT INTO details (firstName, lastName, age) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setInt(3, age);
            int rowsEffected = stmt.executeUpdate();
            System.out.println("No of rows effected in insert are: "+rowsEffected);
            stmt.close(); // close the statement
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private static void UpdateData(Connection connection, String firstname, String lastname, int age, int id){
        String query = "UPDATE details SET firstName = ?, lastName = ?, age = ? WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setInt(3, age);
            stmt.setInt(4, id);
            int rowsEffected = stmt.executeUpdate();
            System.out.println("No of rows effected in update are: "+rowsEffected);
            stmt.close(); // close the statement
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    private static void DeleteData(Connection connection, int id){
        String query = "DELETE FROM details WHERE id = ?;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(4, id);
            int rowsEffected = stmt.executeUpdate();
            System.out.println("No of rows effected in Delete are: "+rowsEffected);
            stmt.close(); // close the statement
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}