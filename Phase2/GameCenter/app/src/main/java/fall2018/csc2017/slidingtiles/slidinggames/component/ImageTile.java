package fall2018.csc2017.slidingtiles.slidinggames.component;

import android.graphics.Bitmap;

/*Tile  used for changing background functionality
* */
public class ImageTile extends Tile {

    private Bitmap background; // background of this tile
    public static Bitmap[][] bitmapCollection; // turned into 5x5 whenever the image is chosen
    public Bitmap getBack() {
        return this.background;
    }
    /*
    initiated the image of the tile
    * @param int backgroundId
    * @param int numRows
    * @param int numCols
    * */
    public ImageTile(int backgroundId,int numRows, int numCols) {
        id = backgroundId + 1;
        if(id == numRows*numCols){
            background = null;
            return;
        }else {
            int counter = 1;
            for (int i = 0; i < numRows; i ++) {
                for (int j = 0; j < numCols; j++) {
                    if (counter == id) {
                        background = bitmapCollection[i][j];
                        return;
                    }
                    counter ++;
                }
            }
        }

        background = bitmapCollection[numRows- 1][numCols - 1];
    }

    @Override
    public int getId() {
        return id;
    }
}
