package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Iterator;

import fall2018.csc2017.slidingtiles.tfgames.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.component.TfTile;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;

import static org.junit.Assert.*;

/**
 * Tests of methods in BoardManagerTF and its BoardTF
 */
public class BoardManagerTFTest {
    private BoardManagerTF boardManagerTF;

    /**
     * Return a new BoardManagerTF
     * @return a new BoardManagerTF
     */
    private BoardManagerTF setBoardManagerTF(){
        boardManagerTF = new BoardManagerTF(BoardTF.LENGTH_OF_SIDE);
        return boardManagerTF;
    }

    /**
     * Test whether leftOperation works (simple)
     */
    @Test
    public void testLeftOperationSimple(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoard().getTile(1, 1).setId(1);
        boardManagerTF.getBoard().getTile(1, 3).setId(1);
        boardManagerTF.touchMove(BoardManagerTF.LEFT_SIGNAL);
        assertEquals(2, (boardManagerTF.getBoard().getTile(1, 0)).getId());
    }

    /**
     * Test whether leftOperation works (complex)
     */
    @Test
    public void testLeftOperationComplex(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoard().getTile(0, 3).setId(1);
        boardManagerTF.getBoard().getTile(2, 0).setId(1);
        boardManagerTF.getBoard().getTile(2, 3).setId(3);
        boardManagerTF.getBoard().getTile(3, 2).setId(2);
        boardManagerTF.getBoard().getTile(3, 3).setId(3);
        boardManagerTF.touchMove(BoardManagerTF.LEFT_SIGNAL);
        assertEquals(1, (boardManagerTF.getBoard().getTile(0, 0)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(2, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(2, 1)).getId());
        assertEquals(2, (boardManagerTF.getBoard().getTile(3, 0)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(3, 1)).getId());
    }

    /**
     * Test whether rightOperation works
     */
    @Test
    public void testRightOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoard().getTile(1, 0).setId(2);
        boardManagerTF.getBoard().getTile(1, 1).setId(1);
        boardManagerTF.getBoard().getTile(2, 0).setId(1);
        boardManagerTF.getBoard().getTile(2, 1).setId(4);
        boardManagerTF.getBoard().getTile(2, 2).setId(3);
        boardManagerTF.getBoard().getTile(2, 3).setId(1);
        boardManagerTF.getBoard().getTile(3, 0).setId(1);
        boardManagerTF.getBoard().getTile(3, 1).setId(4);
        boardManagerTF.touchMove(BoardManagerTF.UP_SIGNAL);
        assertEquals(2, (boardManagerTF.getBoard().getTile(0, 0)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(0, 1)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(0, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(0, 3)).getId());
        assertEquals(2, (boardManagerTF.getBoard().getTile(1, 0)).getId());
        assertEquals(5, (boardManagerTF.getBoard().getTile(1, 1)).getId());
    }

    /**
     * Test whether upOperation works
     */
    @Test
    public void testUpOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoard().getTile(0, 0).setId(1);
        boardManagerTF.getBoard().getTile(0, 1).setId(1);
        boardManagerTF.getBoard().getTile(0, 3).setId(1);
        boardManagerTF.getBoard().getTile(1, 0).setId(2);
        boardManagerTF.getBoard().getTile(1, 1).setId(2);
        boardManagerTF.getBoard().getTile(2, 0).setId(4);
        boardManagerTF.getBoard().getTile(3, 0).setId(3);
        boardManagerTF.getBoard().getTile(3, 1).setId(1);
        boardManagerTF.touchMove(BoardManagerTF.RIGHT_SIGNAL);
        assertEquals(1, (boardManagerTF.getBoard().getTile(0, 2)).getId());
        assertEquals(2, (boardManagerTF.getBoard().getTile(0, 3)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(1, 3)).getId());
        assertEquals(4, (boardManagerTF.getBoard().getTile(2, 3)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(3, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(3, 3)).getId());
    }

    /**
     * Test whether downOperation works
     */
    @Test
    public void testdownOperation(){
        boardManagerTF = setBoardManagerTF();
        boardManagerTF.getBoard().getTile(0, 0).setId(2);
        boardManagerTF.getBoard().getTile(0, 1).setId(1);
        boardManagerTF.getBoard().getTile(0, 2).setId(3);
        boardManagerTF.getBoard().getTile(0, 3).setId(1);
        boardManagerTF.getBoard().getTile(1, 0).setId(2);
        boardManagerTF.getBoard().getTile(1, 1).setId(5);
        boardManagerTF.getBoard().getTile(1, 2).setId(1);
        boardManagerTF.touchMove(BoardManagerTF.DOWN_SIGNAL);
        assertEquals(1, (boardManagerTF.getBoard().getTile(2, 1)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(2, 2)).getId());
        assertEquals(3, (boardManagerTF.getBoard().getTile(3, 0)).getId());
        assertEquals(5, (boardManagerTF.getBoard().getTile(3, 1)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(3, 2)).getId());
        assertEquals(1, (boardManagerTF.getBoard().getTile(3, 3)).getId());
    }

    /**
     * Test whether swapTile works
     */
    @Test
    public void testSwapTile(){
        boardManagerTF = setBoardManagerTF();
        BoardTF boardTF = boardManagerTF.getBoard();
        boardManagerTF.getBoard().getTile(0, 0).setId(2);
        boardManagerTF.getBoard().getTile(0, 1).setId(1);
        boardTF.swapTiles(0, 0, 0, 1);

        assertEquals(1, (boardManagerTF.getBoard().getTile(0, 0)).getId());
        assertEquals(2, (boardManagerTF.getBoard().getTile(0, 1)).getId());
        assertEquals(4, boardTF.getNumCols());
        assertEquals(4, boardTF.getNumRows());
        assertEquals(4, boardManagerTF.getComplexity());
        assertEquals(4, boardManagerTF.getBoardNumOfCols());
        assertEquals(4, boardManagerTF.getBoardNumOfRows());
    }

    /**
     * Test whether the iterator of boardTF works
     */
    @Test
    public void testBoardTFIterator(){
        boardManagerTF = setBoardManagerTF();
        BoardTF boardTF = boardManagerTF.getBoard();
        Iterator<TfTile> iterator = boardTF.iterator();
        boardManagerTF.getBoard().getTile(0, 0).setId(2);
        boardManagerTF.getBoard().getTile(0, 1).setId(1);

        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next().getId());
        assertEquals(1, iterator.next().getId());
    }

