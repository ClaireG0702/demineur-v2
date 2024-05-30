package com.claireg.demineur;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SettingsController {

    private final int MAX_SIZE = 15;

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

        newRowsNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (value > MAX_SIZE) {
                    newRowsNumber.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                newRowsNumber.setText(oldValue);
            }
        });

        newColsNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (value > MAX_SIZE) {
                    newColsNumber.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                newColsNumber.setText(oldValue);
            }
        });

        newMinesNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                int max_mines = (Integer.parseInt(newRowsNumber.getText()) * Integer.parseInt(newColsNumber.getText())) - 5;
                if (value > max_mines) {
                    newMinesNumber.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                newMinesNumber.setText(oldValue);
            }
        });
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
