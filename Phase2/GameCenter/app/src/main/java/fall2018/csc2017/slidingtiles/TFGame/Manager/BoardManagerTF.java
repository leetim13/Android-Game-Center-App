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

    BoardManagerTF(int lengthOfSide){
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        List<TfTile> tfTiles = new ArrayList<>();
        final int numTiles = lengthOfSide * lengthOfSide;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            tfTiles.add(new TfTile(BoardTF.BLANK_ID));
        }
        this.boardTF = new BoardTF(lengthOfSide, tfTiles);
    }

    BoardTF getBoardTF(){ return boardTF; }

    boolean hasWon(){
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

    boolean hasLost(){
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



    public void addScore(){ this.score++; }

    public void minusScore(){ this.score--; }

    public int getScore(){ return this.score; }

    public int getBoardNumOfRows(){ return this.boardNumOfRows; }

    public int getBoardNumOfCols(){ return this.boardNumOfCols; }
}
