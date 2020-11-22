package edu.wit.comp1050;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleSceneController {

    @FXML
    public Button playGameButton;

    public void handlePlayButtonPressed() throws IOException {
        playGameButton.setStyle("-fx-background-color: #adadad; ");
        MainGameSceneController mgsController = new MainGameSceneController();
        playGameButton.getScene().setRoot(mgsController.getContent());
    }

}
