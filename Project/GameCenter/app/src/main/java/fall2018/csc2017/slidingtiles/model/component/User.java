/*
Data Stucture:
HashMap0 Structure
    key: String username
    value []:
        - index 0: String password
        - index 1: String GameName
*/
/*
 * the user instance
 * +username: the name of the user
 * +password: the password of this user
 * */
package fall2018.csc2017.slidingtiles.model.component;

public class User {

    /**
     * The game index for sliding tile game of complexity 3, which is set as 0.
     */
    public static final int ST_GAME_INDEX_3 = 0;

    /**
     * The game index for sliding tile game of complexity 4, which is set as 1.
     */
    public static final int ST_GAME_INDEX_4 = 1;

    /**
     * The game index for sliding tile game of complexity 5, which is set as 2.
     */
    public static final int ST_GAME_INDEX_5 = 2;

    /**
     * The game index for tf game, which is set as 3.
     */
    public static final int TF_GAME_INDEX = 3;

    /**
     * The game index for sudoku game, which is set as 4.
     */
    public static final int SD_GAME_INDEX = 4;

    /**
     * The user's name and password.
     */
    public String username;
    public String password;

    /**
     * To new a new User
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "user name: " + username + "\n" + "password: " + password;
    }
}
