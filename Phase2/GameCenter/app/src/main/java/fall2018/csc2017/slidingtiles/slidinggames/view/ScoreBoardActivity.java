package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.os.Bundle;
import android.view.View;

import fall2018.csc2017.slidingtiles.BasicScoreBoardActivity;
import fall2018.csc2017.slidingtiles.R;
import android.widget.Button;


import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.slidinggames.model.SlidingScore;

/**
 * Excluded from tests because it's a view class.
 * The global scoreboard activity for the sliding puzzle tile game.
 */
public class ScoreBoardActivity extends BasicScoreBoardActivity {

    private int[] renderList1;
    private int[] renderList2;
    private int[] renderList3;

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
    @Override
    public void renderBoard() {

        SlidingScore[] scoreModels = new SlidingScore[]{new SlidingScore(User.ST_GAME_INDEX_3, this),
                                           new SlidingScore(User.ST_GAME_INDEX_4, this),
                                           new SlidingScore(User.ST_GAME_INDEX_5, this),
                                            }; // index0: 33, index1: 44, index2: 55
        ScoreBoardSystem boardSystem = new ScoreBoardSystem(scoreModels);
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
}