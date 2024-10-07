import java.awt.Point;
import java.awt.GridBagConstraints;

/**
 * The SudokuSolver class is responsible for solving a Sudoku puzzle using
 * recursive backtracking and a SudokuGrid object.
 * 
 * @author Daniel Gergo Bihari
 * @id 2108100
 * @author Łukasz Miśkiewicz
 * @id 2164051
 */
public class SudokuSolver {

    private SudokuGrid grid;

    /**
     * Constructor for the SudokuSolver class.
     * 
     * @param grid the SudokuGrid object to be solved
     */
    public SudokuSolver(SudokuGrid grid) {
        // Initialize the SudokuSolver with the provided SudokuGrid
        this.grid = grid;
    }

    /**
     * Solves the Sudoku puzzle using a recursive backtracking strategy.
     * 
     * @return true if the puzzle is solved, false if no solution is found
     */
    public boolean solve() {
        Point emptyCell = grid.findEmptyCell();
        if (emptyCell == null) {
            return true; // solved, no empty cells
        }

        int row = emptyCell.x;
        int col = emptyCell.y;

        // Try placing digits 1 through 9 in the empty cell
        for (int num = 1; num <= 9; num++) {
            if (!grid.givesConflict(row, col, num)) {
                grid.fillCell(row, col, num);

                // Recursively attempt to solve the grid with the current placement
                if (solve()) {
                    return true;
                }

                // If no solution, reset the cell and try the next number
                grid.fillCell(row, col, 0);
            }
        }

        return false; // "No solution" if no valid numbers fit
    }

    /**
     * Attempts to solve the Sudoku puzzle and prints the solution if successful.
     * If no solution is found, it prints "No solution".
     */
    public void solveIt() {
        if (solve()) {
            grid.print();
        } else {
            System.out.print("No solution");
        }
    }

    /**
     * The main method demonstrates the usage of the SudokuSolver class by
     * creating a Sudoku puzzle, solving it, and printing the solution.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // A sample 9x9 Sudoku puzzle
        int[][] puzzle = {
            {5, 6, 8, 2, 3, 1, 7, 9, 4},
            {3, 9, 4, 6, 8, 7, 1, 2, 5},
            {2, 7, 1, 4, 9, 5, 6, 3, 8},
            {7, 8, 6, 5, 1, 3, 2, 4, 9},
            {1, 3, 5, 9, 2, 4, 8, 6, 7},
            {9, 4, 2, 8, 7, 6, 3, 5, 1},
            {8, 2, 3, 1, 5, 9, 4, 7, 6},
            {6, 5, 7, 3, 4, 8, 9, 1, 2},
            {4, 1, 9, 7, 6, 2, 5, 8, 3}
        };

        // Create a SudokuGrid and SudokuSolver
        SudokuGrid grid = new SudokuGrid(puzzle);
        SudokuSolver solver = new SudokuSolver(grid);
        solver.solveIt(); // Solve and print the result
    }
}
