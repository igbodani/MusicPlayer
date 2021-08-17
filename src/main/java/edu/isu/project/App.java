package edu.isu.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        URL url = new File("src/main/java/edu/isu/project/scene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
