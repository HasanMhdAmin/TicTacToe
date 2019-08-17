package amin.mhd.hasan.tictactoe.controller.gameController;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.callback.OnAiMakeDecisionListener;
import amin.mhd.hasan.tictactoe.callback.OnTileClickListener;
import amin.mhd.hasan.tictactoe.controller.endController.EndActivity;
import amin.mhd.hasan.tictactoe.controller.endController.enums.GameResult;
import amin.mhd.hasan.tictactoe.customView.TileView;
import amin.mhd.hasan.tictactoe.dialog.CustomProgressDialog;
import amin.mhd.hasan.tictactoe.model.Player;
import amin.mhd.hasan.tictactoe.utils.StorageUtils;
import amin.mhd.hasan.tictactoe.utils.StringUtils;

import static amin.mhd.hasan.tictactoe.controller.endController.enums.Keys.GAME_RESULT;
import static amin.mhd.hasan.tictactoe.controller.endController.enums.Keys.TIME_OF_THINKING;

public class GameActivity extends AppCompatActivity implements OnTileClickListener, OnAiMakeDecisionListener {

    private static final String TAG = "GameActivity";

    ConstraintLayout root;
    TextView gameTitle;
    TextView playerNameTextView;
    List<TileView> tileViews = new ArrayList<>();
    Player currentPlayer;
    Player me;
    Player computer;
    long timeOfThinking = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        findViews();

        createPlayers();

        startGame();

    }

    private void startGame() {


        // AI player
        playerNameTextView.setText(computer.getPlayerName());
        currentPlayer = computer;
        computerNextStep();
    }

    private void computerNextStep() {
        Log.d(TAG, "computerNextStep: ");
        CustomProgressDialog customProgressDialog = CustomProgressDialog.newInstance(me.getSelectedTiles(), computer.getSelectedTiles());
        customProgressDialog.show(getSupportFragmentManager(), TAG);
    }

    private void createPlayers() {
        Log.d(TAG, "createPlayers: ");
        me = new Player(StorageUtils.getPlayerName(this));
        computer = new Player(getString(R.string.player_2_name));
    }

    private void findViews() {
        root = findViewById(R.id.root);
        gameTitle = findViewById(R.id.gameTitle);
        playerNameTextView = findViewById(R.id.playerNameTextView);
        setOnClickListenerForAllTileViews(root);

        Spannable result = StringUtils.spannableString(gameTitle.getText(), "AI",
                true, true, "#ffffff");
        gameTitle.setText(result);
    }

    private void setOnClickListenerForAllTileViews(View view) {
        if ((view instanceof TileView)) {
            ((TileView) view).setOnTileClickListener(this);
            tileViews.add((TileView) view);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setOnClickListenerForAllTileViews(innerView);
            }
        }
    }


    @Override
    public void onSelectValue(TileView tileView, ImageView imageView, int value) {
        Log.d(TAG, "onSelectValue: value: " + value);
        Log.d(TAG, "onSelectValue: currentPlayer: " + currentPlayer.getPlayerName());
        if (imageView.getDrawable() == null) {
            Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            imageView.startAnimation(animFadeIn);
            if (currentPlayer == computer) {
                computer.getSelectedTiles().add(value);
                imageView.setImageResource(R.drawable.ic_o);
            } else if (currentPlayer == me) {
                me.getSelectedTiles().add(value);
                imageView.setImageResource(R.drawable.ic_x);
            }
        }
        if (TicTacToeUtils.isGameEnded(currentPlayer)) {
            String gameResult;
            if (currentPlayer == me)
                gameResult = GameResult.WON;
            else
                gameResult = GameResult.LOSE;
            Intent endActivity = new Intent(this, EndActivity.class);
            endActivity.putExtra(GAME_RESULT, gameResult);
            endActivity.putExtra(TIME_OF_THINKING, timeOfThinking);
            startActivity(endActivity);
        } else
            switchTurn();
    }


    private void switchTurn() {
        int numberOfTakenPlaces = me.getSelectedTiles().size() + computer.getSelectedTiles().size();
        if (numberOfTakenPlaces == 9) {
            Intent endActivity = new Intent(this, EndActivity.class);
            endActivity.putExtra(GAME_RESULT, GameResult.DRAW);
            endActivity.putExtra(TIME_OF_THINKING, timeOfThinking);
            startActivity(endActivity);
            finish();
            return;
        }
        Log.d(TAG, "switchTurn: ");
        int color = ContextCompat.getColor(this, R.color.player1);
        if (currentPlayer == computer) {
            currentPlayer = me;
            color = ContextCompat.getColor(this, R.color.player1);
        } else if (currentPlayer == me) {
            currentPlayer = computer;
            color = ContextCompat.getColor(this, R.color.player2);
            computerNextStep();
        }

        playerNameTextView.setText(currentPlayer.getPlayerName());
        playerNameTextView.setTextColor(color);
    }

    @Override
    public void onDecisionMaking(int value) {
        // get the index of the tile
        TileView tileView = tileViews.get(value - 1);
        tileView.click();
    }
}
