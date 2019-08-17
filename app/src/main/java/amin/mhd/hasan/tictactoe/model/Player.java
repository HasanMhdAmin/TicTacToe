package amin.mhd.hasan.tictactoe.model;

import java.util.ArrayList;

/**
 * Created by Hasan Mhd Amin on 8/17/2019.
 */
public class Player {
    private String playerName;
    private ArrayList<Integer> selectedTiles = new ArrayList<>();

    public Player(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Integer> getSelectedTiles() {
        return selectedTiles;
    }

    public void setSelectedTiles(ArrayList<Integer> selectedTiles) {
        this.selectedTiles = selectedTiles;
    }
}
