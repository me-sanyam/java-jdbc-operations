package com.upload_images;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;

public class Main {

    public static String url = "";
    public static String userName = "";
    public static String passowrd = "";

    public static void main(String[] args) {
        try{
            Connection conn = DriverManager.getConnection(url, userName, passowrd);
            System.out.println("Connection Succesfully created");

            String image = "C:\\Users\\sanya\\OneDrive\\Pictures\\ss-1.png";
            String name = "user1";
            // Inserting Image;
            InsertImage(conn, image, name);

            // Retreive Image
            RetreiveImage(conn);
            conn.close();
            System.out.println("Connection Closed");
        }catch(SQLException e) {
            System.out.println("-- Error in JDBC Connection : "+e.getMessage());
        }
    }


    private static void InsertImage(Connection conn, String image, String name){
        String query = "INSERT INTO tbl_user (name, avatar ,time) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            FileInputStream inputStream = new FileInputStream(image);
            stmt.setString(1, name);
            stmt.setBinaryStream(2, inputStream);
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            int rowsEffected = stmt.executeUpdate();
            System.out.println("--> Image Inserted Successfully and rows effected are: "+rowsEffected); 
            stmt.close();
        }catch(SQLException | FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private static void RetreiveImage(Connection conn){
        String query = "SELECT * FROM tbl_user;";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){ 
                String name = result.getString("name");
                byte[] img = result.getBytes("avatar");
                Timestamp time = result.getTimestamp("time");
                String image = writeImage(img, time);
                System.out.println("Name: "+name+" Image: "+image+" Time: "+time);
                System.out.println("------------------------------------------------");
            }
            result.close();
            statement.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static String writeImage(byte[] image, Timestamp time){
        try {
            String filename = "image-"+time.getTime()+".png";
            FileOutputStream outputStream = new FileOutputStream(filename);
            outputStream.write(image);
            outputStream.close();
            return filename;
        }catch(IOException e){
           e.printStackTrace();
           return null;
        }
    }
}