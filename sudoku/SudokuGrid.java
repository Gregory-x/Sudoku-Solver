import java.awt.Point;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * The SudokuGrid class represents a 9x9 Sudoku grid and provides methods to
 * manipulate and check the grid for conflicts, as well as find and fill cells.
 */
public class SudokuGrid {
    private static final int SIZE = 9;
    private static final int DIGIT_RANGE = 9;
    
    private int[][] grid;
    private int rEmpty;
    private int cEmpty;

    /**
     * Constructor for the SudokuGrid class.
     * 
     * @param grid the initial 9x9 Sudoku grid to be used
     */
    public SudokuGrid(int[][] grid) {
        // Initialize the grid with the passed grid values
        this.grid = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.grid[i][j] = grid[i][j]; // Copy the passed grid
            }
        }
        rEmpty = -1;
        cEmpty = -1;
    }

    /**
     * Creates a copy of the current Sudoku grid.
     * 
     * @return a new SudokuGrid object that is a copy of the current grid
     */
    public SudokuGrid copy() {
        SudokuGrid gridCopy;
        gridCopy = new SudokuGrid(grid);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                gridCopy.grid[i][j] = grid[i][j];
            }
        }
        return gridCopy;
    }

    /**
     * Finds the next empty cell in the grid in reading order (row by row).
     * 
     * @return the coordinates of the next empty cell as a Point, or null if no empty
     *         cell is found
     */
    public Point findEmptyCell() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    rEmpty = i;
                    cEmpty = j;
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    /**
     * Prints the Sudoku grid to the console in a human-readable format.
     */
    public void print() {
        System.out.println("+-----------------+");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0) {
                    System.out.print("|");
                }
                if (grid[i][j] == 0) {
                    System.out.print(" " + " ");
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println("|");
            if ((i + 1) % 3 == 0) {
                System.out.println("+-----------------+");
            }
        }
    }

    /**
     * Fills the cell at the specified row and column with the given digit.
     * 
     * @param r the row index of the cell
     * @param c the column index of the cell
     * @param d the digit to fill in the cell
     */
    public void fillCell(int r, int c, int d) {
        grid[r][c] = d;
    }

    /**
     * Checks if filling the specified digit in the specified cell causes a conflict
     * (i.e., violates Sudoku rules).
     * 
     * @param r the row index of the cell
     * @param c the column index of the cell
     * @param d the digit to check for conflict
     * @return true if there is a conflict, false otherwise
     */
    public boolean givesConflict(int r, int c, int d) {
        return (rowConflict(r, d) || colConflict(c, d)
            || boxConflict(r, c, d) || asteriskConflict(d));
    }

    /**
     * Checks if there is a conflict in the specified row when filling the given
     * digit.
     * 
     * @param r the row index
     * @param d the digit to check
     * @return true if there is a conflict, false otherwise
     */
    private boolean rowConflict(int r, int d) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[r][i] == d) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a conflict in the specified column when filling the given
     * digit.
     * 
     * @param c the column index
     * @param d the digit to check
     * @return true if there is a conflict, false otherwise
     */
    private boolean colConflict(int c, int d) {
        for (int i = 0; i < SIZE; i++) {
            if (grid[i][c] == d) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there is a conflict in the 3x3 box containing the specified cell
     * when filling the given digit.
     * 
     * @param r the row index of the cell
     * @param c the column index of the cell
     * @param d the digit to check
     * @return true if there is a conflict, false otherwise
     */
    private boolean boxConflict(int r, int c, int d) {
        int boxRow = (r / 3) * 3;
        int boxColumn = (c / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxColumn + j] == d) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if there is a conflict in the asterisk cells (a predefined pattern of
     * cells) when filling the given digit.
     * 
     * @param d the digit to check
     * @return true if there is a conflict, false otherwise
     */
    private boolean asteriskConflict(int d) {
        int[] array = new int[9];
        array[0] = grid[4][1];
        array[1] = grid[2][2];
        array[2] = grid[6][2];
        array[3] = grid[1][4];
        array[4] = grid[4][4];
        array[5] = grid[7][4];
        array[6] = grid[2][6];
        array[7] = grid[6][6];
        array[8] = grid[4][7];
        for (int i = 0; i < 9; i++) {
            if (array[i] == d) {
                return true;
            }
        }
        return false;
    }
}
