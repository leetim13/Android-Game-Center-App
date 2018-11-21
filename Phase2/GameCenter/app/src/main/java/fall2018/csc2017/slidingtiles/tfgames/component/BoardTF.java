package fall2018.csc2017.slidingtiles.tfgames.component;

import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import fall2018.csc2017.slidingtiles.component.BasicBoard;
import fall2018.csc2017.slidingtiles.component.BasicTile;

public class BoardTF extends BasicBoard implements Iterable<TfTile>{
    public static final int LENGTH_OF_SIDE = 4;
    public static final int WIN_VALUE = 2048;
    public static final int BLANK_ID = 0;

    private int numRows;
    private int numCols;
    private TfTile[][] tfTiles;

    public BoardTF(int lengthOfSide, List<TfTile> tfTiles){
        this.numCols = this.numRows = lengthOfSide;
        this.tfTiles = new TfTile[numRows][numCols];
        Iterator<TfTile> iter = tfTiles.iterator();

        for (int row = 0; row != numRows; row++) {
            for (int col = 0; col != numCols; col++) {
                this.tfTiles[row][col] = iter.next();
            }
        }
    }

    @Override
    public TfTile getTile(int row, int col){
        return tfTiles[row][col];
    }

    @Override
    public int getNumRows(){ return this.numRows; }

    @Override
    public int getNumCols(){ return this.numCols; }

    @Override
    public void swapTiles(int row1, int col1, int row2, int col2) {

        TfTile temp = this.tfTiles[row1][col1];

        this.tfTiles[row1][col1] = this.tfTiles[row2][col2];
        this.tfTiles[row2][col2] = temp;


        setChanged();
        notifyObservers();
    }

    private int numTiles(){ return numCols * numRows; }

    /*
     * The nested class defines an iterator of board by implementing Iterator<Tile>.
     * Methods hasNext() and next() are implemented in this class.
     */
    public class BoardIterator implements Iterator<TfTile>{

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

    @Override
    @NonNull
    public Iterator<TfTile> iterator() { return new BoardIterator(); }
}
