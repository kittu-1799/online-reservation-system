package AirlineReservation;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FlyHighController implements Initializable {

    @FXML
    public ChoiceBox<String> cb1,cb2;

    public static String source;
    public static String destination;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb1.getItems().addAll("Source","allahabad","pune","bangalore");
        cb1.setValue("Source");

        cb2.getItems().addAll("Destination","allahabad","Bangalore","Delhi","dubai","goa","kanpur","pune","Mumbai","sirsa","shimla");
        cb2.setValue("Destination");
    }

    public void search(){
        source = cb1.getValue();
        destination = cb2.getValue();

        if(source.equals("Source") || destination.equals("Destination") || source.equals(destination)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Choose Source and ");
            alert.setContentText("Choose Source and Destination correctly");
            alert.show();
            return;
        }

        System.out.println(source);
        System.out.println(destination);

        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("flights.fxml"));
            stage.setTitle("Flights");
            stage.setScene(new Scene(root, 1100, 650));
            stage.show();
        }
        catch (Exception e){
            e.getMessage();
        }
    }
}
