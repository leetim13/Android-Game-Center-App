package fall2018.csc2017.slidingtiles.sudokugames.component;

import fall2018.csc2017.slidingtiles.component.BasicTile;

public class SudokuTile extends BasicTile {
    public void setId(int id){
        this.id = id;
    }
    @Override
    public int getId() {
        return this.id;
    }
}
