package fall2018.csc2017.slidingtiles.model;

import android.graphics.Bitmap;

import java.io.Serializable;

import fall2018.csc2017.slidingtiles.interfaces.Externalable;

/*
*model for storing the user's picture
* */
public class UserPicture implements Serializable, Externalable {
    private String name;
    private String profilePicPath;
    private String imageTilePath;
    private int id;
    private boolean isLoaded = false;
    public final static String PROFILE_PIC_FORMAT = "profile_";
    public final static String IMAGETILE_FORMAT = "image";
    public UserPicture(String username) {

    }
    /*
     // get the resource of picture from th setting paths
    * */
    @Override
    public Bitmap getResource(int type) {
        return null;
    }
    /*
    * join the two strings into a format of .suffix
    * */
    private String join(String str1, String str2, String suffix) {
        return "";
    }

    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }
}