    /**
     * Test whether hasLost works
     */
    @Test
    public void testLose(){
        boardManagerTF = setBoardManagerTF();
        assertFalse(boardManagerTF.hasLost());
        for(int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++)
            for(int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++)
                boardManagerTF.getBoard().getTile(i, j).setId(1);
        assertTrue(boardManagerTF.hasLost());
    }

    /**
     * Test whether hasWon works
     */
    @Test
    public void testWin(){
        boardManagerTF = setBoardManagerTF();
        assertFalse(boardManagerTF.hasWon());
        boardManagerTF.getBoard().getTile(0, 1).setId(11);
        assertTrue(boardManagerTF.hasWon());
    }

    /**
     * Test whether score related methods works
     */
    @Test
    public void testScore(){
        boardManagerTF = setBoardManagerTF();
        assertEquals(0, boardManagerTF.getScore());
        boardManagerTF.addScore();
        boardManagerTF.addScore();
        assertEquals(2, boardManagerTF.getScore());
        boardManagerTF.minusScore();
        assertEquals(1, boardManagerTF.getScore());
    }

    @Test
    public void testGenerateNewTile(){
        boardManagerTF = setBoardManagerTF();
        assertEquals(16, boardManagerTF.countNumOfBlankTiles());
        boardManagerTF.getBoard().getTile(0, 2).setId(3);
        boardManagerTF.getBoard().getTile(0, 3).setId(1);
        boardManagerTF.generateNewTile();
        assertEquals(13, boardManagerTF.countNumOfBlankTiles());
    }
}
