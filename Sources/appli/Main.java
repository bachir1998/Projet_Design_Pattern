package appli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("TEAMS Attendees List Converter");
        primaryStage.setScene(new Scene(root, 460, 600));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
