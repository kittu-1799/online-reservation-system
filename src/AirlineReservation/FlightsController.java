package AirlineReservation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;


public class FlightsController implements Initializable {

    @FXML
    public TextArea ta;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadFlights();
    }

    private void loadFlights(){
        try {
            Connection con = ConnectionDB.getConnection();

            String source = FlyHighController.source;
            String destination = FlyHighController.destination;

            Statement st=con.createStatement();
            ta.appendText("Flights\nFNO  \tPRICE \tF_NAME   \tA_TIME  \tD_TIME  \tTOTAL TIME\n");
            ResultSet rs = st.executeQuery("select f.fno,f.price,f.fname,t.atime,t.dtime,t.time_taken from flights f,flight_time t where f.fno=t.fno and f.flight_from='"+source+"' and f.fdestination='"+destination+"';");
            while (rs.next()){
                ta.appendText(rs.getString(1)+ " \t\t");
                ta.appendText(rs.getString(2) + "\t");
                ta.appendText("Rs.\t"+rs.getString(3).toUpperCase()+"\t\t");
                ta.appendText(rs.getString(4) + "\t");
                ta.appendText(rs.getString(5) + "\t");
                ta.appendText(rs.getString(6) + "\t");
                ta.appendText("\n");
            }

        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public void sortTime(){
        try {
            ta.setText("");
            Connection con = ConnectionDB.getConnection();

            String source = FlyHighController.source;
            String destination = FlyHighController.destination;

            Statement st=con.createStatement();
            ta.appendText("Flights\nFNO  \tPRICE \tF_NAME   \tA_TIME  \tD_TIME  \tTOTAL TIME\n");
            ResultSet rs = st.executeQuery("select f.fno,f.price,f.fname,t.atime,t.dtime,t.time_taken from flights f,flight_time t where f.fno=t.fno and f.flight_from='"+source+"' and f.fdestination='"+destination+"' order by t.time_taken;");
            while (rs.next()){
                ta.appendText(rs.getString(1)+ " \t\t");
                ta.appendText(rs.getString(2) + "\t");
                ta.appendText("Rs.\t"+rs.getString(3).toUpperCase()+"\t\t");
                ta.appendText(rs.getString(4) + "\t");
                ta.appendText(rs.getString(5) + "\t");
                ta.appendText(rs.getString(6) + "\t");
                ta.appendText("\n");
            }

        }
        catch (Exception e){
            e.getMessage();
        }
    }

    public void sortPrice(){
        try {
            ta.setText("");
            Connection con = ConnectionDB.getConnection();

            String source = FlyHighController.source;
            String destination = FlyHighController.destination;

            Statement st=con.createStatement();
            ta.appendText("Flights\nFNO  \tPRICE \tF_NAME   \tA_TIME  \tD_TIME  \tTOTAL TIME\n");
            ResultSet rs = st.executeQuery("select f.fno,f.price,f.fname,t.atime,t.dtime,t.time_taken from flights f,flight_time t where f.fno=t.fno and f.flight_from='"+source+"' and f.fdestination='"+destination+"' order by f.price;");
            while (rs.next()){
                ta.appendText(rs.getString(1)+ " \t\t");
                ta.appendText(rs.getString(2) + "\t");
                ta.appendText("Rs.\t"+rs.getString(3).toUpperCase()+"\t\t");
                ta.appendText(rs.getString(4) + "\t");
                ta.appendText(rs.getString(5) + "\t");
                ta.appendText(rs.getString(6) + "\t");
                ta.appendText("\n");
            }

        }
        catch (Exception e){
            e.getMessage();
        }
    }
    public void registerr() throws Exception {

        Stage rr=new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("Register.fxml"));
        rr.initModality(Modality.APPLICATION_MODAL);
        AnchorPane pane=loader.load();

        Scene scene=new Scene(pane);

        rr.setTitle(" REGISTER ");

        rr.setScene(scene);
        rr.show();
    }
}

