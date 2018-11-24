package fall2018.csc2017.slidingtiles.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.sudokugames.manager.BoardManagerSudoku;

public class MovementControllerSK extends MovementController {
    private BoardManagerSudoku boardManagerSK = new BoardManagerSudoku(9);
    private SaveScore saveScore = new SaveScore();
    @Override
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerSK = (BoardManagerSudoku) boardManager;
    }
    public void processTapMovement(Context context, int direction){
        boardManagerSK.addScore();
    }
}
