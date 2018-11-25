package fall2018.csc2017.slidingtiles.interfaces;

import android.graphics.Bitmap;

/*
* define the model for handling external resources
* */
public interface Externalable {
    String getName(); // must have a name
    int getId(); // must own a id since the external system may use a swap algorithm
    void setId(int id); // set id function should be implemented
    Bitmap getResource(int type); // must implement getResource since we need to get resource
}
