package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.IOException;

public class MainGameSceneController {


    @FXML
    Button flipCardsButton;
    @FXML
    Button guessButton;
    @FXML
    public ImageView image1;
    @FXML
    public ImageView image2;
    @FXML
    public ImageView image3;
    @FXML
    public ImageView image4;
    @FXML
    public ImageView image5;

    public void initialize() {
        Game.createDeck();
        Game.createHand();

    }

    public void handleFlipCardsButtonPressed() {
        flipCardsButton.setStyle("-fx-background-color: gray; ");
    }

    public void handleFlipCardsButtonReleased() {
        flipCardsButton.setStyle("-fx-background-color: #adadad; ");
        flipCardsButton.setOpacity(0.0);

        image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[0].toString() + ".jpg"));
        image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[1].toString() + ".jpg"));
        image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[2].toString() + ".jpg"));
        image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[3].toString() + ".jpg"));
        image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[4].toString() + ".jpg"));
    }


    public void handleGuessButtonPressed() {

    }

    public void handleGuessButtonReleased() {

    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
    }
}
