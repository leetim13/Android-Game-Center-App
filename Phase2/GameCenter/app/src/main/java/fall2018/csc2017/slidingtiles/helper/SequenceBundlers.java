/*
class used for sorting values of integer hashmap in IOHelper
*/
package fall2018.csc2017.slidingtiles.helper;

public class SequenceBundlers implements Comparable <SequenceBundlers>{
    private String key;
    private int value;

    public SequenceBundlers(String key, Object value) {
        this.key = key;
        this.value = (int) value;
    }

    public int compareTo(SequenceBundlers other) {
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
