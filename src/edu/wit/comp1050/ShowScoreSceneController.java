package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.ArrayList;

public class ShowScoreSceneController {

    @FXML
    public Button playAgainButton;
    @FXML
    public Text text1;
    @FXML
    public HBox hBox1;
    @FXML
    public HBox hBox2;
    @FXML
    public HBox hBox3;
    @FXML
    public HBox hBox4;
    @FXML
    public HBox hBox5;
    @FXML
    public HBox hBox6;
    @FXML
    public HBox hBox7;
    @FXML
    public HBox hBox8;
    @FXML
    public HBox hBox9;
    @FXML
    public HBox hBox10;
    @FXML
    public HBox hBox11;
    @FXML
    public HBox hBox12;

    ArrayList<String> scoreList = MainGameSceneController.scoreList;
    ArrayList<ArrayList<ImageView>> images = new ArrayList<>();
    String[] temp = new String[2];
    String[] tempImages = new String[5];
    String[] handCards = new String[5];
    Hand hand = MainGameSceneController.hand;


    public void initialize() {
        _userScore = MainGameSceneController.userScore;
        _actualScore = MainGameSceneController.actualScore;
        setText1();
    }

    void showScore() {
        for(int i = 0; i < 12; i++){
            images.add(new ArrayList<>());
        }

        handCards = hand.toString().split(",");
        for(String s : handCards) {
            hBox1.setSpacing(20);
            ImageView iv = new ImageView("edu/wit/comp1050/CardImages/JPEG/" + s + ".jpg");
            iv.setFitHeight(132);
            iv.setFitWidth(86.375);
            hBox1.getChildren().add(iv);
        }

        int i = 1;
        for(String s : scoreList) {
            temp = s.split(":");

            Text scoreText = new Text(temp[0] + ": ");
            Font font = new Font("System Bold", 24);
            scoreText.setFont(font);

            tempImages = temp[1].split(",");
            for(String t : tempImages) {
                images.get(i).add(new ImageView("edu/wit/comp1050/CardImages/JPEG/" + t + ".jpg"));
            }



            switch(i) {

                case 1:
                    hBox2.getChildren().add(scoreText);
                    hBox2.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox2.getChildren().add(iv);
                    }
                    break;
                case 2:
                    hBox3.getChildren().add(scoreText);
                    hBox3.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox3.getChildren().add(iv);
                    }
                    break;
                case(3):
                    hBox4.getChildren().add(scoreText);
                    hBox4.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox4.getChildren().add(iv);
                    }
                    break;
                case(4):
                    hBox5.getChildren().add(scoreText);
                    hBox5.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox5.getChildren().add(iv);
                    }
                    break;
                case(5):
                    hBox6.getChildren().add(scoreText);
                    hBox6.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox6.getChildren().add(iv);
                    }
                    break;
                case(6):
                    hBox7.getChildren().add(scoreText);
                    hBox7.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox7.getChildren().add(iv);
                    }
                    break;
                case(7):
                    hBox8.getChildren().add(scoreText);
                    hBox8.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox8.getChildren().add(iv);
                    }
                    break;
                case(8):
                    hBox9.getChildren().add(scoreText);
                    hBox9.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox9.getChildren().add(iv);
                    }
                    break;
                case(9):
                    hBox10.getChildren().add(scoreText);
                    hBox10.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox10.getChildren().add(iv);
                    }
                    break;
                case(10):
                    hBox11.getChildren().add(scoreText);
                    hBox11.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox11.getChildren().add(iv);
                    }
                    break;
                case(11):
                    hBox12.getChildren().add(scoreText);
                    hBox12.setSpacing(20);
                    for(ImageView iv : images.get(i)) {
                        iv.setFitHeight(132);
                        iv.setFitWidth(86.375);
                        hBox12.getChildren().add(iv);
                    }
                    break;
            }
            i++;
        }
        images.removeAll(images);
    }

    void setText1() {
        if(_userScore.equals("19"))
            text1.setText("Thats impossible you fool . . .");
        else if(_userScore.equals(_actualScore)) {
            text1.setText("You guessed it! the score was " + _actualScore);
            MainGameSceneController.handsCorrect++;
        } else {
            text1.setText("Wrong! the score was " + _actualScore + "\n Better luck next time . . .");
            showScore();
        }
        System.out.println(MainGameSceneController.handsCorrect);
    }



    public void handlePlayAgainButtonPressed() throws IOException {

        hBox1.getChildren().removeAll();
        hBox2.getChildren().removeAll();
        hBox3.getChildren().removeAll();
        hBox4.getChildren().removeAll();
        hBox5.getChildren().removeAll();
        hBox6.getChildren().removeAll();
        hBox7.getChildren().removeAll();
        hBox8.getChildren().removeAll();
        hBox9.getChildren().removeAll();
        hBox10.getChildren().removeAll();
        hBox11.getChildren().removeAll();
        hBox12.getChildren().removeAll();

        MainGameSceneController mgsController = new MainGameSceneController();
        playAgainButton.getScene().setRoot(mgsController.getContent());
    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("ShowScoreScene.fxml"));
    }

    static String _userScore, _actualScore;
}
