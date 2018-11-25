package fall2018.csc2017.slidingtiles.slidinggames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.GameCenterActivity;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

public class FinalScoreActivity extends AppCompatActivity {
    /**
     * The number of total moves used.
     */
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        setNum();
        addScoreboardButtonListener();
        addScoreboardpersonalButtonListener();
        addGameCenterButtonListener();
    }
    /**
     * Display total moves to replace "num" from BoardManager.
     */
    private void setNum(){
//        BoardManager currentBM = LoginActivity.userBoardHashMap.get(UserPanel.getInstance().getName());
        GameCacheSystem sys = GameCacheSystem.getInstance();
        BoardManager currentBM = (BoardManager) sys.get(UserPanel.getInstance().getName());
        int numMoves = currentBM.getScore();
        System.out.println("number of moves:" + numMoves);
        tv = findViewById(R.id.NumMovesButton);
        tv.setText(Integer.toString(numMoves));
    }

    /**
     * Activate the scoreboard button.
     */
    private void addScoreboardButtonListener() {
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
    private void addScoreboardpersonalButtonListener() {
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
    private void addGameCenterButtonListener() {
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
