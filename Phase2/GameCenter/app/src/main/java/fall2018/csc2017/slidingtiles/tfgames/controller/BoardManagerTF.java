package fall2018.csc2017.slidingtiles.tfgames.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.helper.TileFactory;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.tfgames.model.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.model.component.TfTile;

/**
 * Manage a boardTF, including making movements after each operation made by the user
 * and checking for a win or a lose.
 */
public class BoardManagerTF extends BasicBoardManager implements Serializable {
    /**
     * The board being managed.
     */

    public final static int UP_SIGNAL = 0;
    public final static int RIGHT_SIGNAL = 1;
    public final static int DOWN_SIGNAL = 2;
    public final static int LEFT_SIGNAL = 3;

    private BoardTF boardTF;

    /**
     * the tile factory to generate new tile.
     */
    private TileFactory tileFactory = new TileFactory();

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
     * Counted number of numbers randomly generated
     */
    private int count = 2;

    /**
     * Constructor of BoardManagerTF
     * @param lengthOfSide The length of the side of the board
     */
    public BoardManagerTF(int lengthOfSide){
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        List<TfTile> tfTiles = new ArrayList<>();
        final int numTiles = lengthOfSide * lengthOfSide;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tfTiles.add((TfTile) tileFactory.createTile(BoardTF.BLANK_ID, "TfTile"));
        }
        this.boardTF = new BoardTF(lengthOfSide, tfTiles);
        for (int i = 0; i < 2; i++)
            generateNewTile();
    }

    /**
     * Constructor of BoardManagerTF for testing
     */
    public BoardManagerTF(){
        this.boardNumOfCols = this.boardNumOfRows = BoardTF.LENGTH_OF_SIDE;
        List<TfTile> tfTiles = new ArrayList<>();
        final int numTiles = BoardTF.LENGTH_OF_SIDE * BoardTF.LENGTH_OF_SIDE;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tfTiles.add((TfTile) tileFactory.createTile(BoardTF.BLANK_ID, "TfTile"));
        }
        this.boardTF = new BoardTF(BoardTF.LENGTH_OF_SIDE, tfTiles);
    }

    @Override
    public BoardTF getBoard(){ return boardTF; }

    @Override
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
    public void touchMove(int x){
        switch (x){
            case BoardManagerTF.UP_SIGNAL:
                upOperation();
                generateNewTile();
                break;
            case BoardManagerTF.DOWN_SIGNAL:
                downOperation();
                generateNewTile();
                break;
            case BoardManagerTF.LEFT_SIGNAL:
                leftOperation();
                generateNewTile();
                break;
            case BoardManagerTF.RIGHT_SIGNAL:
                rightOperation();
                generateNewTile();
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
                    if (id2 != 0 && id1 != id2)
                        break;
                    if (id1 == id2){
                        boardTF.getTile(i, j).setId(id1+1);
                        boardTF.getTile(i, k).setId(BoardTF.BLANK_ID);
                        break;
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
                    if (id2 != 0 && id1 != id2)
                        break;
                    if (id1 == id2){
                        boardTF.getTile(i, j).setId(id1+1);
                        boardTF.getTile(i, k).setId(BoardTF.BLANK_ID);
                        break;
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
                    if (id2 != 0 && id1 != id2)
                        break;
                    if (id1 == id2){
                        boardTF.getTile(j, i).setId(id1+1);
                        boardTF.getTile(k, i).setId(BoardTF.BLANK_ID);
                        break;
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
                    if (id2 != 0 && id1 != id2)
                        break;
                    if (id1 == id2){
                        boardTF.getTile(j, i).setId(id1+1);
                        boardTF.getTile(k, i).setId(BoardTF.BLANK_ID);
                        break;
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
     * Count the number of blank tiles in the board
     * @return the number of blank tiles in the board
     */
    public int countNumOfBlankTiles(){
        int count = 0;
        for (TfTile aBoardTF : boardTF)
            if (aBoardTF.getId() == 0)
                count++;
        return count;
    }

    /**
     * Randomly generate a new tile in the board
     */
    public void generateNewTile(){
        Random random = new Random();
        int newTileSeqNum = random.nextInt(countNumOfBlankTiles());
        Iterator<TfTile> iterator = boardTF.iterator();
        TfTile temp = null;
        int i = 0;
        while(i <= newTileSeqNum){
            temp = iterator.next();
            if(temp.getId() == 0) i++;
        }
        if (temp != null) {
            if(count % 100 == 0)
                temp.setId(3);
            else {
                if (count % 10 == 0) {
                    temp.setId(2);
                } else temp.setId(1);
            }
            count++;
        }
    }

    @Override
    public void addScore(){ this.score++; }

    @Override
    public void minusScore(){ this.score--; }

    @Override
    public int getScore(){ return this.score; }

    @Override
    public int getBoardNumOfRows(){ return this.boardNumOfRows; }

    @Override
    public int getBoardNumOfCols(){ return this.boardNumOfCols; }

    @Override
    public int getComplexity() {
        return 4;
    }

    @Override
    public int getGameIndex(){
        return User.TF_GAME_INDEX;
    }

    /**
     * set a new board to this boardManager
     */
    public void setBoardTF(BoardTF boardTF){
        this.boardTF = boardTF;
    }
}
