package fall2018.csc2017.slidingtiles.TFGame.Components;

import fall2018.csc2017.slidingtiles.Components.BasicTile;

public class TfTile extends BasicTile {
    public TfTile(int id){
        this.id = id;
    }
    public void setId(int id) {this.id = id; }
    public int getId() {
        return this.id;
    }
}
