package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * The profile activity for an individual user's profile.
 * used to display the profile of the user
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView titleView; // the title of the user profile
    private TextView previousStateMove; // the number of moves the player took in previous game
    private TextView previousStateType; // previous game name
    private TextView cUser; // the name of the user in the bottom line
    private TextView lastGamePlayed; // the name of the last game played
    public static final String INVALID_TEXT = "no games played";

    /**
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        display();
    }

    /**
     * display the content on the profile page
    */
    private void display() {
        setConstant();
        GameCacheSystem sys = GameCacheSystem.getInstance();
        StorageIndexer indexer = new StorageIndexer();
        BasicBoardManager game = sys.get(UserPanel.getInstance().getName());
        int prevIndex = GameCacheSystem.getInstance().prevGame();
        String prevGame = processPrexIndex(prevIndex, game);
        String numMoves = (game != null) ? Integer.toString(game.getScore()): INVALID_TEXT;
        String lastGameName = indexer.getName(prevIndex);
        displayContent(lastGamePlayed, lastGameName);
        displayContent(titleView, UserPanel.getInstance().getName());
        displayContent(previousStateType, prevGame);
        displayContent(previousStateMove, numMoves);
    }
    /**
     * initiate the instance variable(or elements going to be displayed) for the screen
    */
    private void setConstant() {
        titleView = findViewById(R.id.tv_name);
        previousStateMove = findViewById(R.id.moveNum);
        previousStateType = findViewById(R.id.boardtype);
        lastGamePlayed = findViewById(R.id.last_play);
    }

    /**
     * set text for the content going to be displayed
    */
    private void displayContent(TextView v, String text) {
        if (text != null) {
            v.setText(text);
        }
    }

    /*
    * process the data of the previous index
    * */
    private String processPrexIndex(int index, BasicBoardManager game) {

        StorageIndexer indexer = new StorageIndexer();
        if (index == -1) {
            return INVALID_TEXT;
        }
        int complexity = game.getComplexity();
        return indexer.getName(index) + ": " + complexity + " x " + complexity;
    }
}
