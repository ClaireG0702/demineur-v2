package com.claireg.demineur;

public class GameSettings {

    private static int rowsNumber = 10;
    private static int colsNumber = 10;
    private static int minesNumber = 15;
    private static String endGameMessage = "Victory";

    public static int getRowsNumber() {
        return rowsNumber;
    }

    public static void setRowsNumber(int rowsNumber) {
        GameSettings.rowsNumber = rowsNumber;
    }

    public static int getColsNumber() {
        return colsNumber;
    }

    public static void setColsNumber(int colsNumber) {
        GameSettings.colsNumber = colsNumber;
    }

    public static int getMinesNumber() {
        return minesNumber;
    }

    public static void setMinesNumber(int minesNumber) {
        GameSettings.minesNumber = minesNumber;
    }

    public static String getEndGameMessage() {
        return endGameMessage;
    }

    public static void setEndGameMessage(String endGameMessage) {
        GameSettings.endGameMessage = endGameMessage;
    }
}
