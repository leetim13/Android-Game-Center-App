package fall2018.csc2017.slidingtiles.sudokugames.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.component.User;
import fall2018.csc2017.slidingtiles.sudokugames.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.component.SudokuTile;
import fall2018.csc2017.slidingtiles.tfgames.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.component.TfTile;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;

public class BoardManagerSudoku extends BasicBoardManager implements Serializable {


    private BoardSudoku boardSudoku;

    /**
     * The current number of steps.
     */
    private int score = 0;

    /**
     * the number of Rows/columns of current board.
     */
    private int boardNumOfRows;
    private int boardNumOfCols;

    private SudokuTile[][] sudokuTiles;
    private SudokuTile[][] completeTiles;

    /**
     * Constructor of BoardManagerTF
     * @param lengthOfSide The length of the side of the board
     */
    public BoardManagerSudoku(int lengthOfSide){
        this.boardNumOfCols = this.boardNumOfRows = lengthOfSide;
        this.boardSudoku = new BoardSudoku(lengthOfSide);
        sudokuTiles = boardSudoku.getSudokuTiles();
        completeTiles = boardSudoku.getCompleteTiles();
    }

    @Override
    public BoardSudoku getBoard(){ return boardSudoku; }

    @Override
    public boolean hasWon(){
        boolean win = true;
        for(int i = 0; i < boardNumOfRows; i++)
            for(int j = 0; j < boardNumOfCols; j++)
                if(sudokuTiles[i][j].getId() != completeTiles[i][j].getId()){
                    win = false;
                    break;
                }
        return win;
    }

    public boolean checkBoardValidation(){
        boolean valid = true;
        for(int i = 0; i < boardNumOfRows; i++)
            for(int j = 0; j < boardNumOfCols; j++)
                if(sudokuTiles[i][j].getId() != 0 &&
                        sudokuTiles[i][j].getId() != completeTiles[i][j].getId()){
                    valid = false;
                    break;
                }
        return valid;
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
        return 9;
    }

    @Override
    public int getGameIndex() {
        return User.SD_GAME_INDEX;
    }
}
