package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.sudokugames.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.manager.BoardManagerSudoku;

import static org.junit.Assert.*;

public class SudokuBoardAndManagerTest {
    private BoardManagerSudoku boardManagerSudoku;

    private BoardManagerSudoku setBoardManager(){
        return new BoardManagerSudoku(BoardSudoku.LENGTH_OF_SIDE);
    }

    @Test
    public void testCheckValidation(){
        boardManagerSudoku = setBoardManager();
        BoardSudoku boardSudoku = boardManagerSudoku.getBoard();
        assertTrue(boardManagerSudoku.checkBoardValidation());
    }

    @Test
    public void testHasWon(){
        boardManagerSudoku = setBoardManager();
        assertFalse(boardManagerSudoku.hasWon());
        boardManagerSudoku.setSudokuTiles();
        assertTrue(boardManagerSudoku.hasWon());
    }
}
