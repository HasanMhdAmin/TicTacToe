package amin.mhd.hasan.tictactoe.controller.initialGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import amin.mhd.hasan.tictactoe.R;
import amin.mhd.hasan.tictactoe.callback.ChangeNameListener;
import amin.mhd.hasan.tictactoe.controller.gameController.GameActivity;
import amin.mhd.hasan.tictactoe.dialog.ChangeNameDialog;
import amin.mhd.hasan.tictactoe.utils.ScreenUtils;
import amin.mhd.hasan.tictactoe.utils.StorageUtils;

public class InitActivity extends AppCompatActivity implements View.OnClickListener,
        ChangeNameListener {

    private static final String TAG = "InitActivity";
    private TextView welcome;
    private Button startGame;
    private Button changeName;
    private AppCompatCheckBox darkModeCheckBox;


    private void findViews() {
        welcome = findViewById(R.id.welcome);
        startGame = findViewById(R.id.startGame);
        changeName = findViewById(R.id.changeName);
        darkModeCheckBox = findViewById(R.id.darkModeCheckBox);

        startGame.setOnClickListener(this);
        changeName.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String theme = StorageUtils.getTheme(this);
        if (theme.equals(StorageUtils.LIGHT))
            setTheme(R.style.AppTheme_LIGHT);
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        ScreenUtils.setStatusBarTranslucent(this, true);

        findViews();

        String playerName = StorageUtils.getPlayerName(this);
        String welcomeMessage = getString(R.string.hi) + ", " + playerName;
        welcome.setText(welcomeMessage);

        if (theme.equals(StorageUtils.LIGHT))
            darkModeCheckBox.setChecked(false);
        else
            darkModeCheckBox.setChecked(true);

        darkModeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    StorageUtils.setTheme(InitActivity.this, StorageUtils.DARK);
                } else {
                    StorageUtils.setTheme(InitActivity.this, StorageUtils.LIGHT);
                }
                finish();
                startActivity(getIntent());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == startGame) {
            Intent gameActivity = new Intent(this, GameActivity.class);
            startActivity(gameActivity);
        } else if (v == changeName) {
            ChangeNameDialog changeNameDialog = ChangeNameDialog.newInstance(StorageUtils.getPlayerName(this));
            changeNameDialog.show(getSupportFragmentManager(), TAG);
        }
    }

    @Override
    public void onChangeName(String newName) {
        String welcomeString = getString(R.string.hi) + ", " + newName;
        welcome.setText(welcomeString);
        StorageUtils.setPlayerName(this, newName);
    }
}
