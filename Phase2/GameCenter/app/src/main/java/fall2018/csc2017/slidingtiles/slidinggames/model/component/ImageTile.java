package fall2018.csc2017.slidingtiles.slidinggames.model.component;

import android.graphics.Bitmap;

/**
 * Tile  used for changing background functionality
 */
public class ImageTile extends Tile {

    private Bitmap background; // background of this tile

    public Bitmap getBack() {
        return this.background;
    }

    /**
     * initiated the image of the tile
     *
     * @param backgroundId the background id for this tile
     * @param numRows      number of rows in the current board
     * @param numCols      number of columns in the current board
     */
    public ImageTile(int backgroundId, int numRows, int numCols) {
        BitmapCollection collect = BitmapCollection.getInstance();
        Bitmap[][] collection = collect.data();
        id = backgroundId + 1;
        if (id == numRows * numCols) {
            background = null;
            return;
        } else {
            int counter = 1;
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    if (counter == id) {
                        background = collection[i][j];
                        return;
                    }
                    counter++;
                }
            }
        }

        background = collection[numRows - 1][numCols - 1];
    }

    @Override
    public int getId() {
        return id;
    }
}
