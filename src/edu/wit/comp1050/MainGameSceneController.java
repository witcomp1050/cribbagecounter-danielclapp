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
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;

public class MainGameSceneController {


    @FXML
    BorderPane borderPane1;
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
    @FXML
    public Text timerText;


    public int score;
    boolean buttonValue;

    Runnable runnable = () -> {
        for (double i = 99; i >= 0; i--) {
            timerText.setText("" + (i / 10));
            mainText.setX(borderPane1.getWidth() / 2);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Thread timer = new Thread(runnable);

    public void initialize() {
        buttonValue = true;
        handInit();

        Runnable checkTime = () -> {
            while(true) {
                if(timerText.getText().compareToIgnoreCase("0.0") == 0){
                    timer.stop();
                    timerText.setText("");
                    mainText.setText("You ran out of time ... too bad. the score was " + score);
                    flipCardsButton.setOpacity(100.0);
                    showScore();
                }
            }
        };

        Thread checkTimeThread = new Thread(checkTime);
        checkTimeThread.start();
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

            timer.start();

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
        double time = Double.parseDouble(timerText.getText());
        timer.stop();
        timer = new Thread(runnable);
        if(scoreTextBox.getText().compareToIgnoreCase("19") == 0){
            mainText.setText("That's impossible, you fool...");
        }else if(scoreTextBox.getText().compareToIgnoreCase(String.valueOf(score)) == 0) {
            mainText.setText("The score is " + score + " You guessed it in " + (10.0 - time) + " seconds");
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
