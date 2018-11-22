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

    /**
     * Return the complexity of board the BasicBoardManager
     * @return the complexity of board the BasicBoardManager
     */
    public abstract int getComplexity();

    /**
     * Return whether the game has won
     * @return the boolean whether the game has won
     */
    public abstract boolean hasWon();

    /**
     * Return the board being manipulated by the boardManager
     * @return the board being manipulated by the boardManager
     */
    public abstract BasicBoard getBoard();

    /**
     * Return the index of the current game being manipulated by the boardManager
     * @return the index of the current game being manipulated by the boardManager
     */
    public abstract int getGameIndex();

}
