package com.claireg.demineur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsController {

    @FXML
    private TextField newRowsNumber;

    @FXML
    private TextField newColsNumber;

    @FXML
    private TextField newMinesNumber;

    @FXML
    private void initialize() {
        newRowsNumber.setText(String.valueOf(GameSettings.getRowsNumber()));
        newColsNumber.setText(String.valueOf(GameSettings.getColsNumber()));
        newMinesNumber.setText(String.valueOf(GameSettings.getMinesNumber()));
    }

    @FXML
    private void goToMenu() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void putDefaultParams() throws IOException {
        GameSettings.setRowsNumber(10);
        GameSettings.setColsNumber(10);
        GameSettings.setMinesNumber(15);

        newRowsNumber.setText(String.valueOf(GameSettings.getRowsNumber()));
        newColsNumber.setText(String.valueOf(GameSettings.getColsNumber()));
        newMinesNumber.setText(String.valueOf(GameSettings.getMinesNumber()));
    }

    @FXML
    private void saveParameters() throws IOException {
        int rows = Integer.parseInt(newRowsNumber.getText());
        int cols = Integer.parseInt(newColsNumber.getText());
        int mines = Integer.parseInt(newMinesNumber.getText());

        GameSettings.setRowsNumber(rows);
        GameSettings.setColsNumber(cols);
        GameSettings.setMinesNumber(mines);
    }
}
