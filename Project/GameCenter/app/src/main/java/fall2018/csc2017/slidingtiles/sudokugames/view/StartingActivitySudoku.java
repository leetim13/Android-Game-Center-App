package fall2018.csc2017.slidingtiles.sudokugames.view;

import android.content.Intent;
import android.os.Bundle;

import fall2018.csc2017.slidingtiles.BasicStartingActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;

/**
 * Excluded from tests because it's a view class.
 * The starting activity for sudoku game, from where user can choose to start game or view the
 * scoreBoard.
 */
public class StartingActivitySudoku extends BasicStartingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_starting_sudoku);
    }

    @Override
    public void switchToGame() {
        Intent tmp = new Intent(this, GameActivitySudoku.class);
        GameCacheSystem.getInstance().save(getApplicationContext());
        startActivity(tmp);
    }

    @Override
    public void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreBoardActivitySK.class);
        startActivity(tmp);
    }
}
