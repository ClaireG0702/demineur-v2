package com.claireg.demineur;

import java.io.IOException;

import javafx.fxml.FXML;

public class MenuController {

    @FXML
    private void launchNewGame() throws IOException {
        App.setRoot("game");
    }

    @FXML
    private void resumeGame() throws IOException {
        // TODO
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
