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
        ((TfTile)boardManagerTF.getBoardTF().getTile(1, 1)).setId(1);
        ((TfTile)boardManagerTF.getBoardTF().getTile(1, 3)).setId(1);
        return boardManagerTF;
    }

    @Test
    public void testLeftOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.leftOperation();
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(1, 0)).getId());
    }
}
