package fall2018.csc2017.slidingtiles.system;

import fall2018.csc2017.slidingtiles.component.User;

/*
 * singleton controlpanel, hold a user and record the current user state
 * */
public class UserPanel {

    private User user;

    private static final UserPanel ourInstance = new UserPanel();

    public final static UserPanel getInstance() {
        return ourInstance;
    }

    private UserPanel() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public String getName() {
        return this.getUser().username;
    }
}