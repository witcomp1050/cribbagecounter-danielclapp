package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class ShowScoreSceneController {

    @FXML
    public Text text1;
    @FXML
    public Button playAgainButton;
    @FXML
    public VBox contents;

//    public ShowScoreSceneController(String s1, String s2) {
//        _userScore = s1;
//        _actualScore = s2;
//    }

    public void initialize() {
        _userScore = MainGameSceneController.userScore;
        _actualScore = MainGameSceneController.actualScore;
        setText1();
        setVBoxContents();
    }

    void setText1() {
        if(_userScore.equals("19"))
            text1.setText("Thats impossible you fool . . .");
        else if(_userScore.equals(_actualScore))
            text1.setText("You guessed it! the score was " + _actualScore);
        else
            text1.setText("Wrong! the score was " + _actualScore + "\n Better luck next time . . .");
    }

    public void setVBoxContents() {
        contents.getChildren().add(new ImageView(new Image("edu/wit/comp1050/CardImages/JPEG/aces.jpg")));
    }

    public void handlePlayAgainButtonPressed() throws IOException {
        MainGameSceneController mgsController = new MainGameSceneController();
        playAgainButton.getScene().setRoot(mgsController.getContent());
    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("ShowScoreScene.fxml"));
    }

    static String _userScore, _actualScore;
}
