package fall2018.csc2017.slidingtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.Components.ImageTile;
import fall2018.csc2017.slidingtiles.Components.Tile;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
class BoardManager implements Serializable {

    /**
     * The board being managed.
     */
    private Board board;

    /**
     * The current number of steps.
     */
    private int score = 0;

    /**
     * complexity of current game. (defined as the number of Rows/columns)
     */
    private int complexity;

    /**
     * the number of Rows/columns of current board.
     */
    public int boardNumOfRows;
    public int boardNumOfCols;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    BoardManager(Board board) {
        this.board = board;
        this.boardNumOfCols = Board.numCols;
        this.boardNumOfRows = Board.numRows;
    }

    /**
     * Return the current board.
     */
    Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board with given numRows and numCols.
     */
    BoardManager(int numRows, int numCols) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = numRows * numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if(TileSettingsActivity.isImageTile) {
                tiles.add(new ImageTile(tileNum, numRows, numCols));
            } else{
                tiles.add(new Tile(tileNum, numRows, numCols));
            }
        }
        Collections.shuffle(tiles);
        this.board = new Board(numRows, numCols, tiles);
        this.complexity = numCols;
        this.boardNumOfRows = this.boardNumOfCols = complexity;
    }



    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    boolean puzzleSolved() {
        boolean solved = true;

        // judge by id
        int currentCorrectId = 1;

        for (Tile t: board) {
            if (t.getId() != currentCorrectId) {
                solved = false;
            }
            currentCorrectId++;
        }

        return solved;
    }

    /**
     * Return whether any of the four surrounding tiles is the blank tile.
     *
     * @param position the tile to check
     * @return whether the tile at position is surrounded by a blank tile
     */
    boolean isValidTap(int position) {

        int row = position / Board.numCols;
        int col = position % Board.numCols;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == Board.numRows - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == Board.numCols - 1 ? null : board.getTile(row, col + 1);
        return (below != null && below.getId() == blankId)
                || (above != null && above.getId() == blankId)
                || (left != null && left.getId() == blankId)
                || (right != null && right.getId() == blankId);
    }

    /**
     * Process a touch at position in the board, swapping tiles as appropriate.
     *
     * @param position the position
     * @return the changed position
     */
    int[] touchMove(int position) {

        int row = position / Board.numRows;
        int col = position % Board.numCols;
        int blankId = board.numTiles();

        // tiles is the blank tile, swap by calling Board's swap method.
        int[][] pos = new int[][]{new int[]{row-1, col}, new int[]{row + 1,col},
                new int[]{row, col-1}, new int[]{row, col + 1}};

        for (int[] dir: pos) {
            if (    isValidPos(dir) &&
                    board.getTile(dir[0], dir[1]).getId() == blankId) {
                board.swapTiles(dir[0], dir[1], row, col);
                return dir;
            }
        }
        return null;
    }

    /**
     * Return whether the pos coord of the tile is in the board
     *
     * @param pos the pos containing row and col coordinates to check
     * @return whether the tile at pos coord of the tile is in the board
     */

    private boolean isValidPos(int[] pos) {

        int row = pos[0], col = pos[1];
        return  row >= 0 &&
                row < Board.numRows &&
                col >= 0 &&
                col < Board.numCols;
    }

    /**
     * add one step
     */
    public void addScore(){ this.score++; }

    /**
     * minus one step
     */
    public void minusScore(){
        this.score--;
    }

    /**
     * return current number of steps
     *
     * @return current number of steps
     */
    public int getScore(){
        return score;
    }

    /**
     * return complexity of current game manipulated by this board manager
     *
     * @return complexity complexity of current game manipulated by this board manager
     */
    public int getComplexity(){
        return complexity;
    }
}
