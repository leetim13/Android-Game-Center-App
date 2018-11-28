package fall2018.csc2017.slidingtiles;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.interfaces.ScoreDisplayable;

public class BasicScoreBoardActivity extends AppCompatActivity implements ScoreDisplayable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void displayScore(TextView[] renderList, ScoreBoardSystem<TextView> system, int index) {
        List<SequenceBundlers> bd = system.displayScore(index);

        if (bd == null) {
            return;
        }

        for (int i = 0; i < bd.size(); i++) {
            SequenceBundlers bundler = bd.get(i);
            String username = bundler.getkey();
            int record = bundler.getValue();
            if (i < renderList.length) {
                TextView v = renderList[i];
                String content = username + " " + Integer.toString(record);
                v.setText(content);
            }
        }
    }

    @Override
    public void renderBoard() {
        System.out.println("board is rendered");
    }

}
