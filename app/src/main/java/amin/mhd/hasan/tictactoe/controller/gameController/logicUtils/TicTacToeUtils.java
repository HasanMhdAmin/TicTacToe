package amin.mhd.hasan.tictactoe.controller.gameController.logicUtils;

import java.util.ArrayList;

import amin.mhd.hasan.tictactoe.model.Player;

/**
 * here we will implement the game rules
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class TicTacToeUtils {

    private static ArrayList<Integer> getFirstRow() {
        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        firstRow.add(2);
        firstRow.add(3);
        return firstRow;
    }

    private static ArrayList<Integer> getSecondRow() {
        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(4);
        secondRow.add(5);
        secondRow.add(6);
        return secondRow;
    }

    private static ArrayList<Integer> getThirdRow() {
        ArrayList<Integer> thirdRow = new ArrayList<>();
        thirdRow.add(7);
        thirdRow.add(8);
        thirdRow.add(9);
        return thirdRow;
    }

    private static ArrayList<Integer> getFirstColumn() {
        ArrayList<Integer> firstColumn = new ArrayList<>();
        firstColumn.add(1);
        firstColumn.add(4);
        firstColumn.add(7);
        return firstColumn;
    }

    private static ArrayList<Integer> getSecondColumn() {
        ArrayList<Integer> secondColumn = new ArrayList<>();
        secondColumn.add(2);
        secondColumn.add(5);
        secondColumn.add(8);
        return secondColumn;
    }

    private static ArrayList<Integer> getThirdColumn() {
        ArrayList<Integer> thirdColumn = new ArrayList<>();
        thirdColumn.add(3);
        thirdColumn.add(6);
        thirdColumn.add(9);
        return thirdColumn;
    }

    private static ArrayList<Integer> getDiagonal() {
        ArrayList<Integer> thirdColumn = new ArrayList<>();
        thirdColumn.add(1);
        thirdColumn.add(5);
        thirdColumn.add(9);
        return thirdColumn;
    }

    private static ArrayList<Integer> getAntiDiagonal() {
        ArrayList<Integer> thirdColumn = new ArrayList<>();
        thirdColumn.add(3);
        thirdColumn.add(5);
        thirdColumn.add(7);
        return thirdColumn;
    }

    public static boolean isGameEnded(Player currentPlayer) {
        ArrayList<Integer> testWon = currentPlayer.getSelectedTiles();
        return testWon.containsAll(TicTacToeUtils.getFirstRow())
                || testWon.containsAll(TicTacToeUtils.getSecondRow())
                || testWon.containsAll(TicTacToeUtils.getThirdRow())
                || testWon.containsAll(TicTacToeUtils.getFirstColumn())
                || testWon.containsAll(TicTacToeUtils.getSecondColumn())
                || testWon.containsAll(TicTacToeUtils.getThirdColumn())
                || testWon.containsAll(TicTacToeUtils.getDiagonal())
                || testWon.containsAll(TicTacToeUtils.getAntiDiagonal());
    }


}
