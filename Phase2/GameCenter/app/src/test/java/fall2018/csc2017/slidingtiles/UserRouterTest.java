package fall2018.csc2017.slidingtiles;

import android.content.Context;

import org.junit.Test;

import java.util.HashMap;

import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.model.component.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRouterTest {

    @Test
    public void testVerifyUser(){
        Context context = mock(Context.class);
        HashMap map = new HashMap<String, String[]>();
        User user1 = new User("333", "333");
        UserRouter userRouter = new UserRouter(map);
        assertFalse(userRouter.verifyUser(user1));

//      assertFalse(userRouter.verifyUser(user1, true));
        map.put("333", context);
//        when(userRouter.verifyUser(user1,true)).thenReturn(true);
//        assertTrue(userRouter.verifyUser(user1, true));
    }
}
