package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.controller.MovementControllerST;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MovementControllersTest {
    /*
    * test added for sliding tile movement controller
    * */
    @Test
    public void testSLMovement() {
        MovementControllerST move = new MovementControllerST();
        Context ctx = mock(Context.class);
        BoardManager manager = mock(BoardManager.class);
        move.setBoardManager(manager);
        when(manager.isValidTap(10)).thenReturn(false);
//        move.processTapMovement(ctx, 10);
        assertTrue(move.stateStack.isEmpty());
    }
}
