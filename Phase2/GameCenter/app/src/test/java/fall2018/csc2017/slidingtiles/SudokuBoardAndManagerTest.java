package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Iterator;

import fall2018.csc2017.slidingtiles.sudokugames.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.component.SudokuTile;
import fall2018.csc2017.slidingtiles.sudokugames.manager.BoardManagerSudoku;

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
    }
}
