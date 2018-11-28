package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.ProfileActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.slidinggames.view.ScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.PersonalScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.TileSettingsActivity;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.tfgames.controller.BoardManagerTF;

/**
 * The initial activity for the 2048 game.
 */

public class StartingActivityTF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_tf);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
        addProfileImageButtonListener();
    }

    /**
     * Activate Profile Image button, which goes to profile page
     */
    private void addProfileImageButtonListener() {
        ImageButton pib = findViewById(R.id.ProfileImageButton);
        pib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToProfile();
            }
        });
    }
    /**
     * Activate Start button, which goes to settings page
     */
    private void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGame(true);
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BoardManagerTF boardManager = (BoardManagerTF) GameCacheSystem.getInstance().get(UserPanel.getInstance().getName());
                if (boardManager != null) {
                    makeToastLoadedText();
                    switchToGame(false);
                } else {
                    final TextView invalidView = findViewById(R.id.warningText);
                    ActivityHelper.disableButton(v, invalidView, "You don't have incomplete game undergoing!");
                }
            }
        });
    }

    /**
     * Display that a game was loaded successfully.
     */
    private void makeToastLoadedText() {
        Toast.makeText(this, "Loaded Game", Toast.LENGTH_SHORT).show();
    }

    /**
     * Activate the save button.
     */
    private void addSaveButtonListener() {
        Button saveButton = findViewById(R.id.SaveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GameCacheSystem.getInstance().get(UserPanel.getInstance().getName()) != null) {
                    GameCacheSystem.getInstance().save(getApplicationContext());
                    makeToastSavedText();
                } else {
                    final TextView invalidView = findViewById(R.id.warningText);
                    ActivityHelper.disableButton(v, invalidView, "You don't have incomplete game undergoing!");
                }
            }
        });
    }
    /**
     * Activate the scoreboard button.
     */
    private void addScoreboardButtonListener() {
        Button startButton = findViewById(R.id.button_scoreboard);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Display that a game was saved successfully.
     */
    private void makeToastSavedText() {
        Toast.makeText(this, "Game Saved", Toast.LENGTH_SHORT).show();
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    public void switchToGame() {
        GameCacheSystem cached = GameCacheSystem.getInstance();
        final StartingActivityTF StartingActivityTF = this;
        Intent tmp = new Intent(this, GameActivityTF.class);
        ActivityHelper.saveToFile(UserRouter.GAME_STORAGE_TF, StartingActivityTF, cached.getData());
        startActivity(tmp);
    }
    // a packed switch function to judge whether to order this game
    public void switchToGame(boolean isNew) {
        if (isNew) {
            GameCacheSystem.getInstance().update(UserPanel.getInstance().getName(), null);
        }
        switchToGame();
    }

    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreboardtfActivity.class);
        startActivity(tmp);
    }
    /**
     * Switch to the Profile Activity  view.
     */
    public void switchToProfile() {
        Intent tmp = new Intent(this, ProfileActivity.class);
        startActivity(tmp);
    }
}
