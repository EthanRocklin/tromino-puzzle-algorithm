import java.util.Random;

public class TrominoPuzzleSolver {


    public static void main(String[] args) {
        // Generates an empty grid
        int n = 2;
        int[][] grid = new int[n][n];
        // Generates a random missing square (marked with -1) in the grid
        Random rand = new Random();
        // Missing Row Index = mRow, Missing Column Index = mCol
        int mRow = rand.nextInt(0, n);
        int mCol = rand.nextInt(0, n);
        grid[mRow][mCol] = -1;
        // Starts solving the puzzle
        solvePuzzle(grid, mRow, mCol);
    }

    private static int[][] solvePuzzle(int[][] grid, int mRow, int mCol) {
        int[][] grid = solvePuzzleHelper(grid, mRow, mCol, 0);
        return grid;
    }

    private static int[][] solvePuzzleHelper(int[][] grid, int mRow, int mCol, int tCount) {
            tCount++;
            int tCurr = tCount;
            int n = grid.length;
            // Base Case: When each tow only has two elements but the missing/occupied
            // square could be any of them
            if (grid.length == 2) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if (grid[i][j] == 0) {
                            grid[i][j] = tCurr;
                        }
                    }
                }
            } else {   // Recursive Cases
                // Top-Left Quadrant: places part of the current tromino in the center before
                // recursing if the missing square is not in the quadrant
                if (mRow > (n/2-1) || mCol > (n/2)-1) {
                    grid[n/2-1][n/2-1] = tCurr;
                    // Marked square is now occupied for recursive calls; acts as
                    // "missing square" for the quadrant
                    tCount = solvePuzzleHelper(<><><>, mRow, mRol, tCount);
                }
            }
    }

}
