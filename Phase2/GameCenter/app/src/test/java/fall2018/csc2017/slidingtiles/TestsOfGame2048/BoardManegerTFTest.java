package fall2018.csc2017.slidingtiles.TestsOfGame2048;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.TFGame.Components.BoardTF;
import fall2018.csc2017.slidingtiles.TFGame.Components.TfTile;
import fall2018.csc2017.slidingtiles.TFGame.Manager.BoardManagerTF;

import static org.junit.Assert.*;

public class BoardManegerTFTest {
    private BoardManagerTF boardManagerTF;

    private BoardManagerTF setBoardManagerTF(){
        BoardManagerTF boardManagerTF = new BoardManagerTF(BoardTF.LENGTH_OF_SIDE);
        return boardManagerTF;
    }

    @Test
    public void testLeftOperationSimple(){
        boardManagerTF = setBoardManagerTF();
        ((TfTile)boardManagerTF.getBoardTF().getTile(1, 1)).setId(1);
        ((TfTile)boardManagerTF.getBoardTF().getTile(1, 3)).setId(1);
        boardManagerTF.leftOperation();
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(1, 0)).getId());
    }

    @Test
    public void testLeftOperationComplex(){
        boardManagerTF = setBoardManagerTF();
        ((TfTile)boardManagerTF.getBoardTF().getTile(0, 3)).setId(1);
        ((TfTile)boardManagerTF.getBoardTF().getTile(2, 0)).setId(1);
        ((TfTile)boardManagerTF.getBoardTF().getTile(2, 3)).setId(3);
        ((TfTile)boardManagerTF.getBoardTF().getTile(3, 2)).setId(2);
        ((TfTile)boardManagerTF.getBoardTF().getTile(3, 3)).setId(3);
        boardManagerTF.leftOperation();
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 0)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(2, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(2, 1)).getId());
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(3, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(3, 1)).getId());
    }
}
