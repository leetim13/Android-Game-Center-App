package fall2018.csc2017.slidingtiles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.interfaces.ScoreDisplayable;

/**
 * Excluded from tests because it's a view class.
 * The Basic Score Board activity for all three games.
 */
public class BasicScoreBoardActivity extends AppCompatActivity implements ScoreDisplayable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Display score as TextView.
     */
    public void displayScore(TextView[] renderList, ScoreBoardSystem system, int index) {
        List<SequenceBundlers> bd = system.displayScore(index);

        if (bd == null) {
            return;
        }

        for (int i = 0; i < bd.size(); i++) {
            SequenceBundlers bundler = bd.get(i);
            String username = bundler.getKey();
            int record = bundler.getValue();
            if (i < renderList.length) {
                TextView v = renderList[i];
                String content = username + " " + Integer.toString(record); // number of moves
                v.setText(content);
            }
        }
    }

    @Override
    public void renderBoard() {
        System.out.println("board is rendered");
    }

    public void returnPage(View v) {
        finish();
    }

}
