package fall2018.csc2017.slidingtiles.controller.system;

import fall2018.csc2017.slidingtiles.model.component.User;

/*
 * singleton controlpanel, hold a user and record the current user state
 * */
public class UserPanel {

    /*
     * A registered user
     * */
    private User user;

    /*
     * Initialize a new UserPanel
     * */
    private static final UserPanel ourInstance = new UserPanel();

    public static UserPanel getInstance() {
        return ourInstance;
    }

    private UserPanel() {
    }

    /*
     * A setter for UserPanel
     * */
    public void setUser(User user) {
        this.user = user;
    }

    /*
     * A getter for UserPanel
     * */
    public User getUser() {
        return this.user;
    }

    /*
     * Return the user's username
     * */
    public String getName() {
        return this.getUser().username;
    }
}
