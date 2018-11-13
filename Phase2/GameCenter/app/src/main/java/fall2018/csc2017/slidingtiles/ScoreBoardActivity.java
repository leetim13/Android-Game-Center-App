package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.slidingtiles.Helpers.IOHelper;
import fall2018.csc2017.slidingtiles.users.UserRouter;
import fall2018.csc2017.slidingtiles.Helpers.sequenceBundler;
/**
 * The global scoreboard activity for the sliding puzzle tile game.
 */

public class ScoreBoardActivity extends AppCompatActivity {

    public int[] renderList1;
    public int[] renderList2;
    public int[] renderList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        renderList1 = new int[]{R.id.score_p1_33, R.id.score_p2_33, R.id.score_p3_33};
        renderList2 = new int[]{R.id.score_p1_44, R.id.score_p2_44, R.id.score_p3_44};
        renderList3 = new int[]{R.id.score_p1_55, R.id.score_p2_55, R.id.score_p3_55};
        renderBoard();
    }

    public void returnPage(View v) {
        finish();
    }

    @SuppressWarnings("unchecked")
    public void renderBoard() {
        HashMap<String, int[]> player_state1 = null;
        HashMap<String, int[]> player_state2 = null;
        HashMap<String, int[]> player_state3 = null;

        try {

            player_state1 = IOHelper.readAndroidMap(UserRouter.SCORE_STORAGE_PATH33,
                    getApplicationContext());
            player_state2 = IOHelper.readAndroidMap(UserRouter.SCORE_STORAGE_PATH44,
                    getApplicationContext());
            player_state3 = IOHelper.readAndroidMap(UserRouter.SCORE_STORAGE_PATH55,
                    getApplicationContext());

        } catch (IOException e) {
            System.out.println("score board hasn't been initiated: " + e);
        }

        if (player_state1 != null) {
            System.out.println("wryyyyyyyyyyyy!");
            displayScore(renderList1, player_state1);
        }

        if (player_state2 != null) {
            displayScore(renderList2, player_state2);
        }

        if (player_state3 != null) {
            displayScore(renderList3, player_state3);
        }
    }

    protected void displayScore(int[] renderList, Map<String, int[]> mp) {
        List<sequenceBundler> bd = IOHelper.convertMap(mp);
        Collections.sort(bd);
        for (int i = 0; i < bd.size(); i++) {
            sequenceBundler bundler = bd.get(i);
            String username = bundler.getkey();
            int record = bundler.getValue();
            if (i < renderList.length) {
                Button bt = findViewById(renderList[i]);
                bt.setText(username + "  " + record);
            }
        }
    }
}