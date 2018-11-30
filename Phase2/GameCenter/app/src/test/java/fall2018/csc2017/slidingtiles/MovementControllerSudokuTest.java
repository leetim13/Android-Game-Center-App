package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.controller.MovementControllerST;
import fall2018.csc2017.slidingtiles.sudokugames.controller.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.controller.MovementControllerSK;
import fall2018.csc2017.slidingtiles.sudokugames.model.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.model.component.Sudoku;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * test for sudoku movement controller
 */
public class MovementControllerSudokuTest {

    @Test
    public void testMovementControllerSK(){
        BoardManagerSudoku boardManagerSudoku = new BoardManagerSudoku(9);
        MovementControllerSK move = new MovementControllerSK(boardManagerSudoku);
        Context ctx = mock(Context.class);
        move.setBoardManager(boardManagerSudoku);
        if(!(boardManagerSudoku.isValidTap(10))){
            move.processTapMovement(ctx, 10);
            assertEquals(0, boardManagerSudoku.getScore());
        }
        assertFalse(move.selected());
        move.changeSelect();
        assertTrue(move.selected());
    }



}
