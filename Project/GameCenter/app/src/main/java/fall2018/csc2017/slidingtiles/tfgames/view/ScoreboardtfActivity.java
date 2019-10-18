package fall2018.csc2017.slidingtiles.tfgames.view;

import android.os.Bundle;

import fall2018.csc2017.slidingtiles.BasicScoreBoardActivity;
import fall2018.csc2017.slidingtiles.R;

import android.widget.Button;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.tfgames.model.TfScore;

/**
 * Excluded from tests because it's a view class.
 * The global scoreboard activity for the sliding puzzle tile game.
 */
public class ScoreboardtfActivity extends BasicScoreBoardActivity {

    /**
     * the renderList to display the score
     */
    private int[] renderList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboardtf);
        renderList1 = new int[]{R.id.score_tf1, R.id.score_tf2, R.id.score_tf3, R.id.score_tf4, R.id.score_tf5};
        renderBoard();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void renderBoard() {

        TfScore[] scoreModels = new TfScore[]{new TfScore(this)}; // index0: 33, index1: 44, index2: 55
        ScoreBoardSystem boardSystem = new ScoreBoardSystem(scoreModels);
        Button[] viewList1 = getViewList(renderList1);
        displayScore(viewList1, boardSystem, 0); // display 33 to viewList1
    }

    /**
     * To assign the renderList to a button array and return it
     *
     * @return the buttons array
     */
    private Button[] getViewList(int[] renderList) {
        Button[] result = new Button[5]; // 5 boards in total
        for (int i = 0; i < renderList.length; i++) {
            result[i] = findViewById(renderList[i]);
        }
        return result;
    }
}