package fall2018.csc2017.slidingtiles.tfgames.model.component;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.helper.TileFactory;
import fall2018.csc2017.slidingtiles.model.component.BasicBoard;

/**
 * The board of game twenty forty-eight
 */
public class BoardTF extends BasicBoard implements Iterable<TfTile> {
    /**
     * The length of side of the board
     */
    public static final int LENGTH_OF_SIDE = 4;

    /**
     * The value once a tile in the board reaches, the user wins
     */
    public static final int WIN_VALUE = 11;

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
     * A 2D array of tiles in the board
     */
    private TfTile[][] tfTiles;

    /**
     * the tileFactory to create new TfTiles
     */
    private TileFactory tileFactory = new TileFactory();

    /**
     * Constructor of the board
     *
     * @param lengthOfSide the length of the side of the board
     * @param tfTiles      tiles planing to put in the board
     */
    public BoardTF(int lengthOfSide, List<TfTile> tfTiles) {
        this.numCols = this.numRows = lengthOfSide;
        this.tfTiles = new TfTile[numRows][numCols];
        Iterator<TfTile> iter = tfTiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tfTiles[row][col] = iter.next();
            }
        }
    }

    /**
     * Another constructor of the board
     *
     * @param lengthOfSide the length of the side of the board
     * @param tfTiles      tiles planing to put in the board, in final format
     */
    public BoardTF(int lengthOfSide, TfTile[][] tfTiles) {
        this.numCols = this.numRows = lengthOfSide;
        this.tfTiles = new TfTile[numRows][numCols];
        this.tfTiles = tfTiles;
    }

    @Override
    public TfTile getTile(int row, int col) {
        return tfTiles[row][col];
    }

    @Override
    public int getNumCols() {
        return this.numCols;
    }

    @Override
    public int getNumRows() {
        return this.numRows;
    }

    /**
     * Swap positions of tile1 at position (row1, col1) and tile2 at position (row2, col2)
     *
     * @param row1 the row number of tile1
     * @param col1 the column number of tile1
     * @param row2 the row number of tile2
     * @param col2 the column number of tile2
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {

        TfTile temp = this.tfTiles[row1][col1];

        this.tfTiles[row1][col1] = this.tfTiles[row2][col2];
        this.tfTiles[row2][col2] = temp;


        setChanged();
        notifyObservers();
    }

    /**
     * Return the number of tiles
     *
     * @return the number of tiles
     */
    private int numTiles() {
        return numCols * numRows;
    }

    /**
     * The nested class defines an iterator of board by implementing Iterator<TfTile>.
     * Methods hasNext() and next() are implemented in this class.
     */
    public class BoardIterator implements Iterator<TfTile> {

        /**
         * The index of the next Tile to return.
         */
        private int next = 0;

        /**
         * Return if there is a next Tile to return
         *
         * @return if there is a next Tile to return
         */
        @Override
        public boolean hasNext() {
            return next < numTiles();
        }

        /**
         * Return the next Tile
         *
         * @return the next Tile
         */
        @Override
        public TfTile next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int row = next / LENGTH_OF_SIDE;
            int col = next % LENGTH_OF_SIDE;
            next++;
            return tfTiles[row][col];
        }
    }

    /**
     * Return a iterator of the board
     *
     * @return a iterator of the board
     */
    @Override
    @NonNull
    public Iterator<TfTile> iterator() {
        return new BoardIterator();
    }

    /**
     * Return a copy of the tfTiles in this current TfBoard
     *
     * @return a copy of the tfTiles in this current TfBoard
     */
    public TfTile[][] getTilesCopy() {
        TfTile[][] copy = new TfTile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy[i][j] = (TfTile) tileFactory.createTile(tfTiles[i][j].getId(), "TfTile");
            }
        }
        return copy;
    }
}
