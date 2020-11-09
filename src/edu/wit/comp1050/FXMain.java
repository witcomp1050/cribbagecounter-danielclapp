package edu.wit.comp1050;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


public class FXMain extends Application {

    Scene titleScene, gameScene;

    @Override
    public void start(Stage window) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TitleScene.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.setTitle("Cribbage Counter Game");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }}
