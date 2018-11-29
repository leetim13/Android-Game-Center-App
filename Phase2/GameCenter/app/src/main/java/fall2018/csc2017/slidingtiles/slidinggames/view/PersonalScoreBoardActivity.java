package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.controller.UserRouter;

/**
 * Excluded from tests because it's a view class.
 * The personal scoreboard activity for the sliding puzzle tile game.
 */
public class PersonalScoreBoardActivity extends AppCompatActivity {
    public int[] renderList1;
    public int[] renderList2;
    public int[] renderList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_personal_score_board);
        renderList1 = new int[]{R.id.p_score_p1_33, R.id.p_score_p2_33, R.id.p_score_p3_33};
        renderList2 = new int[]{R.id.p_score_p1_44, R.id.p_score_p2_44, R.id.p_score_p3_44};
        renderList3 = new int[]{R.id.p_score_p1_55, R.id.p_score_p2_55, R.id.p_score_p3_55};
        renderBoard();
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

    public void returnPage(View v) {
        finish();
    }

    /**
     * To sort and display the personal slidingTile game scores of the current user
     */
    protected void displayScore(int[] renderList, Map<String, int[]> mp) {
        int[] obj = mp.get(UserPanel.getInstance().getName());
        int[] new_array;
        if (obj != null) {
            new_array = new int[obj.length];
            // type convert
            for (int i = 0; i < new_array.length; i++) {
                new_array[i] = obj[i];
            }
            Arrays.sort(new_array); // sort the rank of the players

            for (int i = 0; i < new_array.length; i++) {
                String username = UserPanel.getInstance().getName();
                int record = new_array[i];
                if (i < renderList.length) {
                    Button bt = findViewById(renderList[i]);
                    bt.setText("Your Best: " + username + "  " + record);
                }
            }
        }
    }
}
