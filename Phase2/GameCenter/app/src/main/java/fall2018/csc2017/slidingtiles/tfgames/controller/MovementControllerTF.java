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

/**
 * the class to make change on each move for tf (2048) game.
 */
public class MovementControllerTF extends MovementController {

    /**
     * The board Manager in this game.
     */
    private BoardManagerTF boardManagerTF;

    /**
     * The functional saveScore, which provides method to save score into corresponding Map.
     */
    private SaveScore saveScore = new SaveScore();

    /**
     * The new MovementControllerTF
     */
    public MovementControllerTF(){
        super.stateStack = new ArrayStack<BoardTF>(20000);
    }

    @Override
    /**
     * The setter for boardManager.
     */
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerTF = (BoardManagerTF) boardManager;
    }

    @Override
    /**
     * To ask the board manager do proper things according to given tap.
     *
     * @param context  the context.
     * @param position the background number of current tile to be clicked on.
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

    /**
     * To return a copy of boardTF inside the given boardManagerTF
     *
     * @param boardManagerTF the boardManager with boardTF to be copied.
     * @return boardTF the deep copied boardTF.
     */
    private BoardTF getBoardManagerTFCopy(BoardManagerTF boardManagerTF){
        TfTile[][] newTiles = boardManagerTF.getBoard().getTilesCopy();
        return new BoardTF(4, newTiles);
    }
}
