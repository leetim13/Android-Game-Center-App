package fall2018.csc2017.slidingtiles.tfgames.model;

import android.content.Context;

import java.io.IOException;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.model.GameScore;
import fall2018.csc2017.slidingtiles.model.component.User;

/*
 * the model for the data in sliding tile score
 * */
public class TfScore extends GameScore {
    private Map<String, int[]> data;
    @SuppressWarnings("unchecked")
    public TfScore(Context ctx) {
        String filePath = indexer.index(User.TF_GAME_INDEX, StorageIndexer.SCORE);
        try {
            data = IOHelper.readAndroidMap(filePath, ctx);
        } catch (IOException e) {
            System.out.println("model TfScore io error in reading filePath: " + filePath);
        }
    }

    @Override
    public Map<String, int[]> data() {
        return data;
    }
}
