package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicStartingActivity;
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
 * The initial activity for the 2048 game, from which user can start a game or view the scoreBoard.
 */
public class StartingActivityTF extends BasicStartingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setView() {
        setContentView(R.layout.activity_starting_tf);
    }

    /**
     * Switch to the GameActivity view to play the game.
     */
    @Override
    public void switchToGame() {
        Intent tmp = new Intent(this, GameActivityTF.class);
        startActivity(tmp);
    }

    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    @Override
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreboardtfActivity.class);
        startActivity(tmp);
    }
}
