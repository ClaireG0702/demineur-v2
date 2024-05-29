package com.claireg.demineur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndGameController {

    @FXML
    private Label textEndGame;

    @FXML
    private void initialize() {
        textEndGame.setText(GameSettings.getEndGameMessage());
    }

    @FXML
    private void goToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void restartNewGame() throws IOException {
        App.setRoot("game");
    }
}
