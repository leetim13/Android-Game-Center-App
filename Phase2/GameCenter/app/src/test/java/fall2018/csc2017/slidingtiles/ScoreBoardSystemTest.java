package fall2018.csc2017.slidingtiles;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.slidingtiles.controller.system.ScoreBoardSystem;
import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.model.GameScore;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ScoreBoardSystemTest {
    @Test
    public void systemTest() {
        GameScore score = mock(GameScore.class);
        Map<String, int[]> preparedMap = new HashMap<>();

        preparedMap.put("asd", new int[]{1,2,3,4});
        when(score.data()).thenReturn(preparedMap);
        ScoreBoardSystem sys = new ScoreBoardSystem(new GameScore[]{score});
        List<SequenceBundlers> bd = sys.displayScore(0);
        SequenceBundlers a = bd.get(0);
        assertEquals("asd", a.getkey());
        assertEquals(1, a.getValue());

    }
}
