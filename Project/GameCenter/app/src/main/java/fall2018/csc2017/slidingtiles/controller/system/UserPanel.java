package fall2018.csc2017.slidingtiles.controller.system;

import fall2018.csc2017.slidingtiles.model.component.User;

/**
 * singleton control panel, hold a user and record the current user state
 */
public class UserPanel {

    /**
     * A registered user
     */
    private User user;

    /**
     * A boolean to denotes whether the game has been played
     */
    private boolean gamePlayed = false;

    /**
     * A static variable of a UserPanel currently activated
     */
    private static final UserPanel ourInstance = new UserPanel();

    /**
     * getter for ourInstance
     */
    public static UserPanel getInstance() {
        return ourInstance;
    }

    /**
     * Initialize a new UserPanel
     */
    private UserPanel() {
    }

    /**
     * A setter for UserPanel
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * A getter for UserPanel
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Return the user's username
     */
    public String getName() {
        return this.getUser().username;
    }

    /**
     * set the status of the player to have played this game
     */
    public void play() {
        gamePlayed = true;
    }

    /**
     * test whether the player has played the game
     */
    public boolean isPlayed() {
        return gamePlayed;
    }
}
