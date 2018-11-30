package fall2018.csc2017.slidingtiles.tfgames.model.component;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.model.component.BasicTile;

/**
 * Tile for game twenty forty-eight
 */
public class TfTile extends BasicTile {

    /**
     * Constructor of TfTile
     * @param id the id for this tile
     */

    /**
     * Update the id of this tile
     *
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the id of this tile
     *
     * @return the id of this tile
     */
    public int getId() {
        return this.id;
    }


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
        System.out.println("id is..." + id);
        assignBackground(id);
        return background;
    }

    /**
     * A Tile with id and background. The background may not have a corresponding image.
     *
     * @param id the id
     */
    public TfTile(int id) {
        this.id = id;
        assignBackground(id);
    }

    private void assignBackground(int id) {
        switch (id) {
            case 1:
                background = R.drawable.tftile_01;
                break;
            case 2:
                background = R.drawable.tftile_02;
                break;
            case 3:
                background = R.drawable.tftile_03;
                break;
            case 4:
                background = R.drawable.tftile_04;
                break;
            case 5:
                background = R.drawable.tftile_05;
                break;
            case 6:
                background = R.drawable.tftile_06;
                break;
            case 7:
                background = R.drawable.tftile_07;
                break;
            case 8:
                background = R.drawable.tftile_08;
                break;
            case 9:
                background = R.drawable.tftile_09;
                break;
            case 10:
                background = R.drawable.tftile_10;
                break;
            case 11:
                background = R.drawable.tftile_11;
                break;
            default:
                background = R.drawable.tf0;
                break;
        }

    }
}
