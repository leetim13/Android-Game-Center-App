package fall2018.csc2017.slidingtiles;
import android.content.Context;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.slidinggames.model.SlidingScore;
import fall2018.csc2017.slidingtiles.sudokugames.model.SudokuScore;
import fall2018.csc2017.slidingtiles.tfgames.model.TfScore;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * test for Score
 */
public class ScoreTest {
    @Test
    public void scoreModelTest() {
        Context ctx = mock(Context.class);
        SlidingScore sl = new SlidingScore(0, ctx);
        assertEquals(null, sl.data());
        TfScore tf = new TfScore(ctx);
        assertEquals(null, tf.data());
        SudokuScore sk = new SudokuScore(ctx);
        assertEquals(null, sk.data());
    }
}
