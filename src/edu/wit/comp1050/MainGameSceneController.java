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
import java.util.ArrayList;


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
    public ArrayList<String> scoreList = new ArrayList<>();
    boolean buttonValue;

    public static String userScore, actualScore;


    //Runnable thread used to handle the countdown timer.
    Runnable runnable = () -> {
        for (double i = 99; i >= 0; i--) {
            timerText.setText("" + (i / 10));
            mainText.setX(borderPane1.getWidth() / 2);
            sleep(100);
        }
    };

    Thread timer = new Thread(runnable);

    //thread used to check when the timer reaches 0, aka the user ran out of time.
    Runnable checkTime = () -> {
        boolean b = true;
        while(b) {
            if(timerText.getText().equals("0.0")){
                timerText.setText("");
                mainText.setText("You ran out of time ... too bad. the score was " + score);
                flipCardsButton.setOpacity(100.0);
                timer.stop();
                b = false;
            }
            //System.out.println(timerText.getText());
        }

    };

    Thread checkTimeThread = new Thread(checkTime);

    public void initialize() {
        buttonValue = true;
        handInit();
    }

    //button changes color to indicate it has been pressed
    public void handleFlipCardsButtonPressed() {
        flipCardsButton.setStyle("-fx-background-color: gray; ");
    }

    //flips the cards over, starts the timer, checks to see if the user typed the correct score in.
    //button is used as a 'Flip Cards' button and a 'Play Again' button toggled by the boolean button value
    public void handleFlipCardsButtonReleased() {

        // buttonValue true = flipCards
        // buttonValue false = playAgain

        //Flip Cards Button
        if(buttonValue) {

            //changes button color back and hides the button once the cards are flipped, you cannot flip the cards again
            flipCardsButton.setStyle("-fx-background-color: #adadad; ");
            flipCardsButton.setOpacity(0.0);

            //switches images from the back of card to the front
            image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[0].toString() + ".jpg"));
            image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[1].toString() + ".jpg"));
            image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[2].toString() + ".jpg"));
            image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[3].toString() + ".jpg"));
            image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/" + Game.hand[4].toString() + ".jpg"));

            //initiates timer thread and the thread that checks if time's up
            timer.start();
            checkTimeThread.start();

            //once 'Flip Card' is pressed, the button becomes 'Play Again'
            //updating its properties here
            flipCardsButton.setText("Play Again");
            buttonValue = false;

        //Play Again Button
        }else if(flipCardsButton.getOpacity() != 0.0) {

            //changes button color back
            flipCardsButton.setStyle("-fx-background-color: #adadad; ");

            //resets cards to the back of a card
            image1.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image2.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image3.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image4.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));
            image5.setImage(new Image("edu/wit/comp1050/CardImages/JPEG/Bicycle_back.jpg"));

            //clears the timer and score, sets button to flip cards, and creates a new hand
            timerText.setText("");
            scoreTextBox.setText("");
            mainText.setText("Guess The Score");
            flipCardsButton.setText("Flip Cards");
            handInit();

            //resets the threads
            checkTimeThread = new Thread(checkTime);
            timer = new Thread(runnable);

            buttonValue = true;
        }
    }

    //button changes color to indicate it has been pressed
    public void handleGuessButtonPressed() {
        guessButton.setStyle("-fx-background-color: gray; ");
    }

    //checks the score when the button is pressed and proceeds accordingly
    //this can also be triggered by pressing the enter button(hopefully)
    public void handleGuessButtonReleased() throws IOException {
        //logs the time and stops running threads
//        double time = Double.parseDouble(timerText.getText());
        timer.stop();
        checkTimeThread.stop();

        userScore = scoreTextBox.getText();
        actualScore = String.valueOf(score);

        //checks if you got the score right
//        if(scoreTextBox.getText().compareToIgnoreCase("19") == 0){
//            mainText.setText("That's impossible, you fool..."); //haha
//        }else if(scoreTextBox.getText().compareToIgnoreCase(String.valueOf(score)) == 0) {
//            mainText.setText("The score is " + score + "! You guessed it in " + (10.0 - time) + " seconds");
//        }else {
//            mainText.setText("Wrong! The score is " + score);
//        }

        ShowScoreSceneController sssController = new ShowScoreSceneController();
        guessButton.getScene().setRoot(sssController.getContent());

        //tells you what the score is
        mainText.setText("The score was " + score);
        timerText.setText("");

        //changes back button color
        guessButton.setStyle("-fx-background-color: #adadad; ");

        //makes play again button visible now that the game is over
        flipCardsButton.setOpacity(100.0);

    }

    //doess the same thing as the guess button
    public void handleEnterKeyTyped() {
    }

    //creates a hand to be scored
    public void handInit() {
        Game.createDeck();
        Game.createHand();
        score = Game.h.getScore();
        for(String s : Game.h._scores)
            if(s != null)
                scoreList.add(s);
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
    }
}
