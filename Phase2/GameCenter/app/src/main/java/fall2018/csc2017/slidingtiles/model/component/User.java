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

    public static final int ST_GAME_INDEX_3 = 0;
    public static final int ST_GAME_INDEX_4 = 1;
    public static final int ST_GAME_INDEX_5 = 2;
    public static final int TF_GAME_INDEX = 3;
    public static final int SD_GAME_INDEX = 4;
    public String username;
    public String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "user name: " + username + "\n" + "password: " + password;
    }
}
