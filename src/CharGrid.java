public class CharGrid {
    private static char[][] grid;

    /**
     * Constructs a new CharGrid with the given grid.
     * Does not make a copy.
     * @param grid
     */
    public CharGrid(char[][] grid) {
        this.grid = grid;
    }

    /**
     * Returns the area for the given char in the grid.
     * @param ch char
     * @return area for given char
     */
    public static int charArea(char ch) {
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        boolean contains = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == ch) {
                    minX = (i < minX) ? i : minX;
                    minY = (j < minY) ? j : minY;
                    maxX = (i > maxX) ? i : maxX;
                    maxY = (j > maxY) ? j : maxY;
                    contains = true;
                }
            }
        }
        if (contains) {
            return (maxX - minX + 1) * (maxY - minY + 1);
        } else {
            return 0;
        }
        // YOUR CODE HERE
    }

    /**
     * Returns the count of '+' figures in the grid
     * @return number of + in grid
     */
    public static int countPlus() {
        if(grid.length<3 || grid[0].length<3)
            return 0;
        int count=0;
        for (int i = 1; i <grid.length-1 ; i++) {
            for (int j = 1; j <grid[i].length-1 ; j++) {
                if(grid[i][j]==grid[i-1][j] && grid[i][j]==grid[i+1][j] &&
                        grid[i][j]==grid[i][j-1] && grid[i][j]==grid[i][j+1]){
                    count++;
                }
            }
        }
        return count;
        // YOUR CODE HERE
    }
}
