package fall2018.csc2017.slidingtiles.interfaces;

import android.view.View;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.Systems.ScoreBoardSystem;

/*
* this is mainly used for activities which wants to display score using the scoreboardsystem
* */
public interface scoreDisplayable{
    void displayScore(TextView[] viewList, ScoreBoardSystem<TextView> system, int index);
}
