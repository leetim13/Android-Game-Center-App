package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.slidinggames.view.StartingActivity;
import fall2018.csc2017.slidingtiles.sudokugames.view.StartingActivitySudoku;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.tfgames.view.StartingActivityTF;

/**
 * The game activity.
 * @author Timothy Lee
 */
public class GameCenterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_center);
        addTilesGameListener();
        addTFTilesGameListener();
        addProfileListener();
        addSudokuGameListener();
    }
    /**
     * Activate the Sliding Tiles game image button.
     */
    private void addTilesGameListener() {
        ImageButton ib = findViewById(R.id.TilesGameImageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPanel();
            }
        });
    }
    /**
     * Activate the Sudoku game image button.
     */
    private void addSudokuGameListener() {
        ImageButton ib = findViewById(R.id.SudokuGameImageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToSudokuPanel();
            }
        });
    }
    /**
     * Activate the 2048 game image button.
     */
    private void addTFTilesGameListener() {
        ImageButton ib = findViewById(R.id.TFGameImageButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToTFPanel();
            }
        });
    }
    /**
     * Activate the my profile button.
     */
    private void addProfileListener() {
        Button ib = findViewById(R.id.MyProfileButton);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToProfile();
            }
        });
    }
    /**
     * Switch to Starting Activity of Tiles game.
     */
    private void switchToPanel() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        sys.loadGame(User.ST_GAME_INDEX_3, getApplicationContext());
        sys.load_index(this);
        Intent tmp = new Intent(this, StartingActivity.class);
        startActivity(tmp);
    }
    /**
     * Switch to Starting Activity of 2048 game.
     */
    private void switchToTFPanel() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        sys.loadGame(User.TF_GAME_INDEX, getApplicationContext());
        sys.load_index(this);
        Intent tmp = new Intent(this, StartingActivityTF.class);
        startActivity(tmp);
    }
    /**
     * Switch to Starting Activity of Sudoku game.
     */
    private void switchToSudokuPanel() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        sys.loadGame(User.SD_GAME_INDEX, getApplicationContext());
        sys.load_index(this);
        Intent tmp = new Intent(this, StartingActivitySudoku.class);
        startActivity(tmp);
    }

    /**
     * Switch to Profile Activity.
     */
    private void switchToProfile() {
        Intent tmp = new Intent(this, ProfileActivity.class);
        startActivity(tmp);
    }

}
