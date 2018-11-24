package fall2018.csc2017.slidingtiles.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;
import fall2018.csc2017.slidingtiles.tfgames.view.FinalScoreTFActivity;
import fall2018.csc2017.slidingtiles.tfgames.view.StartingActivityTF;
import fall2018.csc2017.slidingtiles.tfgames.view.YouLoseTFActivity;

public class MovementControllerTF extends MovementController{

    BoardManagerTF boardManagerTF;
    SaveScore saveScore = new SaveScore();

//    MovementControllerTF(BoardManagerTF boardManagerTF){
//        this.boardManagerTF = boardManagerTF;
//    }


    @Override
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerTF = (BoardManagerTF) boardManager;
    }

    @Override
    /**
     * UP_SIGNAL = 0;
     * RIGHT_SIGNAL = 1;
     * DOWN_SIGNAL = 2;
     * LEFT_SIGNAL = 3;
     */
    public void processTapMovement(Context context, int direction){
        boardManagerTF.touchMove(direction);
        System.out.println("touch moved!");
        boardManagerTF.addScore();
        if(boardManagerTF.hasWon()){
            Toast.makeText(context, "You win!", Toast.LENGTH_SHORT).show();
            saveScore.saveScoreIntoMap(context, boardManagerTF.getGameIndex(), boardManagerTF.getScore());
            ((Activity) context).finish();
            Intent intent = new Intent(context, FinalScoreTFActivity.class);
            context.startActivity(intent);
        }
        if(boardManagerTF.hasLost()){
            Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show();
            ((Activity) context).finish();
            Intent intent = new Intent(context, YouLoseTFActivity.class);
            context.startActivity(intent);
        }

    }
}
