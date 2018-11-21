package fall2018.csc2017.slidingtiles.structure;

/**
 * ArrayStack for undo function implementation
 */
public class ArrayStack {

    /**
     * Index of the ArrayStack
     */
    private int top = 0;

    /**
     * Contents of the ArrayStack
     */
    private int[] contents;

    /**
     * Constructor of the ArrayStack
     * @param n the size of contents
     */
    public ArrayStack(int n){
        contents = new int[n];
    }

    /**
     * Push a new item into the ArrayStack
     * @param x item pushed
     */
    public void push(int x){
        contents[top] = x;
        top++;
    }

    /**
     * Pop an item from the ArrayStack. Return -1 if the ArrayStack is empty
     * @return the top item from the ArrayStack
     */
    public int pop(){
        if(top == 0)
            return -1;
        top--;
        return contents[top];
    }

    /**
     * Return whether the ArrayStack is empty
     * @return whether the ArrayStack is empty
     */
    public boolean isEmpty(){
        return top == 0;
    }
}
