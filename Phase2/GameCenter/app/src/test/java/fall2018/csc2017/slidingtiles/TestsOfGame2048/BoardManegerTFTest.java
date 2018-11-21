package fall2018.csc2017.slidingtiles.TestsOfGame2048;

import org.junit.Test;

import java.util.Iterator;

import fall2018.csc2017.slidingtiles.tfgames.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.component.TfTile;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;

import static org.junit.Assert.*;

public class BoardManegerTFTest {
    private BoardManagerTF boardManagerTF;

    private BoardManagerTF setBoardManagerTF(){
        boardManagerTF = new BoardManagerTF(BoardTF.LENGTH_OF_SIDE);
        return boardManagerTF;
    }

    @Test
    public void testLeftOperationSimple(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoardTF().getTile(1, 1).setId(1);
        boardManagerTF.getBoardTF().getTile(1, 3).setId(1);
        boardManagerTF.touchMove('l');
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(1, 0)).getId());
    }

    @Test
    public void testLeftOperationComplex(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoardTF().getTile(0, 3).setId(1);
        boardManagerTF.getBoardTF().getTile(2, 0).setId(1);
        boardManagerTF.getBoardTF().getTile(2, 3).setId(3);
        boardManagerTF.getBoardTF().getTile(3, 2).setId(2);
        boardManagerTF.getBoardTF().getTile(3, 3).setId(3);
        boardManagerTF.touchMove('l');
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 0)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(2, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(2, 1)).getId());
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(3, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(3, 1)).getId());
    }

    @Test
    public void testRightOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoardTF().getTile(1, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(1, 1).setId(1);
        boardManagerTF.getBoardTF().getTile(2, 0).setId(1);
        boardManagerTF.getBoardTF().getTile(2, 1).setId(4);
        boardManagerTF.getBoardTF().getTile(2, 2).setId(3);
        boardManagerTF.getBoardTF().getTile(2, 3).setId(1);
        boardManagerTF.getBoardTF().getTile(3, 0).setId(1);
        boardManagerTF.getBoardTF().getTile(3, 1).setId(4);
        boardManagerTF.touchMove('u');
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(0, 0)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 1)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(0, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 3)).getId());
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(1, 0)).getId());
        assertEquals(5, (boardManagerTF.getBoardTF().getTile(1, 1)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(1, 2)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(2, 2)).getId());
    }

    @Test
    public void testUpOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoardTF().getTile(0, 0).setId(1);
        boardManagerTF.getBoardTF().getTile(0, 1).setId(1);
        boardManagerTF.getBoardTF().getTile(0, 3).setId(1);
        boardManagerTF.getBoardTF().getTile(1, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(1, 1).setId(2);
        boardManagerTF.getBoardTF().getTile(2, 0).setId(4);
        boardManagerTF.getBoardTF().getTile(3, 0).setId(3);
        boardManagerTF.getBoardTF().getTile(3, 1).setId(1);
        boardManagerTF.touchMove('r');
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 2)).getId());
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(0, 3)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(1, 3)).getId());
        assertEquals(4, (boardManagerTF.getBoardTF().getTile(2, 3)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(3, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(3, 3)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(0, 1)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(1, 2)).getId());
    }

    @Test
    public void testdownOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoardTF().getTile(0, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(0, 1).setId(1);
        boardManagerTF.getBoardTF().getTile(0, 2).setId(3);
        boardManagerTF.getBoardTF().getTile(0, 3).setId(1);
        boardManagerTF.getBoardTF().getTile(1, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(1, 1).setId(5);
        boardManagerTF.getBoardTF().getTile(1, 2).setId(1);
        boardManagerTF.touchMove('d');
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(2, 1)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(2, 2)).getId());
        assertEquals(3, (boardManagerTF.getBoardTF().getTile(3, 0)).getId());
        assertEquals(5, (boardManagerTF.getBoardTF().getTile(3, 1)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(3, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoardTF().getTile(3, 3)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(0, 3)).getId());
        assertEquals(0, (boardManagerTF.getBoardTF().getTile(1, 1)).getId());
    }

    @Test
    public void testSwapTile(){
        boardManagerTF = setBoardManagerTF();
        BoardTF boardTF = boardManagerTF.getBoardTF();
        boardManagerTF.getBoardTF().getTile(0, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(0, 1).setId(1);
        boardTF.swapTiles(0, 0, 0, 1);

        assertEquals(1, (boardManagerTF.getBoardTF().getTile(0, 0)).getId());
        assertEquals(2, (boardManagerTF.getBoardTF().getTile(0, 1)).getId());
    }

    @Test
    public void testBoardTFIterator(){
        boardManagerTF = setBoardManagerTF();
        BoardTF boardTF = boardManagerTF.getBoardTF();
        Iterator<TfTile> iterator = boardTF.iterator();
        boardManagerTF.getBoardTF().getTile(0, 0).setId(2);
        boardManagerTF.getBoardTF().getTile(0, 1).setId(1);

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next().getId());
        assertEquals(1, iterator.next().getId());
    }
}
