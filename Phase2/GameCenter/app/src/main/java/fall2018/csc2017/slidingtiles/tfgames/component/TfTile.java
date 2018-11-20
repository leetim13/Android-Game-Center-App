package fall2018.csc2017.slidingtiles.tfgames.component;

import fall2018.csc2017.slidingtiles.component.BasicTile;

public class TfTile extends BasicTile {
    public TfTile(int id){
        this.id = id;
    }
    public void setId(int id) {this.id = id; }
    public int getId() {
        return this.id;
    }
}
