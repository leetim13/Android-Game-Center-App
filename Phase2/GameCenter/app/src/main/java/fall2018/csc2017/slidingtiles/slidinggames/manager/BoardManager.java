package fall2018.csc2017.slidingtiles.slidinggames.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.component.ImageTile;
import fall2018.csc2017.slidingtiles.slidinggames.component.Tile;
import fall2018.csc2017.slidingtiles.slidinggames.component.Board;
import fall2018.csc2017.slidingtiles.slidinggames.view.TileSettingsActivity;

/**
 * Manage a board, including swapping tiles, checking for a win, and managing taps.
 */
public class BoardManager extends BasicBoardManager implements Serializable {

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
    private int boardNumOfRows;
    private int boardNumOfCols;

    /**
     * Manage a board that has been pre-populated.
     * @param board the board
     */
    public BoardManager(Board board) {
        this.board = board;
        this.boardNumOfCols = board.getNumCols();
        this.boardNumOfRows = board.getNumRows();
    }

    /**
     * Return the current board.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Manage a new shuffled board with given numRows and numCols.
     */
    public BoardManager(int numRows, int numCols) {
        List<Tile> tiles = new ArrayList<>();
        final int numTiles = numRows * numCols;
        for (int tileNum = 0; tileNum != numTiles; tileNum++) {
            if(TileSettingsActivity.isImageTile) {
                tiles.add(new ImageTile(tileNum, numRows, numCols));
            } else{
                tiles.add(new Tile(tileNum, numRows, numCols));
            }
        }
        do {
            Collections.shuffle(tiles);
        }while(!isValidShuffle(tiles, numCols, numRows));
        this.board = new Board(numRows, numCols, tiles);
        this.complexity = numCols;
        this.boardNumOfRows = this.boardNumOfCols = complexity;
    }


    /**
     * Get the number of inversions in tiles
     * @param tiles the tiles for the board
     * @param blankId the Id of the blank tile
     * @return the number of inversions in tiles
     */
    private int getNumOfInversions(List<Tile> tiles, int blankId){
        if(tiles.isEmpty())
            return 0;
        int size = tiles.size();
        int count = 0;
        for(int i = 0; i < size; i++) {
            if(tiles.get(i).getId() == blankId)
                continue;
            for (int j = i + 1; j < size; j++) {
                if (tiles.get(i).getId() > tiles.get(j).getId()
                        && tiles.get(j).getId() != blankId)
                    count++;
            }
        }
        return count;
    }


    /**
     *
     * @param tiles the tiles of the board
     * @param boardNumOfCols the number of columns of the board
     * @param boardNumOfRows the number of rows of the board
     * @return if the board is solvable
     */
    private boolean isValidShuffle(List<Tile> tiles, int boardNumOfCols, int boardNumOfRows){
        int i;
        int row;
        int blankId = boardNumOfCols * boardNumOfRows;
        if(boardNumOfCols % 2 == 0){
            for(i = 0; i < tiles.size(); i++)
                if(tiles.get(i).getId() == blankId)
                    break;
            row = i / boardNumOfCols + 1;
            if (row % 2 == 0 && getNumOfInversions(tiles, blankId) % 2 == 0)
                return true;
            return row % 2 != 0 && getNumOfInversions(tiles, blankId) % 2 != 0;
        }
        else{
            return getNumOfInversions(tiles, blankId) % 2 == 0;
        }
    }

    @Override
    /**
     * Return whether the tiles are in row-major order.
     *
     * @return whether the tiles are in row-major order
     */
    public boolean hasWon() {
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
    public boolean isValidTap(int position) {

        int row = position / boardNumOfRows;
        int col = position % boardNumOfRows;
        int blankId = board.numTiles();
        // Are any of the 4 the blank tile?
        Tile above = row == 0 ? null : board.getTile(row - 1, col);
        Tile below = row == boardNumOfRows - 1 ? null : board.getTile(row + 1, col);
        Tile left = col == 0 ? null : board.getTile(row, col - 1);
        Tile right = col == boardNumOfCols - 1 ? null : board.getTile(row, col + 1);
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
    public int[] touchMove(int position) {

        int row = position / boardNumOfRows;
        int col = position % boardNumOfCols;
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
                row < boardNumOfRows &&
                col >= 0 &&
                col < boardNumOfCols;
    }

    @Override
    /**
     * add one step
     */
    public void addScore(){ this.score++; }

    @Override
    /**
     * minus one step
     */
    public void minusScore(){
        this.score--;
    }

    @Override
    /**
     * return current number of steps
     *
     * @return current number of steps
     */
    public int getScore(){
        return score;
    }

    @Override
    /**
     * return complexity of current game manipulated by this board manager
     *
     * @return complexity complexity of current game manipulated by this board manager
     */
    public int getComplexity(){
        return complexity;
    }

    public int getBoardNumOfCols() {
        return boardNumOfCols;
    }

    public int getBoardNumOfRows() {
        return boardNumOfRows;
    }

}
