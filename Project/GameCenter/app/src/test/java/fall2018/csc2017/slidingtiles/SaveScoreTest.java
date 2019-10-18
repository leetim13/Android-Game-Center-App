package fall2018.csc2017.slidingtiles;
import android.content.Context;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.helper.SaveScore;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * test for saveScore
 */
public class SaveScoreTest {
    // test the function of score saving
    @Test
    public void testSaveScore() {

        SaveScore sc = new SaveScore();
        Context ctx = mock(Context.class);
        sc.saveScoreIntoMap(ctx, 1, 1);

    }
}
