package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.sudokugames.controller.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.model.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.model.component.SudokuTile;

import static org.junit.Assert.*;

/**
 * Test class for BoardSudoku and BoardManagerSudoku
 */
public class SudokuBoardAndManagerTest {
    /**
     * The BoardManagerSudoku needing to be tested
     */
    private BoardManagerSudoku boardManagerSudoku;

    /**
     * Construct a new BoardManagerSudoku
     * @return a new BoardManagerSudoku
     */
    private BoardManagerSudoku setBoardManager(){
        return new BoardManagerSudoku(BoardSudoku.LENGTH_OF_SIDE);
    }

    /**
     * Test whether checkBoardValidation works
     */
    @Test
    public void testCheckValidation(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = boardManagerSudoku.getBoard();
        assertTrue(boardManagerSudoku.checkBoardValidation());
    }

    /**
     * Test whether hasWon works
     */
    @Test
    public void testHasWon(){
        boardManagerSudoku = setBoardManager();
        assertFalse(boardManagerSudoku.hasWon());
        boardManagerSudoku.setSudokuTiles();
        assertTrue(boardManagerSudoku.hasWon());
    }

    /**
     * Test whether updateSudokuTiles works
     */
    @Test
    public void testUpdateTiles(){
        boardManagerSudoku = setBoardManager();
        Iterator<SudokuTile> iterator = boardManagerSudoku.getBoard().iterator();
        SudokuTile temp;
        int i = 0;
        while(iterator.hasNext()){
            temp = iterator.next();
            if(temp.getId() == 0) break;
            i++;
        }
        assertTrue(boardManagerSudoku.checkBoardValidation());
        boardManagerSudoku.updateSudokuTiles(10, i);
        assertFalse(boardManagerSudoku.checkBoardValidation());
        iterator = boardManagerSudoku.getBoard().iterator();
        for(int j = 0; j < 82; j++)
            try{iterator.next();}
            catch (NoSuchElementException e){
                System.out.println("Exception catched");
            }
    }

    @Test
    public void testRowAndCol(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = new BoardSudoku(BoardSudoku.LENGTH_OF_SIDE);
        assertEquals(9, boardSudoku.getNumCols());
        assertEquals(9, boardSudoku.getNumRows());
        boardManagerSudoku.getBoard().change();
        assertEquals(9, boardManagerSudoku.getBoardNumOfCols());
        assertEquals(9, boardManagerSudoku.getBoardNumOfRows());
    }

    @Test
    public void testGetTile(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = boardManagerSudoku.getBoard();
        assertSame(boardSudoku.getTile(1, 0), boardManagerSudoku.getBoard().getTile(1, 0));
    }

    @Test
    public void testScore(){
        boardManagerSudoku = setBoardManager();
        assertEquals(0, boardManagerSudoku.getScore());
        boardManagerSudoku.addScore();
        boardManagerSudoku.addScore();
        assertEquals(2, boardManagerSudoku.getScore());
        boardManagerSudoku.minusScore();
        assertEquals(1, boardManagerSudoku.getScore());
        assertEquals(9, boardManagerSudoku.getComplexity());
        assertEquals(4, boardManagerSudoku.getGameIndex());
    }

    @Test
    public void testSwapTiles(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = boardManagerSudoku.getBoard();
        int id1 = boardSudoku.getTile(0, 1).getId();
        int id2 = boardSudoku.getTile(0, 2).getId();
        boardSudoku.swapTiles(0, 1, 0, 2);
        assertEquals(id1, boardSudoku.getTile(0, 2).getId());
        assertEquals(id2, boardSudoku.getTile(0, 1).getId());
    }

    @Test
    public void testIsValidTap(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = boardManagerSudoku.getBoard();
        Iterator<SudokuTile> iterator = boardSudoku.iterator();
        SudokuTile temp;
        int i = 0;
        while(iterator.hasNext()){
            temp = iterator.next();
            if(temp.generated()) break;
            i++;
        }
        assertFalse(boardManagerSudoku.isValidTap(i));
        i = 0;
        iterator = boardSudoku.iterator();
        while(iterator.hasNext()){
            temp = iterator.next();
            if(!temp.generated()) break;
            i++;
        }
        assertTrue(boardManagerSudoku.isValidTap(i));
    }
}
