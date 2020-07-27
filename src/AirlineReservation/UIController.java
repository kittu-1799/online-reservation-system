package AirlineReservation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class UIController implements Initializable {

    @FXML
    public TextField Username;

    @FXML
    private PasswordField Password;

    @FXML
    public Label error;

    public static Stage admin=new Stage();

    //public int lno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void logIn() throws Exception
    {
        if(Username.getText().equals("") || Password.getText().equals(""))
        {
            AlertBox.error("Some Field is Empty");
            return;
        }
        if(check_username_password(Username.getText(),Password.getText()))
        {
            openFlyHigh();
        }
        else
        {
            AlertBox.error("Username or Password is incorrect");
        }

    }
    public void Delete() throws Exception
    {
        if(Username.getText().equals("") || Password.getText().equals(""))
        {
            AlertBox.error("Some Field is Empty");
            return;
        }
        if(doDelete(Username.getText(),Password.getText()))
        {
            AlertBox.error("Account id deleted");
            return;
        }
        else
        {
            AlertBox.error("Username or Password is incorrect");
        }

    }
    public void openFlyHigh()throws Exception{

        admin=new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("FlyHigh.fxml"));
        admin.initModality(Modality.APPLICATION_MODAL);
        AnchorPane pane=loader.load();
        Scene scene=new Scene(pane);

        admin.setTitle(" Admin");

        admin.setScene(scene);
        admin.show();

    }
    public boolean doDelete(String username,String password) throws Exception{
        Connection conn=ConnectionDB.getConnection();
        Statement s=conn.createStatement();
        String query="delete from login where email ='"+ username +"';";
        if(check_username_password(username,password)) {
            s.executeUpdate(query);
            return true;
        }
        return false;
    }

    private boolean check_username_password(String username,String password) throws Exception
    {
        Connection con=ConnectionDB.getConnection();
        Statement st=con.createStatement();
        String q="select * from login where email = '" + username +"';";
        ResultSet rs=st.executeQuery(q);

        while (rs.next()) {
            if(password.compareTo(rs.getString("password"))==0) {
                return true;
            }
        }
        return false;
    }

    public void signup()throws Exception {

        Stage su=new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Registration.fxml"));
        su.initModality(Modality.APPLICATION_MODAL);
        AnchorPane pane=loader.load();

        Scene scene=new Scene(pane);

        su.setTitle(" SIGN UP");

        su.setScene(scene);
        su.show();
    }


}
