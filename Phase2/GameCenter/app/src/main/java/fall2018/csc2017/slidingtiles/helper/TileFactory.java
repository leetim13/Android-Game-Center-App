package fall2018.csc2017.slidingtiles.helper;

import fall2018.csc2017.slidingtiles.model.component.BasicTile;
import fall2018.csc2017.slidingtiles.slidinggames.model.component.ImageTile;
import fall2018.csc2017.slidingtiles.slidinggames.model.component.Tile;
import fall2018.csc2017.slidingtiles.sudokugames.model.component.SudokuTile;
import fall2018.csc2017.slidingtiles.tfgames.model.component.TfTile;

/*TODO: add factory pattern for creating tiles in this file*/
public class TileFactory {
    public BasicTile createTile(int id, String type){
        if(type.equals("TfTile")){
            return new TfTile(id);
        }else{
            return new SudokuTile(id);
        }
    }

    public BasicTile createTile(int background, int numRow, int numCol, String type){
        if(type.equals("imageTile")){
            return new ImageTile(background,numCol,numRow);
        } else{
            return new Tile(background,numCol,numRow);
        }
    }
}
