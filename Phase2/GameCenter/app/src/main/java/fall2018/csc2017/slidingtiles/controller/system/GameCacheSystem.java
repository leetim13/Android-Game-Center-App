package fall2018.csc2017.slidingtiles.controller.system;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;

/*
Singleton:
this is a system to store and cache the game data when login process succeeds
  * help store and return the expected data
* */
public class GameCacheSystem {
    private final static int NumOfUsers = 16;
    private Map<String, BasicBoardManager> currentGame = new HashMap<>(NumOfUsers);
    private Map<String, Integer> previousGame = new HashMap<>(NumOfUsers); // previous game map
    private static final GameCacheSystem ourInstance = new GameCacheSystem();
    private StorageIndexer indexer = new StorageIndexer();
    private int currentIndex = -1;
    private static String LAST_GAME_INDEX = "__last__.ser";
    private boolean prevLoaded = false; // check whether the previous index loaded

    public static GameCacheSystem getInstance() {
        return ourInstance;
    }

    private GameCacheSystem() {
    }
    /*
    * @param gameIndex: the game index attribute in User class, with TILEGAMEINDEX, TFGAMEINDEX
    * @param context: the activity context for file reading and writing
    * */
    @SuppressWarnings("unchecked")
    public void loadGame(int gameIndex, Context context) {
        currentGame = new HashMap<>();
        try {
            InputStream inputStream = context.openFileInput(indexer.index(gameIndex, StorageIndexer.GAME));
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                HashMap<String, BasicBoardManager> newMap = (HashMap<String, BasicBoardManager>) input.readObject();
                for(Map.Entry<String, BasicBoardManager> entry: newMap.entrySet()) {
                    String key = entry.getKey();
                    BasicBoardManager board = entry.getValue();
                    currentGame.put(key, board);
                }
                inputStream.close();
                currentIndex = gameIndex;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    public void update(String username, BasicBoardManager manager) {
        currentGame.put(username, manager);
    }

    public Map<String, BasicBoardManager> getData() {
        return currentGame;
    }

    public BasicBoardManager get(String username) {
        if (currentGame.containsKey(username)) {
            return currentGame.get(username);
        }

        else {
            System.out.println("no game is specified in tfgame!");
            return null;
        }
    }
    /*
    * @param gameIndex the index of this game
    * save game progress to certain files according to game index
    * */
    @SuppressWarnings("unchecked")
    public void save(int gameIndex, Context ctx) {
        ActivityHelper.saveToFile(indexer.index(gameIndex, StorageIndexer.GAME), ctx, currentGame);
        prevLoaded = true; // confirm that we're going to save the current index as previous
        saveCurrentIndex(ctx);
    }

    /*
    * default storage method
    * save game progress to files according to current game index
    * */
    @SuppressWarnings("unchecked")
    public void save(Context ctx) {
        if (currentIndex == -1) {
            System.out.println("invalid index for the currentGame");
            return;
        }
        save(currentIndex, ctx);
    }
    /*
    * save the current game index when save to file
    * */
    private void saveCurrentIndex(Context ctx) {

        if (currentIndex == -1 || !prevLoaded) {
            return;
        }

        previousGame.put(UserPanel.getInstance().getName(), currentIndex);
        ActivityHelper.saveToFile(LAST_GAME_INDEX, ctx, previousGame);
    }
    /*
    * load the index of the last game played
    * */
    @SuppressWarnings("unchecked")
    public void load_index(Context ctx) {
        try {
            Map<String, Integer> newMap = IOHelper.readAndroidMap(LAST_GAME_INDEX, ctx);
            previousGame = (newMap == null) ? previousGame:newMap;
            prevLoaded = true;
        } catch (IOException e) {
            System.out.println("previous indexes cannot be loaded!");
        }
    }
    /*
    * return the index of the previous game
    * */
    public int prevGame() {
        String name = UserPanel.getInstance().getName();

        if (previousGame.containsKey(name)) {
            return previousGame.get(name);
        }

        return -1;
    }
}
