package fall2018.csc2017.slidingtiles.controller;

import android.util.SparseArray;

import fall2018.csc2017.slidingtiles.model.component.User;

/*
* This is the class for game indexing, serve as a middleware
*  Telling the storage files for scores and game
* */
public class StorageIndexer {

    public final static int GAME = 0;
    public final static int SCORE = 1;

    private SparseArray<String[]> hook = new SparseArray<>(); // hook to place the storages
    public StorageIndexer () {
        hook.put(User.ST_GAME_INDEX_3, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH33});
        hook.put(User.ST_GAME_INDEX_4, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH44});
        hook.put(User.ST_GAME_INDEX_5, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH55});
        hook.put(User.TF_GAME_INDEX, new String[]{UserRouter.GAME_STORAGE_TF, UserRouter.SCORE_STORAGE_TF});
        hook.put(User.SD_GAME_INDEX, new String[]{UserRouter.GAME_STORAGE_SD, UserRouter.SCORE_STORAGE_SD});
    }

    public String index(int gameIndex, int dataType) {
        return hook.get(gameIndex)[dataType];
    }
}
