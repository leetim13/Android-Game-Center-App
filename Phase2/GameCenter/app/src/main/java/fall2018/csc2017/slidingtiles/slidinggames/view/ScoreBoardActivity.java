package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.interfaces.ScoreDisplayable;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
/**
 * The global scoreboard activity for the sliding puzzle tile game.
 */

public class ScoreBoardActivity extends AppCompatActivity implements ScoreDisplayable {

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

        String[] scoreFiles = new String[]{UserRouter.SCORE_STORAGE_PATH33,
                                           UserRouter.SCORE_STORAGE_PATH44,
                                           UserRouter.SCORE_STORAGE_PATH55
                                            }; // index0: 33, index1: 44, index2: 55
        ScoreBoardSystem boardSystem = new ScoreBoardSystem<Button>(scoreFiles ,getApplicationContext());
        Button[] viewList1 = getViewList(renderList1);
        Button[] viewList2 = getViewList(renderList2);
        Button[] viewList3 = getViewList(renderList3);
        displayScore(viewList1, boardSystem, 0); // display 33 to viewList1
        displayScore(viewList2, boardSystem, 1); // display 44 to viewList2
        displayScore(viewList3, boardSystem, 2);
    }

    private Button[] getViewList(int[] renderList) {
        Button[] result = new Button[3];
        for (int i = 0; i < renderList.length; i++) {
            result[i] = findViewById(renderList[i]);
        }
        return result;
    }

    public void displayScore(TextView[] renderList, ScoreBoardSystem<TextView> system, int index) {
        system.displayScore(renderList, index);
    }
}