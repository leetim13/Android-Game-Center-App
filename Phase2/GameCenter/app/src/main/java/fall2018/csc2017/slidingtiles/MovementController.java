package fall2018.csc2017.slidingtiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.IOException;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.Structures.ArrayStack;
import fall2018.csc2017.slidingtiles.Helpers.IOHelper;
import fall2018.csc2017.slidingtiles.users.User;
import fall2018.csc2017.slidingtiles.users.UserRouter;

/**
 * the class to make change on each move.
 */
public class MovementController {

    /**
     * The board Manager in this game.
     */
    private BoardManager boardManager = null;

    /**
     * The stack to keep track state of every movement.
     */
    protected ArrayStack stateStack = new ArrayStack(200);

    /**
     * The functional saveScore, which provides method to save score into corresponding HashMap.
     */
    private SaveScore saveScore = new SaveScore();

    /**
     * The new MovementController
     */
    public MovementController() {
    }

    /**
     * To set board manager.
     */
    public void setBoardManager(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    /**
     * To ask the board manager do proper things according to given tap.
     *
     * @param context the context.
     * @param position the background number of current tile to be clicked on.
     *
     */
    public void processTapMovement(Context context, int position) {
        if (boardManager.isValidTap(position)) {
            int[] dir = boardManager.touchMove(position);
            boardManager.addScore();
            int positionPrime = dir[0]*Board.numRows + dir[1];
            LoginActivity.userBoardHashMap.put(User.currentUser.username, boardManager);
            stateStack.push(positionPrime);
            if (boardManager.puzzleSolved()) {
                Toast.makeText(context, "YOU WIN!", Toast.LENGTH_SHORT).show();
                saveScore.saveScoreIntoMap(context);
                ((Activity) context).finish();
                Intent intent = new Intent(context, FinalScoreActivity.class);
                context.startActivity(intent);
            }
        } else {
            Toast.makeText(context, "Invalid Tap", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * the functional class, save the score of current completed sliding tile game into correspoding
     * hashMap.
     */
    private class SaveScore{

        /**
         * a hashMap, matching the complexity and Path for their corresponding hashMap to store
         * user's scores.
         */
        private HashMap<Integer, String> hook;

        /**
         * The new SaveScore, initialize the hashMap, matching the complexity and Path for their
         * corresponding hashMap to store the score.
         */
        SaveScore() {
            hook = new HashMap<>();
            hook.put(3, UserRouter.SCORE_STORAGE_PATH33);
            hook.put(4, UserRouter.SCORE_STORAGE_PATH44);
            hook.put(5, UserRouter.SCORE_STORAGE_PATH55);

        }

        /**
         * To save the score this time into the score record of this current user.
         * create the user's record if he/she does not have one.
         *
         * @param context the context.
         */
        @SuppressWarnings("unchecked")
        public void saveScoreIntoMap(Context context){
            String mapName = hook.get(boardManager.getComplexity());
            try{
                int score = boardManager.getScore();
                System.out.println("start reading the map...");
                HashMap<String, int[]> map = IOHelper.readAndroidMap(mapName, context);
                int[] newScores;
                if(map == null){
                    map = new HashMap<String, int[]>();
                    newScores = changeScore(map, new int[1], score);
                }else{
                    int[] scores = map.get(User.currentUser.username);
                    newScores = changeScore(map, scores, score);
                }
                writeInMapHelper(map, newScores, context);
            } catch (NullPointerException e){
                System.out.println("byebye");
            } catch (IOException e){
                System.out.println("File Stream Problem ");
            }
        }

        /**
         * To make proper change in the user's score record.
         *
         * @param map the hashMap of the complexity for current game.
         * @param scores the original score records this user has.
         * @param score the score user got this time.
         *
         * @return the new score records including the newest score user got.
         *
         */
        private int[] changeScore(HashMap map, int[] scores, int score) {
            int[] newScores;
            if (map != null && map.containsKey(User.currentUser.username)) {
                newScores = new int[scores.length + 1];
                for (int i = 0; i < scores.length; i++) {
                    newScores[i] = scores[i];
                }
                newScores[scores.length] = score;
            } else {
                newScores = new int[]{score};
            }
            return newScores;
        }

        /**
         * To make proper change in the user's score record.
         *
         * @param map the hashMap of the complexity for current game.
         * @param newScore the new score records including the newest score user got.
         * @param context the context.
         *
         */
        private void writeInMapHelper(HashMap<String, int[]> map, int[] newScore, Context context) throws IOException{
            String mapName = hook.get(boardManager.getComplexity());
            map.put(User.currentUser.username, newScore);
            IOHelper.writeAndroidMap(map, mapName, context);
            System.out.println("the first score map for" + mapName + "created!");
        }
    }
}
