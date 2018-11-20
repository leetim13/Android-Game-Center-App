package fall2018.csc2017.slidingtiles.component;

public abstract class BasicBoardManager {
    /**
     * Add the score in the BasicBoardManager by some number
     */
    public abstract void addScore();

    /**
     * Minus the score in the BasicBoardManager by some number
     */
    public abstract void minusScore();

    /**
     * Return the score in the BasicBoardManager
     * @return the score in the BasicBoardManager
     */
    public abstract int getScore();

    /**
     * Return the number of columns in the board of the BasicBoardManager
     * @return the number of columns in the board of the BasicBoardManager
     */
    public abstract int getBoardNumOfCols();

    /**
     * Return the number of rows in the board of the BasicBoardManager
     * @return the number of rows in the board of the BasicBoardManager
     */
    public abstract int getBoardNumOfRows();
}
