package fall2018.csc2017.slidingtiles.slidinggames.model.component;

import android.graphics.Bitmap;

/*
* singleton pattern to display the load image
* */
public class BitmapCollection {

    private static final BitmapCollection ourInstance = new BitmapCollection();
    private boolean locked = true;
    private Bitmap[][] collection;
    public static BitmapCollection getInstance() {
        return ourInstance;
    }

    private BitmapCollection() {

    }
    /*
    * whether to lock the image tile functionality
    * @param locked: whether to lock it
    * */
    public void latch(boolean lock) {
        locked = lock;
    }
    /*
    * check whether the image is locked
    * */
    public boolean isLocked() {
        return locked;
    }

    public void loadImage(Bitmap[][] map) {
        collection = map;
    }

    public Bitmap[][] data() {
        return collection;
    }
}
