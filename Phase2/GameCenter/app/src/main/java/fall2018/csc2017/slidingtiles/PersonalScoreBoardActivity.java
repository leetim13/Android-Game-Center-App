package fall2018.csc2017.slidingtiles;

import android.os.Bundle;
import android.widget.Button;

import java.util.Arrays;
import java.util.Map;

import fall2018.csc2017.slidingtiles.users.User;

/**
 * The personal scoreboard activity for the sliding puzzle tile game.
 */
public class PersonalScoreBoardActivity extends ScoreBoardActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_personal_score_board);
        renderList1 = new int[]{R.id.p_score_p1_33, R.id.p_score_p2_33, R.id.p_score_p3_33};
        renderList2 = new int[]{R.id.p_score_p1_44, R.id.p_score_p2_44, R.id.p_score_p3_44};
        renderList3 = new int[]{R.id.p_score_p1_55, R.id.p_score_p2_55, R.id.p_score_p3_55};
        renderBoard();
    }

    @Override
    public void renderBoard() {
        super.renderBoard();
    }

    @Override
    protected void displayScore(int[] renderList, Map<String, int[]> mp) {
        int[] obj = mp.get(User.currentUser.username);

        int[] new_array;
        if (obj != null) {
            new_array = new int[obj.length];
            // type convert
            for (int i = 0; i < new_array.length; i++) {
                new_array[i] = obj[i];
            }
            Arrays.sort(new_array); // sort the rank of the players

            for (int i = 0; i < new_array.length; i++) {

                String username = User.currentUser.username;
                int record = new_array[i];
                if (i < renderList.length) {
                    Button bt = findViewById(renderList[i]);
                    bt.setText("Your Best: " + username + "  " + record);
                }
            }
        }
    }
}
