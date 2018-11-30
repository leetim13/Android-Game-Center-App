package fall2018.csc2017.slidingtiles.slidinggames.model.component;

import android.graphics.Bitmap;

/**
 * singleton pattern to display the load image
 */
public class BitmapCollection {

    /**
     * the BitmapCollection to store the image.
     */
    private static final BitmapCollection ourInstance = new BitmapCollection();

    /**
     * whether we will load the image or use basic digit tile background for in sliding game.
     */
    private boolean locked = true; // don't use image if it's locked

    /**
     * the 2d bitmap to store the image
     */
    private Bitmap[][] collection;

    /**
     * the getter for ourInstance.
     */
    public static BitmapCollection getInstance() {
        return ourInstance;
    }

    /**
     * a private constructor of BitmapCollection followed the singleton pattern.
     */
    private BitmapCollection() {
    }

    /**
    * whether to lock the image tile functionality
    * @param lock: whether to lock it
    */
    public void latch(boolean lock) {
        locked = lock;
    }

    /**
    * check whether the image is locked
    */
    public boolean isLocked() {
        return locked;
    }

    /**
     * the setter for collection
     */
    public void loadImage(Bitmap[][] map) {
        collection = map;
    }

    /**
     * the getter for collection
     */
    public Bitmap[][] data() {
        return collection;
    }
}
