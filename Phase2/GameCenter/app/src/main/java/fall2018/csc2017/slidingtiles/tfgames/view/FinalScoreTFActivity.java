package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicFinalScoreActivity;
import fall2018.csc2017.slidingtiles.GameCenterActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.slidinggames.view.PersonalScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.ScoreBoardActivity;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.tfgames.controller.BoardManagerTF;
/**
 * Final Score Activity for 2048.
 */
public class FinalScoreTFActivity extends BasicFinalScoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setBM() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        currentBM = sys.get(UserPanel.getInstance().getName());
    }

    @Override
    /**
     * Switch to the PersonalScoreBoardActivity  view to see local (personal) scoreboard.
     */
    public void switchToPersonalBoard() {
        Toast.makeText(this, "this is exclusive for slidingtile game", Toast.LENGTH_LONG).show();
    }

    @Override
    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreboardtfActivity.class);
        startActivity(tmp);
    }
}
