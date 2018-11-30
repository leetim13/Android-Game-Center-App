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
    private SparseArray<String> nameHook = new SparseArray<>(); // hook to reflect to name according to index
    /*
     * A constructor to initialize storage and names
     * */
    public StorageIndexer () {
        initializeStorage();
        initializeNames();
    }

    /*
     * Initialize storage for all 3 games
     * */
    private void initializeStorage() {
        hook.put(User.ST_GAME_INDEX_3, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH33});
        hook.put(User.ST_GAME_INDEX_4, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH44});
        hook.put(User.ST_GAME_INDEX_5, new String[]{UserRouter.GAME_STORAGE_SLIDING, UserRouter.SCORE_STORAGE_PATH55});
        hook.put(User.TF_GAME_INDEX, new String[]{UserRouter.GAME_STORAGE_TF, UserRouter.SCORE_STORAGE_TF});
        hook.put(User.SD_GAME_INDEX, new String[]{UserRouter.GAME_STORAGE_SD, UserRouter.SCORE_STORAGE_SD});
    }
    /*
     * Initialize Names for all 3 games
     * */
    private void initializeNames() {
        nameHook.put(User.ST_GAME_INDEX_3, "SlidingTiles");
        nameHook.put(User.ST_GAME_INDEX_4, "SlidingTiles");
        nameHook.put(User.ST_GAME_INDEX_5, "SlidingTiles");
        nameHook.put(User.TF_GAME_INDEX, "2048");
        nameHook.put(User.SD_GAME_INDEX, "sudoku");
    }

    /*
     * Return the name of the game, often used as filePath
     * @param gameIndex the game index attribute in User class
     * @param dataType the data type of the game either GAME or SCORE
     * */
    public String index(int gameIndex, int dataType) {
        return hook.get(gameIndex)[dataType];
    }

    /*
     * Return the name of the specifed game through the index.
     * @param gameIndex the game index attribute in User class
     * */
    public String getName(int gameindex) {
        return nameHook.get(gameindex);
    }
}
