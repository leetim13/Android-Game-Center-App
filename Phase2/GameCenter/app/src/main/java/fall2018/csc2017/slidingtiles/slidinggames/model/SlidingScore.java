package fall2018.csc2017.slidingtiles.slidinggames.model;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.model.GameScore;

/**
 * the model for the data in sliding tile score
 */
public class SlidingScore extends GameScore {

    /**
     * The map stores some scores for sliding game
     */
    private Map<String, int[]> data;

    @SuppressWarnings("unchecked")
    /**
     * To new a SlidingScore, stores data read from file
     */
    public SlidingScore(int type, Context ctx) {
        String filePath = indexer.index(type, StorageIndexer.SCORE);
        try {
            data = IOHelper.readAndroidMap(filePath, ctx);
        } catch (IOException e) {
            System.out.println("model sliding score io error in reading filePath: " + filePath);
        }
    }

    @Override
    /**
     * The getter for data
     */
    public Map<String, int[]> data() {
        return data;
    }
}
