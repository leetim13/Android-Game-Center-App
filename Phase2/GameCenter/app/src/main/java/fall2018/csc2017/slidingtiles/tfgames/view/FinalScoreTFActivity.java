package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicFinalScoreActivity;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * Final Score Activity for 2048.
 */
public class FinalScoreTFActivity extends BasicFinalScoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    /**
     * To set the current boardManagerTF
     */
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
    public void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreboardtfActivity.class);
        startActivity(tmp);
    }
}
