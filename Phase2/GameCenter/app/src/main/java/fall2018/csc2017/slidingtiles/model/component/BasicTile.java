package fall2018.csc2017.slidingtiles.model.component;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * abstract of the tiles, standard method
 */
public abstract class BasicTile implements Comparable <BasicTile>, Serializable {

    /**
     * The id of the BasicTile
     */
    public int id;

    /**
     * Return the id of the BasicTile
     * @return the id of the BasicTile
     */
    public abstract int getId();

    /**
     * Calculate the difference between the id of this BasicTile and
     * the id of the other BasicTile o
     * @param o the other BasicTile
     * @return the difference between the id of this BasicTile and
     *          the id of the other BasicTile o
     */
    @Override
    public int compareTo(@NonNull BasicTile o) {
        return o.id - this.id;
    }
}
