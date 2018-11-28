package fall2018.csc2017.slidingtiles.sudokugames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicScoreBoardActivity;
import fall2018.csc2017.slidingtiles.BasicStartingActivity;
import fall2018.csc2017.slidingtiles.ProfileActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.slidinggames.view.PersonalScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.ScoreBoardActivity;
import fall2018.csc2017.slidingtiles.sudokugames.controller.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

public class StartingActivitySudoku extends BasicStartingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_sudoku);
        addStartButtonListener();
        addLoadButtonListener();
        addSaveButtonListener();
        addScoreboardButtonListener();
        addProfileImageButtonListener();
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_starting_tf);
    }

    @Override
    public void switchToGame() {
        Intent tmp = new Intent(this, GameActivitySudoku.class);
        GameCacheSystem.getInstance().save(getApplicationContext());
        startActivity(tmp);
    }

    @Override
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreBoardActivitySK.class);
        startActivity(tmp);
    }
}
