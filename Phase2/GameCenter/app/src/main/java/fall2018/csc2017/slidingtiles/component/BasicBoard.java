package fall2018.csc2017.slidingtiles.component;

import java.io.Serializable;
import java.util.Observable;

public abstract class BasicBoard extends Observable implements Serializable {
    /**
     * Return the number of rows in the basicboard
     * @return the number of rows in the basicboard
     */
    public abstract int getNumRows();

    /**
     * Return the number of columns in the basicboard
     * @return the number of columns in the basicboard
     */
    public abstract int getNumCols();

    /**
     * Swap the positions of  tile1 at (row1, col1) and tile2 at (row2, col2)
     * @param row1 the row number of tile1
     * @param col1 the column number of tile1
     * @param row2 the row number of tile2
     * @param col2 the column number of tile2
     */
    public abstract void swapTiles(int row1, int col1, int row2, int col2);
}
