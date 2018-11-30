package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.helper.TileFactory;
import fall2018.csc2017.slidingtiles.tfgames.controller.BoardManagerTF;
import fall2018.csc2017.slidingtiles.tfgames.controller.MovementControllerTF;
import fall2018.csc2017.slidingtiles.tfgames.model.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.model.component.TfTile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TestMovementControllerTF {
    MovementControllerTF movementControllerTF = new MovementControllerTF();
    BoardManagerTF boardManagerTF = new BoardManagerTF();
    TileFactory tileFactory = new TileFactory();

    @Test
    public void testProcessTapMovement(){
        BoardTF boardTF = generateBoardTF();
        boardManagerTF.setBoardTF(boardTF);
        movementControllerTF.setBoardManager(boardManagerTF);
        Context context = mock(Context.class);
        movementControllerTF.processTapMovement(context, 0);
        assertTrue(boardManagerTF.getBoard().getTilesCopy()[0][1].getId() == 1);
    }

    private BoardTF generateBoardTF(){
        List<TfTile> tiles = new ArrayList<>();
        for(int i = 0; i < 16; i ++){
            if(i != 5) {
                tiles.add(i, (TfTile) tileFactory.createTile(0, "TfTile"));
            }else{
                tiles.add(i, (TfTile) tileFactory.createTile(1, "TfTile"));
            }
        }
        BoardTF boardTF = new BoardTF(4, tiles);
        return boardTF;
    }
}
