import java.util.Random;

public class TrominoPuzzleSolver {

    int[][] grid

    public static void main(String[] args) {
        // Generates an empty grid
        int n = 2;
        int[][] grid = new int[n][n];
        // Generates a random missing square (marked with -1) in the grid
        Random rand = new Random();
        int missingRowIndex = rand.nextInt(0, n);
        int missingColIndex = rand.nextInt(0, n);
        grid[missingRowIndex][missingColIndex] = -1;
        // Starts solving the puzzle
        solvePuzzle(grid, missingRowIndex, missingColIndex, 0);
    }

    private static void solvePuzzle(int[][] grid, int missingRowIndex, int missingColIndex, int tCount) {
            tCount++;
            int tCurr = tCount;
            // Base Case: When each tow only has two elements but the missing/occupied
            // square could be any of them
            if (grid.length == 2) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        if
                    }
                }
            }
    }

}
