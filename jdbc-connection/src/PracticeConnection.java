import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class PracticeConnection{
    public static void main(String[] args){
        String url = "";
        String userName = "";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM details;");

            while (result.next()){
                
                int id = result.getInt("id");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");

                System.out.println("User details are: id -> "+id+" Firstname --> "+firstName+ " Lastname --> "+lastName);
                System.out.println("--------------------------------------------------------------------");
            }
            // closing all statements, resultsets, connections
            result.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("--> Exception in connection: "+e.getMessage());
            System.out.println("Failed to make Database Connection");
        }
    }
}