package fall2018.csc2017.slidingtiles;
import org.junit.Test;

import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;

import static org.junit.Assert.*;

public class HelperTests {
    @Test
    /*
     * Test the arraystack functionalities
     * */
    public void testArrayStack() {
        ArrayStack<Integer> stack = new ArrayStack<>(2000);
        stack.push(3);
        stack.push(4);
        assertEquals(4, (int) stack.pop());
        assertFalse(stack.isEmpty());
        assertEquals(3, (int)stack.pop());
        assertTrue(stack.isEmpty());
        assertEquals(null, stack.pop());
    }

    @Test
    /**
     *
     */
    public void testSequenceBundler(){
        SequenceBundlers sequenceBundlers1 = new SequenceBundlers("xm", 1);
        SequenceBundlers sequenceBundlers2 = new SequenceBundlers("ly", 3);
        assertEquals(1, sequenceBundlers1.getValue());
        assertEquals("xm", sequenceBundlers1.getkey());
        assertEquals(2, sequenceBundlers2.compareTo(sequenceBundlers1));
    }
}
