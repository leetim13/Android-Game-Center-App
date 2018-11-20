package fall2018.csc2017.slidingtiles.Controllers;

import android.content.Context;

import java.io.IOException;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.Helpers.IOHelper;
import fall2018.csc2017.slidingtiles.Components.User;
import fall2018.csc2017.slidingtiles.Systems.UserPanel;

/**
router class for user and file reflections.
* */
public class UserRouter {

    final public static String USER_STORE_PATH = "user.ser"; // path to store user file
    final public static String SCORE_STORAGE_PATH33 = "score_storage0.ser"; // path to store the game file 3x3
    final public static String SCORE_STORAGE_PATH44 = "score_storage1.ser"; // path to store the game file 4x4
    final public static String SCORE_STORAGE_PATH55 = "score_storage2.ser"; // path to store the game file 5x5

    private HashMap <String, String[]> userMap;
    private Context context;

    @SuppressWarnings("unchecked")
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
     * the basic verification
     * @param user: the user being examined
    */
    public boolean verifyUser(User user) {

        if (userMap == null) {
            return false;
        }

        if (userMap.containsKey(user.username)) {
            return ((String) userMap.get(user.username)[0]).equals(user.password);
        }

        return false;
    }
    /**
     * @param  user: the user that is being verified
     * @param  store: whether to store this user when verification succeeds
     * @return whether the user has passed the verification
    */
    public boolean verifyUser(User user, boolean store) {

        boolean result = verifyUser(user);
        System.out.println(user);

        if (store && !result) {

            if (userMap == null) {
                userMap = new HashMap<String, String[]>();
            }

            userMap.put(user.username, new String[]{user.password,
                                                    Integer.toString(user.TILEGAMEINDEX)});
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
