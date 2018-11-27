package fall2018.csc2017.slidingtiles.sudokugames.model.component;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.model.component.BasicTile;

public class SudokuTile extends BasicTile {

    public SudokuTile(int id){
        this.id = id;
        assignBackground(id);
    }


    public void setId(int id){
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }
    private boolean isGenerated = false;

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

    /*
    * set the tile to solid tile
    * */
    public void setTrait(boolean isGenerated) {
        this.isGenerated = isGenerated;
    }

    public boolean generated() {
        return this.isGenerated;
    }

    private void assignBackground (int id) {
        switch (id) {
            case 1:
                background = (generated()) ? R.drawable.ngtf1 : R.drawable.tf1;
                break;
            case 2:
                background = (generated()) ? R.drawable.ngtf2 : R.drawable.tf2;
                break;
            case 3:
                background = (generated()) ? R.drawable.ngtf3 : R.drawable.tf3;
                break;
            case 4:
                background = (generated()) ? R.drawable.ngtf4 : R.drawable.tf4;
                break;
            case 5:
                background = (generated()) ? R.drawable.ngtf5 : R.drawable.tf5;
                break;
            case 6:
                background = (generated()) ? R.drawable.ngtf6 : R.drawable.tf6;
                break;
            case 7:
                background = (generated()) ? R.drawable.ngtf7 : R.drawable.tf7;
                break;
            case 8:
                background = (generated()) ? R.drawable.ngtf8 : R.drawable.tf8;
                break;
            case 9:
                background = (generated()) ? R.drawable.ngtf9 : R.drawable.tf9;
                break;
            case -1: // for selected case
                background = R.drawable.sudoku_selected;
                break;
            default:
                background = R.drawable.tf0;
                break;
        }
    }
}
