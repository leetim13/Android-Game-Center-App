package fall2018.csc2017.slidingtiles.sudokugames.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.BasicFinalScoreActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * The view to show player their score after completed game.
 */
public class FinalScoreSKActivity extends BasicFinalScoreActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score_tf);
        setNum();
        addScoreboardButtonListener();
        addGameCenterButtonListener();
    }

    @Override
    /*
     * To set the currentBM (current sudoku boardManager)
     */
    public void setBM() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        currentBM = sys.get(UserPanel.getInstance().getName());
    }

    @Override
    /*
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    public void switchToScoreboard() {
        Intent tmp = new Intent(this, ScoreBoardActivitySK.class);
        startActivity(tmp);
    }

    @Override
    /*
     * Switch to the PersonalScoreBoardActivity  view to see local (personal) scoreboard.
     */
    public void switchToPersonalBoard() {
        Toast.makeText(this, "this is exclusive for slidingtile game", Toast.LENGTH_LONG).show();
    }
}
