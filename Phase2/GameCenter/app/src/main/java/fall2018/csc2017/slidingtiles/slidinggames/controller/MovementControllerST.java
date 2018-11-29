package fall2018.csc2017.slidingtiles.slidinggames.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.MovementController;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;
import fall2018.csc2017.slidingtiles.slidinggames.view.FinalScoreActivity;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * the class to make change on each move.
 */
public class MovementControllerST extends MovementController {

    /**
     * The board Manager in this game.
     */
    private BoardManager boardManager = null;

    /**
     * The stack to keep track state of every movement.
     */

    private SaveScore saveScore = new SaveScore();
    private ArrayStack<Integer> stateStack;

//    /**
//     * The functional saveScore, which provides method to save score into corresponding HashMap.
//     */
//    private SaveScore saveScore = new SaveScore();

    /**
     * The new MovementControllerST
     */
    public MovementControllerST() {
        stateStack = new ArrayStack<>(20000);
    }

    /**
     * To set board manager.
     */
    public void setBoardManager(BasicBoardManager boardManager) {
        this.boardManager = (BoardManager) boardManager;
    }

    /**
     * To ask the board manager do proper things according to given tap.
     *
     * @param context  the context.
     * @param position the background number of current tile to be clicked on.
     */
    public void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            int[] dir = boardManager.touchMove(position);
            boardManager.addScore();
            int positionPrime = dir[0] * boardManager.getBoardNumOfRows() + dir[1];
            UserPanel user = UserPanel.getInstance();
//            LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
            GameCacheSystem sys = GameCacheSystem.getInstance();
            sys.update(user.getName(), boardManager);
            stateStack.push(positionPrime);
            if (boardManager.hasWon()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                saveScore.saveScoreIntoMap(context, boardManager.getGameIndex(), boardManager.getScore());
                ((Activity) context).finish();
                Intent intent = new Intent(context, FinalScoreActivity.class);
                context.startActivity(intent);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }
}


