package miniproduct.miniproduct;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database  {
    public static Connection conn(){
        String url = "jdbc:mysql://localhost:3308/java_mini_product";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection(url,user,password);
            System.out.println("Connection is Successful to the database"+url);
            return connect;
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn();
    }


}
