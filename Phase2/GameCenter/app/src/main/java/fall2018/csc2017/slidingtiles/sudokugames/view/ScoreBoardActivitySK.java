package fall2018.csc2017.slidingtiles.sudokugames.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import fall2018.csc2017.slidingtiles.BasicScoreBoardActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.interfaces.ScoreDisplayable;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.slidinggames.model.SlidingScore;
import fall2018.csc2017.slidingtiles.sudokugames.model.SudokuScore;
import fall2018.csc2017.slidingtiles.tfgames.model.TfScore;

/**
 * Excluded from tests because it's a view class.
 * The global scoreboard activity for the sudoku game.
 */

public class ScoreBoardActivitySK extends BasicScoreBoardActivity {

    private int[] renderList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board_sk);
        renderList1 = new int[]{R.id.score_tf1, R.id.score_tf2, R.id.score_tf3, R.id.score_tf4, R.id.score_tf5};
        renderBoard();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void renderBoard() {

        SudokuScore[] scoreModels = new SudokuScore[]{new SudokuScore(this)}; // index0: 33, index1: 44, index2: 55
        ScoreBoardSystem boardSystem = new ScoreBoardSystem(scoreModels);
        Button[] viewList1 = getViewList(renderList1);
        displayScore(viewList1, boardSystem, 0); // display 33 to viewList1
    }

    private Button[] getViewList(int[] renderList) {
        Button[] result = new Button[5]; // 5 boards in total
        for (int i = 0; i < renderList.length; i++) {
            result[i] = findViewById(renderList[i]);
        }
        return result;
    }
}