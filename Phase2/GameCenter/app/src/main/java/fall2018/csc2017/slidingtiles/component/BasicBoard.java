package fall2018.csc2017.slidingtiles.component;

import java.io.Serializable;
import java.util.Observable;

public abstract class BasicBoard extends Observable implements Serializable {
    public abstract int getNumRows();
    public abstract int getNumCols();
    public abstract void swapTiles(int row1, int col1, int row2, int col2);
}
