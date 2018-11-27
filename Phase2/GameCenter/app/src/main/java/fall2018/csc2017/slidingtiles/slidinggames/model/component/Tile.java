package fall2018.csc2017.slidingtiles.slidinggames.model.component;

import fall2018.csc2017.slidingtiles.model.component.BasicTile;
import fall2018.csc2017.slidingtiles.R;

/**
 * A Tile in a sliding tiles puzzle.
 */
public class Tile extends BasicTile {

    /**
     * The background id to find the tile image.
     */
    private int background;

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        return background;
    }

    /**
     * Return the tile id.
     *
     * @return the tile id
     */
    public int getId() {
        return id;
    }

//    /**
//     * A Tile with id and background. The background may not have a corresponding image.
//     *
//     * @param id         the id
//     * @param background the background
//     */
//    public Tile(int id, int background) {
//        this.id = id;
//        this.background = background;
//    }

    public Tile() {}

    /**
     * A tile with a background id; look up and set the id.
     *
     * @param backgroundId
     */
    public Tile(int backgroundId, int numRows, int numCols) {
        id = backgroundId + 1;
        // This looks so ugly.
        if(id == numRows*numCols){
            background = R.drawable.tile_25;
        }else{
        switch (backgroundId + 1) {
            case 1:
                background = R.drawable.tile_1;
                break;
            case 2:
                background = R.drawable.tile_2;
                break;
            case 3:
                background = R.drawable.tile_3;
                break;
            case 4:
                background = R.drawable.tile_4;
                break;
            case 5:
                background = R.drawable.tile_5;
                break;
            case 6:
                background = R.drawable.tile_6;
                break;
            case 7:
                background = R.drawable.tile_7;
                break;
            case 8:
                background = R.drawable.tile_8;
                break;
            case 9:
                background = R.drawable.tile_9;
                break;
            case 10:
                background = R.drawable.tile_10;
                break;
            case 11:
                background = R.drawable.tile_11;
                break;
            case 12:
                background = R.drawable.tile_12;
                break;
            case 13:
                background = R.drawable.tile_13;
                break;
            case 14:
                background = R.drawable.tile_14;
                break;
            case 15:
                background = R.drawable.tile_15;
                break;
            case 16:
                background = R.drawable.tile_16;
                break;
            case 17:
                background = R.drawable.tile_17;
                break;
            case 18:
                background = R.drawable.tile_18;
                break;
            case 19:
                background = R.drawable.tile_19;
                break;
            case 20:
                background = R.drawable.tile_20;
                break;
            case 21:
                background = R.drawable.tile_21;
                break;
            case 22:
                background = R.drawable.tile_22;
                break;
            case 23:
                background = R.drawable.tile_23;
                break;
            case 24:
                background = R.drawable.tile_24;
                break;
            case 25:
                background = R.drawable.tile_25;
                break;
            default:
                background = R.drawable.tile_25;
        }
        }
    }
}
