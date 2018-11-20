package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.ProfileActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.ScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.manager.BoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.view.PersonalScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.TileSettingsActivity;
import fall2018.csc2017.slidingtiles.system.UserPanel;
/**
 * The initial activity for the 2048 game.
 */

public class StartingActivityTF extends AppCompatActivity {

    /**
     * A temporary save file.
     */
    public static final String TEMP_SAVE_FILENAME = "save_file_tmp.ser";

    /**
     * The board manager.
     */
    private BoardManager boardManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardManager = LoginActivity.userBoardHashMap.get(UserPanel.getInstance().getName());

        setContentView(R.layout.activity_starting_tf);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
        addScoreboardpersonalButtonListener();
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
                switchToGame();
            }
        });
    }

    /**
     * Activate the load button.
     */
    private void addLoadButtonListener() {
        Button loadButton = findViewById(R.id.LoadButton);
        final StartingActivityTF StartingActivityTF = this;
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boardManager = LoginActivity.userBoardHashMap.get(UserPanel.getInstance().getName());
                if (boardManager != null) {
//                    Board.numRows = boardManager.boardNumOfRows;
//                    Board.numCols = boardManager.boardNumOfCols;
                    LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
                    ActivityHelper.saveToFile(TEMP_SAVE_FILENAME, StartingActivityTF, LoginActivity.userBoardHashMap);
                    makeToastLoadedText();
                    switchToGame();
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
        final StartingActivityTF StartingActivityTF = this;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boardManager != null) {
                    ActivityHelper.saveToFile(TEMP_SAVE_FILENAME, StartingActivityTF, LoginActivity.userBoardHashMap);
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

    private void addScoreboardpersonalButtonListener() {
        Button startButton = findViewById(R.id.button_personal_record);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPersonalBoard();
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
        final StartingActivityTF StartingActivityTF = this;
        Intent tmp = new Intent(this, GameActivityTF.class);
        ActivityHelper.saveToFile(TEMP_SAVE_FILENAME,StartingActivityTF, LoginActivity.userBoardHashMap);
        startActivity(tmp);
    }
    /**
     * Switch to the TileSettingsActivity Activity view to customize game settings.
     */
    public void switchToSettings(){
        Intent tmp = new Intent(this, TileSettingsActivity.class);
        startActivity(tmp);
    }
    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        startActivity(tmp);
    }
    /**
     * Switch to the PersonalScoreBoardActivity  view to see local (personal) scoreboard.
     */
    public void switchToPersonalBoard() {
        Intent tmp = new Intent(this, PersonalScoreBoardActivity.class);
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
