package edu.wit.comp1050;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    @FXML
    public Button settingsApplyButton;
    @FXML
    public TextField settingsTextBox;
    @FXML
    public CheckBox settingsCheckBox = new CheckBox();
    public static boolean settingsShowScore = true;

    public static double handsScored = 0, handsCorrect = 0;
    public static double percentCorrect = 0.0;
    static double averageTime = 0.0;
    static double bestTime = 11.0, time = 10.0;

    public int score;
    public static ArrayList<String> scoreList = new ArrayList<>();
    boolean buttonValue;

    public static String userScore, actualScore;

    Stage settingsStage = new Stage(), statisticsStage = new Stage();
    boolean settingsMod = true, statisticsMod = true;

    static Hand hand;

    //Runnable thread used to handle the countdown timer.
    Runnable runnable = () -> {
        for (double i = time * 10; i >= 0; i--) {
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
            System.out.print("");
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

        public void initialize () {
            buttonValue = true;
            handInit();
        }

        //flips the cards over, starts the timer, checks to see if the user typed the correct score in.
        //flips the cards over, starts the timer, checks to see if the user typed the correct score in.
        //button is used as a 'Flip Cards' button and a 'Play Again' button toggled by the boolean button value
        public void handleFlipCardsButtonPressed () {

            // buttonValue true = flipCards
            // buttonValue false = playAgain

            //Flip Cards Button
            if (buttonValue) {

                //Hides the button once the cards are flipped, you cannot flip the cards again
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
            } else if (flipCardsButton.getOpacity() != 0.0) {

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

        //checks the score when the button is pressed and proceeds accordingly
        //this can also be triggered by pressing the enter button(hopefully)
        public void handleGuessButtonPressed () throws IOException {
            //logs the time and stops running threads
            double time = 10.0 - Double.parseDouble(timerText.getText());
            double totalTime = 0;
            totalTime += time;
            timer.stop();
            checkTimeThread.stop();

            userScore = scoreTextBox.getText();
            actualScore = String.valueOf(score);
            System.out.printf("User Score: %s, Actual Score: %s\n", userScore, actualScore); //debug

            ShowScoreSceneController sssController = new ShowScoreSceneController();
            guessButton.getScene().setRoot(sssController.getContent());

            //makes play again button visible now that the game is over
            flipCardsButton.setOpacity(100.0);


            //Statistics
            handsScored++;
            percentCorrect = (handsCorrect / handsScored) * 100;
            averageTime = Math.round(((totalTime) / handsScored) * 100.0) / 100.0;
            if (time < bestTime) bestTime = Math.round(time * 100.0) / 100.0;
        }

        //does the same thing as the guess button
        public void handleEnterKeyTyped (KeyEvent event) throws IOException {
            if(event.getCode().equals(KeyCode.ENTER)) {
                handleGuessButtonPressed();
            }
        }

        //creates a hand to be scored
        public void handInit () {
            scoreList.removeAll(scoreList);
            Game game = new Game();
            game.createDeck();
            game.createHand();
            hand = game.h;
            score = hand.getScore();
            for (String s : hand._scores)
                if (s != null)
                    scoreList.add(s);
        }

        public void sleep ( int milliseconds){
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public Parent getContent () throws IOException {
            return FXMLLoader.load(getClass().getResource("MainGameScene.fxml"));
        }

        public void handleSettingsPressed () throws IOException {
            Scene settingsScene = new Scene(FXMLLoader.load(getClass().getResource("SettingsScene.fxml")));
            settingsStage.setScene(settingsScene);
            settingsCheckBox.setSelected(settingsShowScore);
            if(settingsMod) {
                settingsStage.initModality(Modality.APPLICATION_MODAL);
                settingsMod = false;
            }
            settingsStage.showAndWait();
        }

        public void ExitGame () {
            System.exit(0);
        }

        public void HandleMenuStatisticsPressed () throws IOException {
            Scene statisticsScene = new Scene(FXMLLoader.load(getClass().getResource("StatisticsScene.fxml")));
            statisticsStage.setScene(statisticsScene);
            if(statisticsMod) {
                statisticsStage.initModality(Modality.APPLICATION_MODAL);
                statisticsMod = false;
            }
            statisticsStage.showAndWait();
        }

        public void handleApplyButtonPressed() {

            try {
                if(Double.parseDouble(settingsTextBox.getText()) > 0)
                    time = Double.parseDouble(settingsTextBox.getText());
            } catch(Exception ex) {
                System.out.println(ex);
            }
            System.out.println(settingsCheckBox.isSelected());
            settingsShowScore = settingsCheckBox.isSelected();
            settingsCheckBox.setSelected(settingsShowScore);
            settingsTextBox.setText("");
        }

}
