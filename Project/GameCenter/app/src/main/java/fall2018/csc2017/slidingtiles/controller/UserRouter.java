package fall2018.csc2017.slidingtiles.controller;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;


import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * router class for user and file reflections.
 */
public class UserRouter {

    /**
     * path to store user file
     */
    final private static String USER_STORE_PATH = "user.ser";

    /**
     * path to store the sliding tile game score file 3x3, 4x4, 5x5
     */
    final public static String SCORE_STORAGE_PATH33 = "score_storage_st0.ser";
    final public static String SCORE_STORAGE_PATH44 = "score_storage_st1.ser";
    final public static String SCORE_STORAGE_PATH55 = "score_storage_st2.ser";

    /**
     * path to store the game file of slidingTile game
     */
    final static String GAME_STORAGE_SLIDING = "save_file_tmp.ser";

    /**
     * path to store the score files and game file for tf game.
     */
    final static String SCORE_STORAGE_TF = "score_storage_tf.ser";
    final static String GAME_STORAGE_TF = "game_storage_tf.ser"; // path to store the tfgame

    /**
     * path to store the score files and game file for sudoku game.
     */
    final static String SCORE_STORAGE_SD = "score_storage_sd.ser";
    final static String GAME_STORAGE_SD = "game_storage_sd.ser";

    /**
     * User Map
     */
    private HashMap<String, String[]> userMap;

    /**
     * the context
     */
    private Context context;

    @SuppressWarnings("unchecked")
    /**
     * To new a UserRouter with given context
     */
    public UserRouter(Context ctx) {
        try {
            userMap = (HashMap<String, String[]>) IOHelper.readAndroidMap(USER_STORE_PATH, ctx);
            this.context = ctx;
        } catch (IOException e) {
            System.out.println("map loading failed! please check the file: " + USER_STORE_PATH);
            userMap = null;
        }
    }

    /**
     * the constructor for test use
     *
     * @param userMap: the user map passed in
     */
    public UserRouter(HashMap<String, String[]> userMap) {
        this.userMap = userMap;
    }

    /**
     * the basic verification
     * set to public for test use
     *
     * @param user: the user being examined
     */
    public boolean verifyUser(User user) {

        if (userMap == null) {
            return false;
        }

        if (userMap.containsKey(user.username)) {
            return (userMap.get(user.username)[0]).equals(user.password);
        }

        return false;
    }

    /**
     * @param user:  the user that is being verified
     * @param store: whether to store this user when verification succeeds
     * @return whether the user has passed the verification
     */
    public boolean verifyUser(User user, boolean store) {

        boolean result = verifyUser(user);
        System.out.println(user);

        if (store && !result) {

            if (userMap == null) {
                userMap = new HashMap<>();
            }

            userMap.put(user.username, new String[]{user.password});
            try {
                IOHelper.writeAndroidMap(userMap, USER_STORE_PATH, context);
                result = true;
            } catch (IOException e) {
                System.out.println("user file writing failed: ... " + e);
            }
        }

        if (result) {
            UserPanel.getInstance().setUser(user);
        }

        return result;

    }
}
