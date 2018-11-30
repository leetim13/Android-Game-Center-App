package fall2018.csc2017.slidingtiles;
import android.text.Spanned;
import android.text.SpannedString;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;
import fall2018.csc2017.slidingtiles.helper.structure.InputFilterMinMax;
import static org.mockito.Mockito.*;
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
    /*
    * tets input filterminmax
    * */
    @Test
    public void testInputFilter() {
        InputFilterMinMax mx = new InputFilterMinMax("1", "10");
        String testStr = "1";
        SpannedString word = mock(SpannedString.class);
        when(word.toString()).thenReturn("100");
        String result = (String) mx.filter(testStr, 0, 1, word, 2, 4);
        assertEquals("", result);
        when(word.toString()).thenReturn("0");
        String s = (String) mx.filter(testStr, 0, 1, word, 2, 4);
        assertEquals("", s);
    }
}
