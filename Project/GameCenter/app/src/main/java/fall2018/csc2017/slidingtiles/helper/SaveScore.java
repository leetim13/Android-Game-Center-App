package fall2018.csc2017.slidingtiles.helper;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * the functional class, save the score of current completed sliding tile game into correspoding
 * hashMap.
 */
public class SaveScore {

    /**
     * load the indexer
     */
    private StorageIndexer indexer = new StorageIndexer();

    /**
     * The new SaveScore, initialize the hashMap, matching the complexity and Path for their
     * corresponding hashMap to store the score.
     */
    public SaveScore() {

    }

    /**
     * To save the score this time into the score record of this current user.
     * create the user's record if he/she does not have one.
     *
     * @param context the context.
     */
    @SuppressWarnings("unchecked")
    public void saveScoreIntoMap(Context context, int gameIndex, int score) {
        String mapName = indexer.index(gameIndex, StorageIndexer.SCORE);
        try {
            HashMap<String, int[]> map = IOHelper.readAndroidMap(mapName, context);
            int[] newScores;
            if (map == null) {
                map = new HashMap<String, int[]>();
                newScores = changeScore(map, new int[1], score);
            } else {
                int[] scores = map.get(UserPanel.getInstance().getName());
                newScores = changeScore(map, scores, score);
            }
            writeInMapHelper(map, newScores, context, gameIndex);
        } catch (NullPointerException e) {
            System.out.println("byebye");
        } catch (IOException e) {
            System.out.println("File Stream Problem ");
        }
    }

    /**
     * To make proper change in the user's score record.
     *
     * @param map    the hashMap of the complexity for current game.
     * @param scores the original score records this user has.
     * @param score  the score user got this time.
     * @return the new score records including the newest score user got.
     */
    private int[] changeScore(HashMap map, int[] scores, int score) {
        int[] newScores;
        if (map != null && map.containsKey(UserPanel.getInstance().getName())) {
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
     * @param map      the hashMap of the complexity for current game.
     * @param newScore the new score records including the newest score user got.
     * @param context  the context.
     */
    private void writeInMapHelper(HashMap<String, int[]> map, int[] newScore, Context context, int gameIndex) throws IOException {
        String mapName = indexer.index(gameIndex, StorageIndexer.SCORE);
        map.put(UserPanel.getInstance().getName(), newScore);
        IOHelper.writeAndroidMap(map, mapName, context);
        System.out.println("the first score map for" + mapName + "created!");
    }
}
