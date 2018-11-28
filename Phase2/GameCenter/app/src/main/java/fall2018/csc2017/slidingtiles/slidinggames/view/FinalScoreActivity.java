package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.BasicFinalScoreActivity;
import fall2018.csc2017.slidingtiles.GameCenterActivity;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

public class FinalScoreActivity extends BasicFinalScoreActivity {
    /**
     * The number of total moves used.
     */
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /*
    * set the boardmanager
    * */
    @Override
    public void setBM() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        currentBM = sys.get(UserPanel.getInstance().getName());
    }
}
