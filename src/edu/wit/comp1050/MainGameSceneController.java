package edu.wit.comp1050;

import com.sun.scenario.animation.shared.FiniteClipEnvelope;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import javax.swing.*;
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

    @FXML
    public TextField scoreTextBox;
    @FXML
    public Text mainText;

    public int score;
    boolean buttonValue;

    public void initialize() {
        buttonValue = true;
        handInit();
    }

    public void handleFlipCardsButtonPressed() {
        flipCardsButton.setStyle("-fx-background-color: gray; ");
    }

    public void handleFlipCardsButtonReleased() {
        if(buttonValue) {
            flipCardsButton.setStyle("-fx-background-color: #adadad; ");
            flipCardsButton.setOpacity(0.0);

            image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[0].toString() + ".jpg"));
            image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[1].toString() + ".jpg"));
            image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[2].toString() + ".jpg"));
            image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[3].toString() + ".jpg"));
            image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[4].toString() + ".jpg"));

            flipCardsButton.setText("Play Again");
            buttonValue = false;

        }else if(flipCardsButton.getOpacity() != 0.0) {

            flipCardsButton.setStyle("-fx-background-color: #adadad; ");

            image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));

            scoreTextBox.setText("");
            mainText.setText("Guess The Score");
            flipCardsButton.setText("Flip Cards");
            handInit();

            buttonValue = true;
        }
    }


    public void handleGuessButtonPressed() {
        guessButton.setStyle("-fx-background-color: gray; ");
    }

    public void handleGuessButtonReleased() {
        if(scoreTextBox.getText().compareToIgnoreCase("19") == 0){
            mainText.setText("That's impossible, you fool...");
        }else if(scoreTextBox.getText().compareToIgnoreCase(String.valueOf(score)) == 0) {
            mainText.setText("You guessed it!!! The score is " + score);
            showScore();
        }else {
            mainText.setText("Wrong! The score is " + score);
        }
        guessButton.setStyle("-fx-background-color: #adadad; ");

        flipCardsButton.setOpacity(100.0);

    }

    public void handleEnterKeyTyped() {
    }

    public void handInit() {
        Game.createDeck();
        Game.createHand();
        score = Game.h.getScore();
    }

    public void showScore() {

    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
    }
}
