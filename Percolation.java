import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF wquuf;
    private final WeightedQuickUnionUF normalQU;
    private final int dimension;
    private final int headIndex;
    private final int tailIndex;
    private final boolean[][] openStatus;
    private int numOpen;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0.");
        }

        // Create a n x n + 2 sized
        wquuf = new WeightedQuickUnionUF(n * n + 2);
        normalQU = new WeightedQuickUnionUF(n * n + 1);
        dimension = n;
        headIndex = 0;
        tailIndex = n * n + 1;
        numOpen = 0;
        openStatus = new boolean[n][n];
        // designate the penultimate to be virtual top and the end to be the virtual
        // bottom
        // Logically the rest of the grid is uniform grid with 0 corresponding to (1,1)
        // and n - 1 corresponding to (n,n). n is penultimate and n + 1 is ultimate

    }

    private int getIndex(int row, int col) {
        int index = (row - 1) * dimension + col;
        return index;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBounds(row, col);
        // Check to see if any neighbor states are open
        if (!isOpen(row, col)) {
            numOpen++;
            openStatus[row - 1][col - 1] = true;
            // logic to check ajacent boxes for openings. Look out for corner cases on edges
            // of the grid
            // Brute force before cleverness - check up, down, left, right

            // Check Tail
            if (row == dimension) {
                wquuf.union(getIndex(row, col), tailIndex);
            }

            // Check Head
            if (row == 1) {
                wquuf.union(getIndex(row, col), headIndex);
                normalQU.union(getIndex(row, col), headIndex);
            }

            // North
            if (tryBounds(row, col - 1)) {
                wquuf.union(getIndex(row, col), getIndex(row, col - 1));
                normalQU.union(getIndex(row, col), getIndex(row, col - 1));
            }
            // South
            if (tryBounds(row, col + 1)) {
                wquuf.union(getIndex(row, col), getIndex(row, col + 1));
                normalQU.union(getIndex(row, col), getIndex(row, col + 1));

            }
            // East
            if (tryBounds(row + 1, col)) {
                wquuf.union(getIndex(row, col), getIndex(row + 1, col));
                normalQU.union(getIndex(row, col), getIndex(row + 1, col));

            }
            if (tryBounds(row - 1, col)) {
                wquuf.union(getIndex(row, col), getIndex(row - 1, col));
                normalQU.union(getIndex(row, col), getIndex(row - 1, col));

            }
        }

    }

    private void addingTemp()
    {
        System.out.println("Hello");
    }

    private boolean tryBounds(int row, int col) {
        if (row < 1 || row > dimension || col < 1 || col > dimension || !isOpen(row, col)) {
            return false;
        }
        return true;
    }

    private void checkBounds(int row, int col) {
        // check bounds
        if (row < 1 || row > dimension) {
            throw new IllegalArgumentException("Row is out of bounds.");
        }
        if (col < 1 || col > dimension) {
            throw new IllegalArgumentException("Column is out of bounds.");
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // Perhaps we have to maintain this state? I don't think that we can maintain it
        // inside of the wquuf
        checkBounds(row, col);
        return openStatus[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // I believe we can check for this using a call to the wquuf find method for the
        // headIndex and the node in question
        checkBounds(row, col);
        return normalQU.find(headIndex) == normalQU.find(getIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return wquuf.find(headIndex) == wquuf.find(tailIndex);
    }

    // test client (optional)
    public static void main(String[] args) {
        StdOut.println("Please run PercolationStats instead.");
    }
}