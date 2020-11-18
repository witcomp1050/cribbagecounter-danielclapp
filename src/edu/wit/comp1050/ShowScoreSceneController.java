package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class ShowScoreSceneController {

    @FXML
    public Text text1;
    @FXML
    public Button playAgainButton;

//    public ShowScoreSceneController(String s1, String s2) {
//        _userScore = s1;
//        _actualScore = s2;
//    }

    public void initialize() {
        setText1();
    }

    void setText1() {
        if(_userScore.equals("19"))
            text1.setText("Thats impossible you fool . . .");
        else if(_userScore.equals(_actualScore))
            text1.setText("You guessed it! the score was " + _actualScore);
        else
            text1.setText("Wrong! the score was " + _actualScore + "\n Better luck next time . . .");
    }

    public Parent getContent() throws IOException {
        return FXMLLoader.load(getClass().getResource("ShowScoreScene.fxml"));
    }

    public void handlePlayAgainButtonPressed() throws IOException {
        MainGameSceneController mgsController = new MainGameSceneController();
        playAgainButton.getScene().setRoot(mgsController.getContent());
    }

    static String _userScore = MainGameSceneController.userScore,
                  _actualScore = MainGameSceneController.actualScore;
}
