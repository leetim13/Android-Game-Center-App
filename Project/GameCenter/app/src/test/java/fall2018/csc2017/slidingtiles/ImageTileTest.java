package fall2018.csc2017.slidingtiles;

import android.graphics.Bitmap;

import org.junit.Test;

import fall2018.csc2017.slidingtiles.slidinggames.model.component.BitmapCollection;
import fall2018.csc2017.slidingtiles.slidinggames.model.component.ImageTile;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * test for image tile
 */
public class ImageTileTest {
    @Test
    public void testImageTile() {
        BitmapCollection.getInstance().loadImage(new Bitmap[10][10]);
        ImageTile tile = new ImageTile(0, 2, 2);
        assertEquals(1, tile.getId());
        assertEquals(null, tile.getBack());
    }
}
