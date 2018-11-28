package fall2018.csc2017.slidingtiles.interfaces;

import android.widget.TextView;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;

/*
* this is mainly used for activities which wants to display score using the scoreboardsystem
* */
public interface ScoreDisplayable {
    /*
    * @param viewList the view to display
    * @param ScoreBoardSystem the current score board system
    * @param int index: the index of the current model for the scoreboard
    * */
    void displayScore(TextView[] viewList, ScoreBoardSystem<TextView> system, int index);
    /*
    * every score board should have a function to render its own score board
    * */
    void renderBoard();
}
