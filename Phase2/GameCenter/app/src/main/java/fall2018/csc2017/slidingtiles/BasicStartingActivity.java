package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;

public class BasicStartingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setView();
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
        addProfileImageButtonListener();
    }

    public void setView() {
        System.out.println("override this before set your view");
    }

    /**
     * Activate Profile Image button, which goes to profile page
     */
    public void addProfileImageButtonListener() {
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
    public void addStartButtonListener() {
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
    public void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BasicBoardManager boardManager = GameCacheSystem.getInstance().get(UserPanel.getInstance().getName());
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
    public void addSaveButtonListener() {
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
    public void addScoreboardButtonListener() {
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
    }

    /**
     * Switch to the Profile Activity  view.
     */
    public void switchToProfile() {
        Intent tmp = new Intent(this, ProfileActivity.class);
        startActivity(tmp);
    }
}
