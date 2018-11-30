package fall2018.csc2017.slidingtiles;
import org.junit.Test;

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
}
