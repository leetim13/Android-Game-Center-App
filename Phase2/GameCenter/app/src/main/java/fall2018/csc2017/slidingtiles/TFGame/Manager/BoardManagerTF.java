package fall2018.csc2017.slidingtiles.TFGame.Manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.Components.BasicBoardManager;
import fall2018.csc2017.slidingtiles.TFGame.Components.BoardTF;
import fall2018.csc2017.slidingtiles.TFGame.Components.TfTile;

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

    public BoardManagerTF(int lengthOfSide){
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        List<TfTile> tfTiles = new ArrayList<>();
        final int numTiles = lengthOfSide * lengthOfSide;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tfTiles.add(new TfTile(BoardTF.BLANK_ID));
        }
        this.boardTF = new BoardTF(lengthOfSide, tfTiles);
    }

    public BoardTF getBoardTF(){ return boardTF; }

    public boolean hasWon(){
        boolean win = false;

        for(int i = 0; i < boardTF.getNumRows(); i++) {
            for (int j = 0; j < boardTF.getNumCols(); j++)
                if (((TfTile)boardTF.getTile(i, j)).getId() == BoardTF.WIN_VALUE) {
                    win = true;
                    break;
                }
            if (win)
                break;
        }
        return win;
    }

    public boolean hasLost(){
        boolean lose = true;

        for(int i = 0; i < boardTF.getNumRows(); i++){
            for (int j = 0; j < boardTF.getNumCols(); j++)
                if (((TfTile)boardTF.getTile(i, j)).getId() == BoardTF.BLANK_ID){
                    lose = false;
                    break;
                }
            if(!lose)
                break;
        }
        return lose;
    }

    public void leftOperation(){
        int id1, id2;
        for (int i = 0; i < BoardTF.LENGTH_OF_SIDE; i++){
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                id1 = ((TfTile)boardTF.getTile(i, j)).getId();
                if (id1 == BoardTF.BLANK_ID)
                    continue;
                for(int k = j+1; k < BoardTF.LENGTH_OF_SIDE; k++){
                    id2 = ((TfTile)boardTF.getTile(i, k)).getId();
                    if (id1 == id2){
                        ((TfTile)boardTF.getTile(i, j)).setId(id1+1);
                        ((TfTile)boardTF.getTile(i, k)).setId(BoardTF.BLANK_ID);
                    }
                }
            }
            for (int j = 0; j < BoardTF.LENGTH_OF_SIDE; j++){
                int k = j;
                while (k < BoardTF.LENGTH_OF_SIDE &&
                        ((TfTile)boardTF.getTile(i, k)).getId() == BoardTF.BLANK_ID)
                    k++;
                if (j != k && k != BoardTF.LENGTH_OF_SIDE)
                    boardTF.swapTiles(i, j, i, k);
            }
        }
    }

    public void addScore(){ this.score++; }

    public void minusScore(){ this.score--; }

    public int getScore(){ return this.score; }

    public int getBoardNumOfRows(){ return this.boardNumOfRows; }

    public int getBoardNumOfCols(){ return this.boardNumOfCols; }
}
