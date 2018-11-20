package fall2018.csc2017.slidingtiles.structure;

/**
 * ArrayStack for undo function implementation
 */
public class ArrayStack {
    private int top = 0;
    private int[] contents;

    public ArrayStack(int n){
        contents = new int[n];
    }

    public void push(int x){
        contents[top] = x;
        top++;
    }

    public int pop(){
        if(top == 0)
            return -1;
        top--;
        return contents[top];
    }

    public boolean isEmpty(){
        return top == 0;
    }
}
