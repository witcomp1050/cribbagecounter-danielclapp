package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.lang.Thread.yield;

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
    public ArrayList<String> scoreList = new ArrayList<String>();
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

    Runnable checkTime = () -> {
        boolean b = true;
        while(b) {
            if(timerText.getText().equals("0.0")){
                timerText.setText("");
                mainText.setText("You ran out of time ... too bad. the score was " + score + ". Better Luck Next Time");
                flipCardsButton.setOpacity(100.0);
                try {
                    showScore();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.stop();
                b = false;
            }
            System.out.println(timerText.getText());
        }

    };

    Thread checkTimeThread = new Thread(checkTime);

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

            timer.start();
            checkTimeThread.start();

            flipCardsButton.setText("Play Again");
            buttonValue = false;

        }else if(flipCardsButton.getOpacity() != 0.0) {

            flipCardsButton.setStyle("-fx-background-color: #adadad; ");

            image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));

            timerText.setText("");
            scoreTextBox.setText("");
            mainText.setText("Guess The Score");
            flipCardsButton.setText("Flip Cards");
            handInit();

            checkTimeThread = new Thread(checkTime);
            timer = new Thread(runnable);
            buttonValue = true;
        }
    }


    public void handleGuessButtonPressed() {
        guessButton.setStyle("-fx-background-color: gray; ");
    }

    public void handleGuessButtonReleased() throws InterruptedException {
        double time = Double.parseDouble(timerText.getText());
        timer.stop();
        checkTimeThread.stop();
        if(scoreTextBox.getText().compareToIgnoreCase("19") == 0){
            mainText.setText("That's impossible, you fool...");
        }else if(scoreTextBox.getText().compareToIgnoreCase(String.valueOf(score)) == 0) {
            mainText.setText("The score is " + score + "! You guessed it in " + (10.0 - time) + " seconds");
        }else {
            mainText.setText("Wrong! The score is " + score);
        }

        mainText.setText("The Score was " + score);
        showScore();

        guessButton.setStyle("-fx-background-color: #adadad; ");

        flipCardsButton.setOpacity(100.0);

    }

    public void handleEnterKeyTyped() {
    }

    public void handInit() {
        Game.createDeck();
        Game.createHand();
        score = Game.h.getScore();
        for(String s : Game.h._scores)
            if(s != null)
                scoreList.add(s);
    }

    public void showScore() throws InterruptedException {
//        String[] temp = new String[2];
//        String[] images = new String[5];
//
//        //Thread.sleep(0);
//
//        for(String s : scoreList) {
//            temp = s.split(":");
//            images = temp[1].split(", ");
//
//            mainText.setText(temp[0]);
//            for(String i : images)
//                if(i != null && image1.getImage().equals(i + ".jpg"))
//                    image1.setOpacity(0.0);
//
//        }
    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
    }
}
