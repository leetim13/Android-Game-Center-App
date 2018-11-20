package fall2018.csc2017.slidingtiles.tfgames.managers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.tfgames.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.component.TfTile;

public class BoardManagerTF extends BasicBoardManager implements Serializable {
    /**
     * The board being managed.
     */
    private BoardTF boardTF;

    /**
     * The current number of steps.
     */
    private int score = 0;

    /**
     * the number of Rows/columns of current board.
     */
    private int boardNumOfRows;
    private int boardNumOfCols;

    /**
     * Constructor of BoardManagerTF
     * @param lengthOfSide The length of the side of the board
     */
    public BoardManagerTF(int lengthOfSide){
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        List<TfTile> tfTiles = new ArrayList<>();
        final int numTiles = lengthOfSide * lengthOfSide;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tfTiles.add(new TfTile(BoardTF.BLANK_ID));
        }
        this.boardTF = new BoardTF(lengthOfSide, tfTiles);
    }


    /**
     * Return the boardtf of boardManagerTF
     * @return the boardtf of boardManagerTF
     */
    public BoardTF getBoardTF(){ return boardTF; }


    /**
     * Check if the user wins the game
     * @return whether the user wins the game
     */
    public boolean hasWon(){
        boolean win = false;

        for(int i = 0; i < boardTF.getNumRows(); i++) {
            for (int j = 0; j < boardTF.getNumCols(); j++)
                if (boardTF.getTile(i, j).getId() == BoardTF.WIN_VALUE) {
                    win = true;
                    break;
                }
            if (win)
                break;
        }
        return win;
    }

    /**
     * Check if the user loses the game
     * @return whether the user loses the game
     */
    public boolean hasLost(){
        boolean lose = true;

        for(int i = 0; i < boardTF.getNumRows(); i++){
            for (int j = 0; j < boardTF.getNumCols(); j++)
                if ((boardTF.getTile(i, j)).getId() == BoardTF.BLANK_ID){
                    lose = false;
                    break;
                }
            if(!lose)
                break;
        }
        return lose;
    }

    /**
     * Make movements in the boardTF according to operation of the user
     * @param x Character indicating the operation the user made
     */
    public void touchMove(char x){
        switch (x){
            case 'u':
                upOperation();
                break;
            case 'd':
                downOperation();
                break;
            case 'l':
                leftOperation();
                break;
            case 'r':
                rightOperation();
                break;
            default:
                System.out.println("Invalid Operation");
        }
    }

    /**
     * Calculate the places of each tfTile after a slide towards left is made
     * Update the boardTF accordingly
     */
    private void leftOperation(){
        int id1, id2;
        for (int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++){
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                id1 = boardTF.getTile(i, j).getId();
                if (id1 == BoardTF.BLANK_ID)
                    continue;
                for(int k = j+1; k < BoardTF.LENGTH_OF_SIDE; k++){
                    id2 = boardTF.getTile(i, k).getId();
                    if (id1 == id2){
                        boardTF.getTile(i, j).setId(id1+1);
                        boardTF.getTile(i, k).setId(BoardTF.BLANK_ID);
                    }
                }
            }
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                int k = j;
                while (k < BoardTF.LENGTH_OF_SIDE &&
                        boardTF.getTile(i, k).getId() == BoardTF.BLANK_ID)
                    k++;
                if (j != k && k != BoardTF.LENGTH_OF_SIDE)
                    boardTF.swapTiles(i, j, i, k);
            }
        }
    }

    /**
     * Calculate the places of each tfTile after a slide towards right is made
     * Update the boardTF accordingly
     */
    private void rightOperation(){
        int id1, id2;
        for (int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++){
            for (int j = BoardTF.LENGTH_OF_SIDE - 1; j >= 0; j--){
                id1 = boardTF.getTile(i, j).getId();
                if (id1 == BoardTF.BLANK_ID)
                    continue;
                for(int k = j-1; k >= 0; k--){
                    id2 = boardTF.getTile(i, k).getId();
                    if (id1 == id2){
                        boardTF.getTile(i, j).setId(id1+1);
                        boardTF.getTile(i, k).setId(BoardTF.BLANK_ID);
                    }
                }
            }
            for (int j = BoardTF.LENGTH_OF_SIDE - 1; j >= 0; j--){
                int k = j;
                while (k >= 0 &&
                        boardTF.getTile(i, k).getId() == BoardTF.BLANK_ID)
                    k--;
                if (j != k && k >= 0)
                    boardTF.swapTiles(i, j, i, k);
            }
        }
    }

    /**
     * Calculate the places of each tfTile after a slide towards upwards is made
     * Update the boardTF accordingly
     */
    private void upOperation(){
        int id1, id2;
        for (int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++){
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                id1 = boardTF.getTile(j, i).getId();
                if (id1 == BoardTF.BLANK_ID)
                    continue;
                for(int k = j+1; k < BoardTF.LENGTH_OF_SIDE; k++){
                    id2 = boardTF.getTile(k, i).getId();
                    if (id1 == id2){
                        boardTF.getTile(j, i).setId(id1+1);
                        boardTF.getTile(k, i).setId(BoardTF.BLANK_ID);
                    }
                }
            }
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                int k = j;
                while (k < BoardTF.LENGTH_OF_SIDE &&
                        boardTF.getTile(k, i).getId() == BoardTF.BLANK_ID)
                    k++;
                if (j != k && k != BoardTF.LENGTH_OF_SIDE)
                    boardTF.swapTiles(j, i, k, i);
            }
        }
    }

    /**
     * Calculate the places of each tfTile after a slide downwards left is made
     * Update the boardTF accordingly
     */
    private void downOperation(){
        int id1, id2;
        for (int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++){
            for (int j = BoardTF.LENGTH_OF_SIDE - 1; j >= 0; j--){
                id1 = boardTF.getTile(j, i).getId();
                if (id1 == BoardTF.BLANK_ID)
                    continue;
                for(int k = j-1; k >= 0; k--){
                    id2 = boardTF.getTile(k, i).getId();
                    if (id1 == id2){
                        boardTF.getTile(j, i).setId(id1+1);
                        boardTF.getTile(k, i).setId(BoardTF.BLANK_ID);
                    }
                }
            }
            for (int j = BoardTF.LENGTH_OF_SIDE - 1; j >= 0; j--){
                int k = j;
                while (k >= 0 &&
                        boardTF.getTile(k, i).getId() == BoardTF.BLANK_ID)
                    k--;
                if (j != k && k >= 0)
                    boardTF.swapTiles(j, i, k, i);
            }
        }
    }

    /**
     * Add the score of the user by 1
     */
    public void addScore(){ this.score++; }


    /**
     * Minus the score of the user by 1
     */
    public void minusScore(){ this.score--; }


    /**
     * Return the score of the user
     * @return the score of the user
     */
    public int getScore(){ return this.score; }

    /**
     * Return the number of rows in boardTF
     * @return the number of rows in boardTF
     */
    public int getBoardNumOfRows(){ return this.boardNumOfRows; }

    /**
     * Return the number of columns in boardTF
     * @return the number of columns in boardTF
     */
    public int getBoardNumOfCols(){ return this.boardNumOfCols; }
}
