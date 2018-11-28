package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicStartingActivity;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.ProfileActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * The initial activity for the sliding puzzle tile game.
 */

public class StartingActivity extends BasicStartingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addStartButtonListener();
        addScoreboardpersonalButtonListener();
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_starting_);
    }

    /**
     * Activate Start button, which goes to settings page
     */
    @Override
    public void addStartButtonListener() {
        Button startButton = findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSettings();
            }
        });
    }

    /*
    * add the listener for personal ScoreBoard
    * */
    private void addScoreboardpersonalButtonListener() {
        Button startButton = findViewById(R.id.button_personal_record);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPersonalBoard();
            }
        });
    }


    @Override
    public void switchToGame() {
        Intent tmp = new Intent(this, GameActivity.class);
        GameCacheSystem.getInstance().save(this);
        startActivity(tmp);
    }

    /**
     * Switch to the TileSettingsActivity Activity view to customize game settings.
     */
    public void switchToSettings(){
        Intent tmp = new Intent(this, TileSettingsActivity.class);
        startActivity(tmp);
    }

    @Override
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
}
