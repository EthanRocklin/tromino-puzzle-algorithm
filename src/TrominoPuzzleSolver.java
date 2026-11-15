import java.util.Random;

/**
 * This Java file contains an algorithm for solving the Tromino puzzle.
 * The puzzle consists of a 2^m-by-2^m (m > 0) grid with a missing square.
 * The goal is to fill any possible grid with trominos, which are L-shape
 * pieces made of 3 units, and to not occupy the missing square with part
 * of a tromino.
 *
 * @author Ethan Rocklin
 */

public class TrominoPuzzleSolver {

    private static final int GRID_SIZE = 4;

    public static void main(String[] args) {
        // Checks that the grid size is a power of 2
        double baseTwoExponent = Math.log10(GRID_SIZE)/Math.log10(2);
        if (baseTwoExponent != (int) baseTwoExponent) {
            System.out.print("ERROR: Grid size must be a power of 2, with the minimum grid size being 2 x 2");
        } else {
            // Generates an empty grid
            int[][] grid = new int[GRID_SIZE][GRID_SIZE];
            // Starting and ending rows and columns
            int startRow = 0;
            int startCol = 0;
            int endRow = grid.length - 1;
            int endCol = grid.length - 1;
            int tCount = 0;
            // Generates a random missing square (marked with -1) in the grid
            Random rand = new Random();
            int mRow = rand.nextInt(0, GRID_SIZE);
            int mCol = rand.nextInt(0, GRID_SIZE);
            grid[mRow][mCol] = -1;
            // Starts solving the puzzle; displays grid before and after solving
            System.out.print("\nGrid Before Solving:\n\n");
            printGrid(grid);
            int totalTrominos = solvePuzzle(grid, startRow, endRow, startCol, endCol, mRow, mCol, tCount);
            System.out.print("Grid After Solving:\n\n");
            printGrid(grid);
            System.out.print("Trominos Placed = " + totalTrominos + "\n");
        }
    }

    /**
     * This method places trominos in the grid. Time Complexity: Θ(n)
     *
     * @param grid an integer matrix containing the state of the tromino quadrant
     * @param startRow the index representing the first row of the grid or quadrant
     * @param endRow the index representing the last row of the grid or quadrant
     * @param startCol the index representing the first column of the grid or quadrant
     * @param endCol the index representing the last column of the grid or quadrant
     * @param mRow the index representing the row index of the missing square
     * @param mCol the index representing the column index of the missing square
     * @param tCount the number of trominos placed
     * @return the total number of trominos placed on the grid
     */
    private static int solvePuzzle(int[][] grid, int startRow, int endRow, int startCol, int endCol, int mRow, int mCol, int tCount) {
        tCount++;
        int tCurr = tCount;
        // Base Case: When each row/column only has two elements
        // but the missing/occupied square could be any of them
        if (endRow - startRow < 2) {
            for (int i = startRow; i <= endRow; i++) {
                for (int j = startCol; j <= endCol; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = tCurr;
                    }
                }
            }
        }
        // Recursive Cases
        else {
            // Computing new bounds for subgrids
            int newEndRow = (endRow + startRow) / 2;
            int newEndCol = (endCol + startCol) / 2;
            int newStartRow = newEndRow + 1;
            int newStartCol = newEndCol + 1;

            // Top-Left Quadrant: places part of the current tromino in the center before
            // recursing if the missing square is not in the quadrant
            if (mRow > newEndRow || mCol > newEndCol) {
                grid[newEndRow][newEndCol] = tCurr;
                // Marked square is now occupied for recursive calls; acts as
                // "missing square" for the quadrant
                tCount = solvePuzzle(grid, startRow, newEndRow, startCol, newEndCol, newEndRow, newEndCol, tCount);
            } else {
                tCount = solvePuzzle(grid, startRow, newEndRow, startCol, newEndCol, mRow, mCol, tCount);
            }

            // Top-right quadrant
            if (mRow > newEndRow || mCol < newEndCol) {
                grid[newEndRow][newStartCol] = tCurr;
                tCount = solvePuzzle(grid, startRow, newEndRow, newStartCol, endCol, newEndRow, newStartCol, tCount);
            } else {
                tCount = solvePuzzle(grid, startRow, newEndRow, newStartCol, endCol, mRow, mCol, tCount);
            }

            // Bottom-left quadrant
            if (mRow < newStartRow || mCol > newEndCol) {
                grid[newStartRow][newEndCol] = tCurr;
                tCount = solvePuzzle(grid, newStartRow, endRow, startCol, newEndCol, newStartRow, newEndCol, tCount);
            } else {
                tCount = solvePuzzle(grid, newStartRow, endRow, startCol, newEndCol, mRow, mCol, tCount);
            }

            // Bottom-right quadrant
            if (mRow < newStartRow || mCol < newStartCol) {
                grid[newStartRow][newStartCol] = tCurr;
                tCount = solvePuzzle(grid, newStartRow, endRow, newStartCol, endCol, newStartRow, newStartCol, tCount);
            } else {
                tCount = solvePuzzle(grid, newStartRow, endRow, newStartCol, endCol, mRow, mCol, tCount);
            }
        }
        return tCount;
    }

    /**
     * This method prints the contents of the tromino grid.
     * @param grid an integer matrix containing the state of the tromino grid
     */
    private static void printGrid(int[][] grid) {
        // Dynamic column sizing for printing depending on the size of the grid
        int columnWidth = (int) (Math.log10(GRID_SIZE)/Math.log10(2)) + 1;
        String format = "%-" + columnWidth + "d";
        // Iteratively prints the grid's contents
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                System.out.printf(format, grid[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
