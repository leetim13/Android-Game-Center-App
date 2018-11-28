package fall2018.csc2017.slidingtiles;
/*
* Basic format of final score activity
* */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.view.PersonalScoreBoardActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.ScoreBoardActivity;

public class BasicFinalScoreActivity extends AppCompatActivity {

    public BasicBoardManager currentBM; // current manager
    private TextView tv; // the view of the moves
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score_tf);
        setNum();
        addScoreboardButtonListener();
        addScoreboardpersonalButtonListener();
        addGameCenterButtonListener();
    }



    public void setBM() {

        System.out.println("current bm set!");
    } //

    public void setNum() {
        GameCacheSystem sys = GameCacheSystem.getInstance();
        setBM(); // set the board manager here
        int numMoves = currentBM.getScore();
        System.out.println("number of moves:" + numMoves);
        tv = findViewById(R.id.NumMovesButton);
        tv.setText(Integer.toString(numMoves));
    }

    /**
     * Activate the scoreboard button.
     */
    public void addScoreboardButtonListener() {
        Button startButton = findViewById(R.id.button_scoreboard);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToScoreboard();
            }
        });
    }

    /**
     * Activate the personal scoreboard button.
     */
    public void addScoreboardpersonalButtonListener() {
        Button startButton = findViewById(R.id.button_personal_record);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToPersonalBoard();
            }
        });
    }

    /**
     * Activate the game center button.
     */
    public void addGameCenterButtonListener() {
        Button startButton = findViewById(R.id.GameCenterButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGameCenter();
            }
        });
    }

    /**
     * Switch to the ScoreBoardActivity Activity view to see global scoreboard.
     */
    public void switchToScoreboard(){
        Intent tmp = new Intent(this, ScoreBoardActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the PersonalScoreBoardActivity  view to see local (personal) scoreboard.
     */
    public void switchToPersonalBoard() {
        Intent tmp = new Intent(this, PersonalScoreBoardActivity.class);
        startActivity(tmp);
    }

    /**
     * Switch to the PersonalScoreBoardActivity  view to see local (personal) scoreboard.
     */
    public void switchToGameCenter() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        startActivity(tmp);
    }
}
