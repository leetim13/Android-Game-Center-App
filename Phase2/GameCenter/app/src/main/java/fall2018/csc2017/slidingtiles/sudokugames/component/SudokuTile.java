package fall2018.csc2017.slidingtiles.sudokugames.component;

import fall2018.csc2017.slidingtiles.component.BasicTile;

public class SudokuTile extends BasicTile {
    public SudokuTile(int id){
        this.id = id;
    }
    public void setId(int id){
        this.id = id;
    }
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Return the background id.
     *
     * @return the background id
     */
    public int getBackground() {
        System.out.println("id is..." + id);
        int background = id;
        return background;
    }
}
