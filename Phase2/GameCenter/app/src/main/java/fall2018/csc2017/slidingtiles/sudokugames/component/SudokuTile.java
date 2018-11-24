package fall2018.csc2017.slidingtiles.sudokugames.component;

import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.component.BasicTile;

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

    private void assignBackground (int id) {
        switch (id) {
            case 1:
                background = R.drawable.tf1;
                break;
            case 2:
                background = R.drawable.tf2;
                break;
            case 3:
                background = R.drawable.tf3;
                break;
            case 4:
                background = R.drawable.tf4;
                break;
            case 5:
                background = R.drawable.tf5;
                break;
            case 6:
                background = R.drawable.tf6;
                break;
            case 7:
                background = R.drawable.tf7;
                break;
            case 8:
                background = R.drawable.tf8;
                break;
            case 9:
                background = R.drawable.tf9;
                break;
            default:
                background = R.drawable.tf0;
                break;
        }

    }
}
