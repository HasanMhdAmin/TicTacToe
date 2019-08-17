package amin.mhd.hasan.tictactoe.controller.endController;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.controller.endController.enums.GameResult;
import amin.mhd.hasan.tictactoe.controller.gameController.GameActivity;
import amin.mhd.hasan.tictactoe.utils.DateUtils;
import amin.mhd.hasan.tictactoe.utils.ScreenUtils;

import static amin.mhd.hasan.tictactoe.controller.endController.enums.Keys.GAME_RESULT;
import static amin.mhd.hasan.tictactoe.controller.endController.enums.Keys.TIME_OF_THINKING;

public class EndActivity extends AppCompatActivity implements View.OnClickListener {

    private String gameResult;
    private long durationOfThinking;

    private TextView title;
    private TextView result;
    private TextView timeOfThinking;
    private Button startGame;

    private void findViews() {
        title = findViewById(R.id.title);
        result = findViewById(R.id.result);
        timeOfThinking = findViewById(R.id.timeOfThinking);
        startGame = findViewById(R.id.startGame);

        startGame.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        ScreenUtils.setStatusBarTranslucent(this, true);

        findViews();

        if (getIntent().hasExtra(GAME_RESULT)) {
            gameResult = getIntent().getStringExtra(GAME_RESULT);
        }

        if (getIntent().hasExtra(TIME_OF_THINKING)) {
            durationOfThinking = getIntent().getLongExtra(TIME_OF_THINKING, 0);
        }

        bindData();

    }

    private void bindData() {
        switch (gameResult) {
            case GameResult.WON:  // the user won
                title.setText(getString(R.string.congratulations));
                result.setText(getString(R.string.you_won));
                break;
            case GameResult.LOSE:  // the user lose
                title.setText(getString(R.string.oops));
                result.setText(getString(R.string.you_lose));
                break;
            case GameResult.DRAW:  // it is a tie
                title.setText(getString(R.string.draw));
                result.setText(getString(R.string.it_is_a_tie));
                break;
        }
        String readableTimeDuration = getString(R.string.time_of_thinking) + "  "
                + DateUtils.convertTimestampToReadableDuration(durationOfThinking);

        timeOfThinking.setText(readableTimeDuration);

    }


    @Override
    public void onClick(View v) {
        if (v == startGame) {
            Intent gameActivity = new Intent(this, GameActivity.class);
            startActivity(gameActivity);
            finish();
        }
    }

}
