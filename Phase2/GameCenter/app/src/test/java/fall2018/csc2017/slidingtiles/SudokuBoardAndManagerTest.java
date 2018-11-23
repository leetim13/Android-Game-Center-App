package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import java.util.Iterator;

import fall2018.csc2017.slidingtiles.sudokugames.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.component.SudokuTile;
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
        boardManagerSudoku.updateSudokuTiles(10, i);
        assertFalse(boardManagerSudoku.checkBoardValidation());
    }
}
