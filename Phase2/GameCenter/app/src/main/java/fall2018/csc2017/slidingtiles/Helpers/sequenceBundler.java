/*
class used for sorting values of integer hashmap in IOHelper
*/
package fall2018.csc2017.slidingtiles.Helpers;

public class sequenceBundler implements Comparable <sequenceBundler>{
    private String key;
    private int value;

    public sequenceBundler(String key, Object value) {
        this.key = key;
        this.value = (int) value;
    }

    public int compareTo(sequenceBundler other) {
        return this.value - other.value;
    }
    /*@return String the key value of this bundler
    * */
    public String getkey() {
        return this.key;
    }
    /*@return int the int value of this bundler
    * */
    public int getValue() {
        return this.value;
    }
}
