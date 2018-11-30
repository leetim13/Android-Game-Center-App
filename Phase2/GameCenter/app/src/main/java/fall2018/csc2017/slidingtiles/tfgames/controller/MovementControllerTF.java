package fall2018.csc2017.slidingtiles.tfgames.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.MovementController;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;
import fall2018.csc2017.slidingtiles.tfgames.model.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.model.component.TfTile;
import fall2018.csc2017.slidingtiles.tfgames.view.FinalScoreTFActivity;
import fall2018.csc2017.slidingtiles.tfgames.view.YouLoseTFActivity;

public class MovementControllerTF extends MovementController {

    private BoardManagerTF boardManagerTF;
    private SaveScore saveScore = new SaveScore();

    public MovementControllerTF(){
        super.stateStack = new ArrayStack<BoardTF>(20000);
    }

    @Override
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerTF = (BoardManagerTF) boardManager;
    }

    @Override
    /*
     * UP_SIGNAL = 0;
     * RIGHT_SIGNAL = 1;
     * DOWN_SIGNAL = 2;
     * LEFT_SIGNAL = 3;
     */
    @SuppressWarnings("unchecked")
    public void processTapMovement(Context context, int direction){
        BoardTF boardCopy = getBoardManagerTFCopy(boardManagerTF);
        stateStack.push(boardCopy);
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

    private BoardTF getBoardManagerTFCopy(BoardManagerTF boardManagerTF){
        TfTile[][] newTiles = boardManagerTF.getBoard().getTilesCopy();
        return new BoardTF(4, newTiles);
    }
}
