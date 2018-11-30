//Adapted from: https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android. Retrieved Nov, 2018
package fall2018.csc2017.slidingtiles.helper.structure;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * This is an adapted class retrieved on
 * https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android on
 * Nov, 2018. It's purpose is to set a minimum and maximum integer value for the user when the user to input their maximum
 * number of undo steps.
 *
 * @author Timothy Lee
 */
public class InputFilterMinMax implements InputFilter {
    /**
     * Maximum and minimum input that is valid.
     */
    private int min, max;

    /**
     * Constructor for maximum and minimum input.
     *
     * @param min min input
     * @param max max input
     */
    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    /**
     * Filters the input data and verify if it's in desired range
     *
     * @param source source of CharSequence
     * @param start  starting int
     * @param end    ending int
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        try {
            if (end == 1)
                min = Integer.parseInt(source.toString());
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    /**
     * Check if input is in range  for maximum and minimum input.
     *
     * @param a min input
     * @param b max input
     * @param c user input
     */
    private boolean isInRange(int a, int b, int c) {

        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}

