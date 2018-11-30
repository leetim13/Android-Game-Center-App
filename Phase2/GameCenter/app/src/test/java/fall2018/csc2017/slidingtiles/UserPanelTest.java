package fall2018.csc2017.slidingtiles;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.model.component.User;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * test the UserPanel
 */
public class UserPanelTest {
    @Test
    public void testPlayState() {
        UserPanel panel = UserPanel.getInstance();
        User user = new User("ad", "asd");
        panel.setUser(user);
        assertEquals("ad", panel.getName());
        assertFalse(panel.isPlayed());
        panel.play();
        assertTrue(panel.isPlayed());
        assertEquals(user, panel.getUser());
    }
}
