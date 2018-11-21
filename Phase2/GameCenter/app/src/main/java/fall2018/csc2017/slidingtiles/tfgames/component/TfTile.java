package fall2018.csc2017.slidingtiles.tfgames.component;

import fall2018.csc2017.slidingtiles.component.BasicTile;

/**
 * Tile for game twenty forty-eight
 */
public class TfTile extends BasicTile {

    /**
     * Constructor of TfTile
     * @param id the id for this tile
     */
    public TfTile(int id){
        this.id = id;
    }

    /**
     * Update the id of this tile
     * @param id new id
     */
    public void setId(int id) {this.id = id; }

    /**
     * Return the id of this tile
     * @return the id of this tile
     */
    public int getId() {
        return this.id;
    }
}
