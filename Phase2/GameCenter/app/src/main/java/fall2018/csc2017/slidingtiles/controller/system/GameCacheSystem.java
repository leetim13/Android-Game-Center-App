package fall2018.csc2017.slidingtiles.controller.system;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
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
    private static final GameCacheSystem ourInstance = new GameCacheSystem();
    private StorageIndexer indexer = new StorageIndexer();

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
    }
}
