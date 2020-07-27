package AirlineReservation;


import java.sql.*;

public class ConnectionDB {
    public static Connection getConnection(){

        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
            con = DriverManager.getConnection(url, "root", "0209");
            System.out.println("connected to DB");
        }
        catch(Exception e)
        {
            AlertBox.error("Error while connecting to DB");
            System.out.println("Error while connecting to DB");
        }
        return con;
    }
}
