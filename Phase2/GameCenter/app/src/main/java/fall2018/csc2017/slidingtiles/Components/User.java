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
package fall2018.csc2017.slidingtiles.Components;

public class User {

    public static final int TILEGAMEINDEX = 0;
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
