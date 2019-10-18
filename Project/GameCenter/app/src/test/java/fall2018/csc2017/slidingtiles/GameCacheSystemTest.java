package fall2018.csc2017.slidingtiles;
import android.content.Context;

import org.junit.Test;
import static org.mockito.Mockito.*;

import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.model.component.User;

import static org.junit.Assert.*;

public class GameCacheSystemTest {
    private GameCacheSystem sys = GameCacheSystem.getInstance();
    @Test
    public void testPrevIndex() {
        UserPanel panel = UserPanel.getInstance();
        User us = new User("a", "b");
        panel.setUser(us);
        assertEquals(-1, sys.prevGame());
        Context ctx = mock(Context.class);
        sys.load_index(ctx);
        assertEquals(-1, sys.prevGame());
    }
}
