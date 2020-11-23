package edu.wit.comp1050;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class StatisticsController {

    @FXML
    public Text menuHandsScored = new Text("");
    @FXML
    public Text menuPercentCorrect = new Text("");
    @FXML
    public Text menuAverageTime = new Text("");
    @FXML
    public Text menuBestTime = new Text("");

    public void initialize() {
        menuHandsScored.setText("Hands scored: " + MainGameSceneController.handsScored);
        menuPercentCorrect.setText("Percent of hands scored correctly: " + MainGameSceneController.percentCorrect + "%");
        menuAverageTime.setText("Average Time: " + MainGameSceneController.averageTime + " seconds");
        if(MainGameSceneController.bestTime != 11.0)
            menuBestTime.setText("Best Time: " + MainGameSceneController.bestTime + " seconds");
        else
            menuBestTime.setText("Best Time: 0.0 seconds");
    }

}
