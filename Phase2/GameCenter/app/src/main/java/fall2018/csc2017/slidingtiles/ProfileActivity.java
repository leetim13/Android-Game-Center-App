package fall2018.csc2017.slidingtiles;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


import fall2018.csc2017.slidingtiles.SlidingGame.Managers.BoardManager;
import fall2018.csc2017.slidingtiles.users.UserPanel;

/**
 * The profile activity for an individual user's profile.
 * used to display the profile of the user
 */
public class ProfileActivity extends AppCompatActivity {

    private TextView titleView; // the title of the user profile
    private TextView previousStateMove; // the number of moves the player took in previous game
    private TextView previousStateType; // previous game name
    private TextView cUser; // the name of the user in the bottom line
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
        BoardManager game = LoginActivity.userBoardHashMap.get(UserPanel.getInstance().getName());
        String prevGame = (game != null) ?
                            game.getComplexity() + "x" + game.getComplexity():
                            INVALID_TEXT;
        String numMoves = (game != null) ? Integer.toString(game.getScore()): INVALID_TEXT;
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

    }

    /**
     * set text for the content going to be displayed
    */
    private void displayContent(TextView v, String text) {
        if (text != null) {
            v.setText(text);
        }
    }
}
