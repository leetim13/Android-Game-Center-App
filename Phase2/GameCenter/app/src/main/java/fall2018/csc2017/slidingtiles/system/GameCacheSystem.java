package fall2018.csc2017.slidingtiles.system;
/*
Singleton:
this is a system to store and cache the game data when login process succeeds
  * help store and return the expected data
* */
public class GameCacheSystem {
    private static final GameCacheSystem ourInstance = new GameCacheSystem();

    public static GameCacheSystem getInstance() {
        return ourInstance;
    }

    private GameCacheSystem() {
    }
}
