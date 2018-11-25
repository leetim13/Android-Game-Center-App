package fall2018.csc2017.slidingtiles.sudokugames.model.component;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.model.component.BasicBoard;

public class BoardSudoku extends BasicBoard implements Iterable<SudokuTile>{
    /**
     * The length of side of the board
     */
    public static final int LENGTH_OF_SIDE = 9;

    /**
     * The id of a blank tile
     */
    public static final int BLANK_ID = 0;

    /**
     * Number of rows and columns
     */
    private int numRows;
    private int numCols;

    /**
     * A 2D array of tiles containing values the user inputs
     */
    private SudokuTile[][] sudokuTiles;

    /**
     * A 2D array of tiles containing values of a completed sudoku board
     */
    private SudokuTile[][] completeTiles;

    /**
     * Return tiles containing removed digits and the user's inputs
     * @return tiles containing removed digits and the user's inputs
     */
    public SudokuTile[][] getSudokuTiles(){ return sudokuTiles; }

    /**
     * Return tiles containing values of completed sudoku board
     * @return tiles containing values of completed sudoku board
     */
    public SudokuTile[][] getCompleteTiles() { return completeTiles; }


    /**
     * Constructor of the sudoku
     * @param lengthOfSide The length of side of the sudoku board
     */
    public BoardSudoku(int lengthOfSide){
        this.numCols = this.numRows = lengthOfSide;
        int numDigitRemoved = 40;
        List<SudokuTile> tiles;
        Sudoku sudoku = new Sudoku(lengthOfSide, numDigitRemoved);
        int[][] mat;
        sudokuTiles = new SudokuTile[lengthOfSide][lengthOfSide];
        completeTiles = new SudokuTile[lengthOfSide][lengthOfSide];
        sudoku.fillValues();
        tiles = sudoku.getTilesList();
        Iterator<SudokuTile> iterator = tiles.iterator();
        for(int i = 0; i < lengthOfSide; i++)
            for(int j = 0; j < lengthOfSide; j++)
                completeTiles[i][j] = iterator.next();
        sudoku.removeKDigits();
        tiles = sudoku.getTilesList();
        iterator = tiles.iterator();
        for(int i = 0; i < lengthOfSide; i++)
            for(int j = 0; j < lengthOfSide; j++)
                sudokuTiles[i][j] = iterator.next();
    }

    @Override
    public SudokuTile getTile(int row, int col){ return sudokuTiles[row][col]; }

    @Override
    public int getNumCols(){ return this.numCols; }

    @Override
    public int getNumRows(){ return this.numRows; }

    /**
     * Swap positions of tile1 at position (row1, col1) and tile2 at position (row2, col2)
     * @param row1 the row number of tile1
     * @param col1 the column number of tile1
     * @param row2 the row number of tile2
     * @param col2 the column number of tile2
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {

        SudokuTile temp = this.sudokuTiles[row1][col1];

        this.sudokuTiles[row1][col1] = this.sudokuTiles[row2][col2];
        this.sudokuTiles[row2][col2] = temp;

        setChanged();
        notifyObservers();
    }

    /**
     * Return the number of tiles
     * @return the number of tiles
     */
    private int numTiles(){ return numCols * numRows; }

    /*
     * The nested class defines an iterator of board by implementing Iterator<SudokuTile>.
     * Methods hasNext() and next() are implemented in this class.
     */
    public class BoardIterator implements Iterator<SudokuTile>{

        /** The index of the next Tile to return. */
        private int next = 0;

        /*
         * Return if there is a next Tile to return
         * @return if there is a next Tile to return
         */
        @Override
        public boolean hasNext() {
            return next < numTiles();
        }

        /*
         * Return the next Tile
         * @return the next Tile
         */
        @Override
        public SudokuTile next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int row = next / LENGTH_OF_SIDE;
            int col = next % LENGTH_OF_SIDE;
            next++;
            return sudokuTiles[row][col];
        }
    }
    /*
    * notify that this board has been changed
    * */
    public void change() {
        this.setChanged();
    }

    /**
     * Return a iterator of the board
     * @return a iterator of the board
     */
    @Override
    @NonNull
    public Iterator<SudokuTile> iterator() { return new BoardIterator(); }
}
