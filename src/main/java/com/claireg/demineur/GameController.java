package com.claireg.demineur;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 *
 * @author claire.gobert
 */
public class GameController {

    private int rowsNumber;
    private int colsNumber;
    private int minesNumber;
    private int visualMinesRemained;
    private boolean[][] mines;
    private boolean[][] flags;
    private boolean[][] casesRevealed;

    @FXML
    private Label remainedMines;

    @FXML
    private GridPane gameGrid;

    @FXML
    private void initialize() {
        rowsNumber = GameSettings.getRowsNumber();
        colsNumber = GameSettings.getColsNumber();
        minesNumber = GameSettings.getMinesNumber();
        visualMinesRemained = minesNumber;

        remainedMines.setText(String.valueOf(minesNumber));
        mines = new boolean[rowsNumber][colsNumber];
        flags = new boolean[rowsNumber][colsNumber];
        casesRevealed = new boolean[rowsNumber][colsNumber];

        generateMinesPositions();

        for (int i = 0; i < rowsNumber; i++) {
            for (int j = 0; j < colsNumber; j++) {
                Button caseButton = new Button();
                caseButton.setMinSize(30, 30);
                caseButton.setMaxSize(30, 30);
                final int x = i;
                final int y = j;
                caseButton.setOnMouseClicked(event -> handleCaseClick(event, x, y));
                gameGrid.add(caseButton, j, i);
            }
        }
    }

    private void generateMinesPositions() {
        Random random = new Random();
        int placedMines = 0;

        while (placedMines < minesNumber) {
            int x = random.nextInt(rowsNumber);
            int y = random.nextInt(colsNumber);

            if (!mines[x][y]) {
                mines[x][y] = true;
                placedMines++;
            }
        }
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rowsNumber && j >= 0 && j < colsNumber && mines[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    private void revealeCasesAround(int x, int y) {
        if (x < 0 || x >= rowsNumber || y < 0 || y >= colsNumber || casesRevealed[x][y]) {
            return;
        }

        casesRevealed[x][y] = true;
        Button button = (Button) gameGrid.getChildren().get((x * colsNumber + y) + 1);
        button.setStyle("-fx-background-color: lightgrey;");

        int adjacentMines = countAdjacentMines(x, y);
        if (adjacentMines > 0) {
            button.setText(String.valueOf(adjacentMines));
        } else if (adjacentMines == 0 && !mines[x][y]) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (i >= 0 && i < rowsNumber && j >= 0 && j < colsNumber) {
                        revealeCasesAround(i, j); // Révéler récursivement les cases adjacentes
                    }
                }
            }
        }
    }

    @FXML
    private void goToMenu() throws IOException {
        App.setRoot("menu");
    }

    private void goToEndGame(String text) throws IOException {
        GameSettings.setEndGameMessage(text);
        App.setRoot("endGame");
    }

    private void handleCaseClick(MouseEvent event, int x, int y) {
        if (event.getButton() == MouseButton.PRIMARY) { // Gestion actions clique gauche
            if (!flags[x][y]) {
                if (mines[x][y]) {
                    System.out.println("Game over");
                    try {
                        goToEndGame("Game over");
                    } catch (IOException ex) {
                        System.err.println("Can't go to game over view");
                    }
                } else {
                    revealeCasesAround(x, y);
                    if (checkIfWin()) {
                        System.out.println("Victoire");
                        try {
                            goToEndGame("Victoire");
                        } catch (IOException e) {
                            System.err.println("Can't go to victory view");
                        }
                    } else {
                        System.out.println("Case sûre : " + x + ", " + y);
                    }
                }
            }
        } else if (event.getButton() == MouseButton.SECONDARY) { // Gestion actions clique droit
            Button button = (Button) gameGrid.getChildren().get((x * colsNumber + y) + 1);

            if (flags[x][y]) {
                flags[x][y] = false;
                visualMinesRemained++;
                remainedMines.setText(String.valueOf(visualMinesRemained));
                System.out.println("Drapeau retiré sur : " + x + ", " + y);

                button.setGraphic(null);
            } else {
                flags[x][y] = true;
                visualMinesRemained--;
                remainedMines.setText(String.valueOf(visualMinesRemained));
                System.out.println("Drapeau placé sur : " + x + ", " + y);

                Image img = new Image(getClass().getResourceAsStream("/com/claireg/demineur/images/flag.png"));
                ImageView imgView = new ImageView(img);
                imgView.setFitHeight(30);
                imgView.setFitWidth(30);
                imgView.setPreserveRatio(true);
                button.setGraphic(imgView);

                if (checkIfWin()) {
                    System.out.println("Victoire");
                    try {
                        goToEndGame("Victoire");
                    } catch (IOException e) {
                        System.err.println("Can't go to victory view");
                    }
                }
            }
        }
    }

    private boolean checkIfWin() {
        for (int x = 0; x < rowsNumber; x++) {
            for (int y = 0; y < colsNumber; y++) {
                if (!mines[x][y] && !casesRevealed[x][y]) {
                    return false;
                }
                if (mines[x][y] && !flags[x][y]) {
                    return false;
                }
            }
        }

        return true;
    }
}
