package com.claireg.demineur;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController {

    private final String FILE_NAME = "game-infos.txt";

    @FXML
    private Button resumeGameButton;

    @FXML
    private void initialize() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            resumeGameButton.setDisable(false);
        } else {
            resumeGameButton.setDisable(true);
        }
    }

    @FXML
    private void launchNewGame() throws IOException {
        GameSettings.setResumeGame(false);
        App.setRoot("game");
    }

    @FXML
    private void resumeGame() throws IOException {
        GameSettings.setResumeGame(true);
        App.setRoot("game");
    }

    @FXML
    private void openSettings() throws IOException {
        App.setRoot("settings");
    }

    @FXML
    private void quitApp() throws IOException {
        System.exit(0);
    }
}
