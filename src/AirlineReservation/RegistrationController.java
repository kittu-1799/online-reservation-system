package AirlineReservation;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class RegistrationController {

    @FXML
    public TextField SUemail,SUpassword,SUcp;

    public void cancell() {
        UIController.admin.close();
    }

    public boolean check_pno(ResultSet rss)throws Exception{
        if(rss.next())
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Email already exist");
            alert.show();
            return false;
        }
        return true;
    }
    public void save_data()throws Exception{


        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://localhost:3306/airline?autoReconnect=true&useSSL=false";
        Connection con = DriverManager.getConnection(url, "root", "0209");
        System.out.println("Connected to DB");
        Statement st=con.createStatement();
        ResultSet rs;


        String email_i=SUemail.getText();
        String pwd_i=SUpassword.getText();
        String cpwd_i=SUcp.getText();

        if(email_i.compareTo("")==0 || pwd_i.compareTo("")==0 || cpwd_i.compareTo("")==0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Some Field is empty");
            alert.show();
        }
        else if(check_pno(st.executeQuery("select email from login where email = '"+email_i+"';")))
        {

            String query_input="insert into login (email,password) values(\""+email_i+"\",\""+pwd_i+"\");";

            try{
                System.out.println(query_input);
                st.executeUpdate(query_input);
                System.out.println("done");

                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Data Saved");
                alert.show();
                cancell();

            }
            catch (Exception eq)
            {
                System.out.println("Error");
            }
        }
    }
}
