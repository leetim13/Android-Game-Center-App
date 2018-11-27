package fall2018.csc2017.slidingtiles.sudokugames.model.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fall2018.csc2017.slidingtiles.helper.TileFactory;


/**
 * Adapted from:
 * https://www.geeksforgeeks.org/program-sudoku-generator/
 *
 * Generate a sudoku matrix
 */
class Sudoku {
    /**
     * Matrix used to contain the generated sudoku
     */
    private int[] mat[];

    /**
     * The length of side of the sudoku
     */
    private int lengthOfSide;

    /**
     * Square root of lengthOfSide
     */
    private int rootLenOfSide;

    /**
     * Number of digits to remove
     */
    private int numOfRemovedDigits;

    /**
     * the tile factory to generate new tile.
     */
    private TileFactory tileFactory = new TileFactory();

    /**
     * Constructor of the sudoku
     * @param lengthOfSide The length of side of the sudoku
     * @param numOfRemovedDigits Number of removed digits
     */
    Sudoku(int lengthOfSide, int numOfRemovedDigits)
    {
        this.lengthOfSide = lengthOfSide;
        this.numOfRemovedDigits = numOfRemovedDigits;

        // Compute square root of lengthOfSide
        Double squaLen = Math.sqrt(lengthOfSide);
        rootLenOfSide = squaLen.intValue();

        mat = new int[lengthOfSide][lengthOfSide];
    }

    /**
     * Sudoku generator
     */
    void fillValues()
    {
        // Fill the diagonal of rootLenOfSide x rootLenOfSide matrices
        fillDiagonal();

        // Fill remaining blocks
        fillRemaining(0, rootLenOfSide);
    }

    /**
     * Fill the diagonal rootLenOfSide number of rootLenOfSide x rootLenOfSide matrices
     */
    private void fillDiagonal()
    {

        for (int i = 0; i< lengthOfSide; i=i+ rootLenOfSide)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i);
    }

    /**
     * Return whether given 3 x 3 block contains num
     * @param rowStart Starting row of the block
     * @param colStart Starting column of the block
     * @param num The number to check
     * @return whether the given 3 x 3 block contains num
     */
    private boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i< rootLenOfSide; i++)
            for (int j = 0; j< rootLenOfSide; j++)
                if (mat[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    /**
     * Fill a 3 x 3 block
     * @param row Starting row of the block
     * @param col Starting column of the block
     */
    private void fillBox(int row,int col)
    {
        int num;
        for (int i = 0; i< rootLenOfSide; i++)
        {
            for (int j = 0; j< rootLenOfSide; j++)
            {
                do
                {
                    num = randomGenerator(lengthOfSide);
                }
                while (!unUsedInBox(row, col, num));

                mat[row+i][col+j] = num;
            }
        }
    }

    /**
     * Generate a number from 0 to num randomly
     * @param num The celling of the number that would be genarated
     * @return The generated number
     */
    private int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    // Check if safe to put in cell
    private boolean CheckIfSafe(int i,int j,int num)
    {
        return (unUsedInRow(i, num) &&
                unUsedInCol(j, num) &&
                unUsedInBox(i-i% rootLenOfSide, j-j% rootLenOfSide, num));
    }

    /**
     * Check in row for existence
     * @param row The row being checked
     * @param num The number being checked
     * @return whether the row contains num
     */
    private boolean unUsedInRow(int row,int num)
    {
        for (int col = 0; col < lengthOfSide; col++)
            if (mat[row][col] == num)
                return false;
        return true;
    }

    /**
     * Check in column for existence
     * @param col The column being checked
     * @param num The number being checked
     * @return whether the column contains num
     */
    private boolean unUsedInCol(int col,int num)
    {
        for (int row = 0; row < lengthOfSide; row++)
            if (mat[row][col] == num)
                return false;
        return true;
    }

    /**
     * A recursive function to fill remaining matrix
     * @param row Row number
     * @param col Column number
     * @return whether its filled
     */
    private boolean fillRemaining(int row, int col)
    {
        if (col>= lengthOfSide && row< lengthOfSide -1)
        {
            row = row + 1;
            col = 0;
        }
        if (row>= lengthOfSide && col>= lengthOfSide)
            return true;

        if (row < rootLenOfSide)
        {
            if (col < rootLenOfSide)
                col = rootLenOfSide;
        }
        else if (row < lengthOfSide - rootLenOfSide)
        {
            if (col==(row/ rootLenOfSide)* rootLenOfSide)
                col =  col + rootLenOfSide;
        }
        else
        {
            if (col == lengthOfSide - rootLenOfSide)
            {
                row = row + 1;
                col = 0;
                if (row>= lengthOfSide)
                    return true;
            }
        }

        for (int num = 1; num<= lengthOfSide; num++)
        {
            if (CheckIfSafe(row, col, num))
            {
                mat[row][col] = num;
                if (fillRemaining(row, col+1))
                    return true;

                mat[row][col] = 0;
            }
        }
        return false;
    }

    /**
     * Count the number of blank non-zeros in the board
     * @return the number of blank non-zeros in the board
     */
    private int countNumOfBlankTiles(){
        int count = 0;
        for(int i = 0; i < BoardSudoku.LENGTH_OF_SIDE; i++)
            for(int j = 0; j < BoardSudoku.LENGTH_OF_SIDE; j++)
                if(mat[i][j] != 0)
                    count++;
        return count;
    }

    /**
     * Randomly remove one digit of mat
     */
    private void removeOneDigit(){
        Random random = new Random();
        int newPosition = random.nextInt(countNumOfBlankTiles());
        int position = 0;
        int row = 0, col = 0, i = 0;
        while(i <= newPosition){
            row = position / BoardSudoku.LENGTH_OF_SIDE;
            col = position % BoardSudoku.LENGTH_OF_SIDE;
            if(mat[row][col] != 0) i++;
            position++;
        }
        mat[row][col] = 0;
    }

    /**
     * Remove numOfRemovedDigits to generate a sudoku game board
     * completing the game
     */
    void removeKDigits()
    {
        for(int i = 0; i < numOfRemovedDigits; i++)
            removeOneDigit();
    }

    /**
     * Transmit the values of mat to a list of SudokuTiles
     * Return the list
     * @return A list a SudokuTiles containing values of mat accordingly
     */
    List<SudokuTile> getTilesList(){
        List<SudokuTile> tiles = new ArrayList<>();
        for(int i = 0; i < BoardSudoku.LENGTH_OF_SIDE; i++) {
            for (int j = 0; j < BoardSudoku.LENGTH_OF_SIDE; j++) {
                SudokuTile newTile = (SudokuTile) tileFactory.createTile(mat[i][j], "SudokuTile");
                if (mat[i][j] != 0) {newTile.setTrait(true);}
                tiles.add(newTile);
            }
        }
        return tiles;
    }
}
