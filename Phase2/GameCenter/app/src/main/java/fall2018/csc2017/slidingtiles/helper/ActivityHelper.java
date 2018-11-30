package fall2018.csc2017.slidingtiles.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.ObjectOutputStream;

/*
* helper tools for handling ui effect
* The static methods have the same use like System.Math, (utility) with only small scope of use, it's not a
* code smell. We're not creating object from any of these static method, or extends these
 * classes with static methods.
* */
public class ActivityHelper {

    /**
     * disable a button and shows the expected text on the textView which has
     * been set inside a layout xml
     * @param button: the button in the layout
     * @param invalidView: the warning textView in that layout
     * @param text: the text to display on the invalidView
     */
    public static void disableButton(final View button, final TextView invalidView, String text) {
        invalidView.setVisibility(View.VISIBLE);
        invalidView.setText(text);
        button.setEnabled(false);
        invalidView.postDelayed(new Runnable() {
            @Override
            public void run() {
                invalidView.setText("");
                invalidView.setVisibility(View.GONE);
                button.setEnabled(true);
            }
        }, 3000);

        System.out.println("not matched!");
    }

    /**
     * split a bitmap into certain parts
     * reference: https://stackoverflow.com/questions/19728181/how-to-divide-a-bitmap-into-parts-that-are-bitmaps-too
     * @param bitmap the bit map to split
     * @param xCount: x columns
     * @param yCount: y columns
     */
    public static Bitmap[][] splitBitmap(Bitmap bitmap, int xCount, int yCount) {
        // Allocate a two dimensional array to hold the individual images.
        Bitmap[][] bitmaps = new Bitmap[xCount][yCount];
        int width, height;
        // Divide the original bitmap width by the desired vertical column count
        width = bitmap.getWidth() / xCount;
        // Divide the original bitmap height by the desired horizontal row count
        height = bitmap.getHeight() / yCount;
        // Loop the array and create bitmaps for each coordinate
        for(int x = 0; x < xCount; ++x) {
            for(int y = 0; y < yCount; ++y) {
                // Create the sliced bitmap
                bitmaps[x][y] = Bitmap.createBitmap(bitmap, x * width, y * height, width, height);
            }
        }
        // Return the array
        return bitmaps;
    }

    /**
     * Save the the object to certain file.
     * @param ctx the activity context
     * @param fileName the name of the file
     * @param obj the object to write to the file with fileName
     */
    public static void saveToFile(String fileName, Context ctx, Object obj) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(ctx.openFileOutput(fileName,
                                                                    ctx.MODE_PRIVATE));
            outputStream.writeObject(obj);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}
