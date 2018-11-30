package fall2018.csc2017.slidingtiles.slidinggames.model.component;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import fall2018.csc2017.slidingtiles.model.component.BasicBoard;

/**
 * The sliding tiles board.
 */
public class Board extends BasicBoard implements Iterable<Tile> {

    /**
     * The number of rows.
     */
    private int numRows;

    /**
     * The number of rows.
     */
    private int numCols;

    /**
     * The tiles on the board in row-major order.
     */
    private Tile[][] tiles;

    /**
     * A new board of tiles in row-major order.
     * Precondition: len(tiles) == numRows * numCols
     *
     * @param tiles the tiles for the board
     */
    public Board(int numRows, int numCols, List<Tile> tiles) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.tiles = new Tile[numRows][numCols];
        Iterator<Tile> iter = tiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tiles[row][col] = iter.next();
            }
        }
    }

    @NonNull
    @Override
    public TileIterator iterator() {
        return new TileIterator();
    }

    /**
     * Return the number of tiles on the board.
     *
     * @return the number of tiles on the board
     */
    public int numTiles() {
        int sum = 0;

        for (Tile[] row : this.tiles) {
            sum += row.length;
        }

        return sum;
    }

    @Override
    /**
     * Return the tile at (row, col)
     *
     * @param row the tile row
     * @param col the tile column
     * @return the tile at (row, col)
     */
    public Tile getTile(int row, int col) {
        return tiles[row][col];
    }

    /**
     * Swap the tiles at (row1, col1) and (row2, col2)
     *
     * @param row1 the first tile row
     * @param col1 the first tile col
     * @param row2 the second tile row
     * @param col2 the second tile col
     */
    public void swapTiles(int row1, int col1, int row2, int col2) {

        Tile temp = this.tiles[row1][col1];

        this.tiles[row1][col1] = this.tiles[row2][col2];
        this.tiles[row2][col2] = temp;

        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                '}';
    }

    /**
     * the nested iterator class, used to iterate over the tiles
     */
    private class TileIterator implements Iterator<Tile> {

        /**
         * the index of the certain tile in one row of tiles
         */
        private int colIndex = 0;

        /**
         * the index of the certain row in tiles
         */
        private int rowIndex = 0;

        @Override
        /**
         * return whether there is tile following the current tile (here means at right/below of
         * current tile)
         *
         * @return whether there is tile following the current tile
         */
        public boolean hasNext() {
            return rowIndex + 1 < tiles.length || (colIndex + 1 < tiles[rowIndex].length);
        }

        @Override
        /**
         * return next tile if there exists one.
         *
         * @return next tile if there exists one.
         */
        public Tile next() {

            Tile[] curTile = tiles[rowIndex];

            if (colIndex >= curTile.length) {
                colIndex = 0;
                rowIndex++;
            }

            return tiles[rowIndex][colIndex++];
        }
    }

    @Override
    public int getNumRows() {
        return numRows;
    }

    @Override
    public int getNumCols() {
        return numCols;
    }
}
