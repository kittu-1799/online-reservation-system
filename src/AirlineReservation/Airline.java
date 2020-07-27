package AirlineReservation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Airline extends Application {

    public static Stage mainStage=new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Airline.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setTitle("FlyHigh");
        primaryStage.setScene(new Scene(root, 1020, 640));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
