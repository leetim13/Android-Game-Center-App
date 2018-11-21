package fall2018.csc2017.slidingtiles.system;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.slidingtiles.slidinggames.manager.BoardManager;

/*
Singleton:
this is a system to store and cache the game data when login process succeeds
  * help store and return the expected data
* */
public class GameCacheSystem {
    private final static int NumOfUsers = 16;
    private Map<String, BoardManager> slidingMap = new HashMap<>(NumOfUsers);
    private static final GameCacheSystem ourInstance = new GameCacheSystem();
    public static GameCacheSystem getInstance() {
        return ourInstance;
    }

    private GameCacheSystem() {

    }

    public void loadFromFile(String fileName, Context ctx) {

    }
}
