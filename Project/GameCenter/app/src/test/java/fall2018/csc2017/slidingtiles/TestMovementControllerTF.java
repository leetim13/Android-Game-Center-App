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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test the MovementControllerTF
 */
public class TestMovementControllerTF {

    /**
     * MovementControllerTF being tested
     */
    MovementControllerTF movementControllerTF = new MovementControllerTF();

    /**
     * BoardManager and tileFactory Inside it
     */
    BoardManagerTF boardManagerTF = new BoardManagerTF();
    TileFactory tileFactory = new TileFactory();

    @Test
    /**
     * test if the processTapMovement and the helper getBoardTFCopy work fine.
     */
    public void testProcessTapMovement(){
        Context context = mock(Context.class);
        BoardTF boardTF = generateBoardTF();
        boardManagerTF.setBoardTF(boardTF);
        movementControllerTF.setBoardManager(boardManagerTF);
        movementControllerTF.processTapMovement(context, 0);
        assertTrue(boardManagerTF.getBoard().getTilesCopy()[0][1].getId() == 1);
    }

    /**
     * the helper to generate a board with only one non-empty tile.
     */
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
