package fall2018.csc2017.slidingtiles.component;

import android.support.annotation.NonNull;

import java.io.Serializable;

/*
* abstract of the tiles, standard method
* */
public abstract class BasicTile implements Comparable <BasicTile>, Serializable {
    public int id;
    public abstract int getId();

    @Override
    public int compareTo(@NonNull BasicTile o) {
        return o.id - this.id;
    }
}
