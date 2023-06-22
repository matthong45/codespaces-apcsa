public class Board {

	private String[][] squares;

	static final int boardSize = 10;

	public Board() {
		resetBoard();
	}

	public void setBoard(String[][] testBoard) {
		squares = testBoard;
	}

	public void resetBoard() {
		squares = new String[boardSize][boardSize];
		for (int r = 0; r < squares.length; r++) {
			for (int c = 0; c < squares.length; c++) {
				squares[r][c] = "-";
			}
		}
	}

	public String toString() {
		String boardAsString = "";
		for (String[] r : squares) {
			for (String c : r) {
				boardAsString += c + " ";
			}
			boardAsString += "\n";
		}
		return boardAsString;
	}

	public boolean addShip(int row, int col, int len, boolean horizontal) {
		if (!checkBounds(row, col))
			return false;
		if (horizontal) {
			return addHorizontalShip(row, col, len);
		} else {
			return addVerticalShip(row, col, len);
		}
	}

	private boolean addVerticalShip(int row, int col, int len) {
		// First Calculate if the location is valid
		// Add modify the board appropriately
		if (row + len > boardSize)
			return false;
		for (int j = 0; j < len; j++) {
			if (squares[row + j][col] != "-")
				return false;
		}

		// If the validations passed we can update the board
		for (int j = 0; j < len; j++) {
			squares[row + j][col] = "b";
		}
		return true;
	}

	private boolean addHorizontalShip(int row, int col, int len) {
		// First Calculate if the location is valid
		// Add modify the board appropriately
		if (col + len > boardSize)
			return false;
		for (int i = 0; i < len; i++) {

			if (squares[row][col + i] != "-")
				return false;
		}

		// If the validations passed we can update the board
		for (int i = 0; i < len; i++) {
			squares[row][col + i] = "b";
		}
		return true;
	}

	public boolean foundShip(int len) {
		// Iterates through all rows of array
		for (int r = 0; r < squares.length; r++) {
			/*
			 * Iterates through each element in the row, looking for consecutive runs of the
			 * String character "b". This is done by using an interior while loop which is
			 * entered when a "b" is found and increments both the variable c and the
			 * variable foundLen as long as "b" appears. If foundLen is equal to len at the
			 * end of this interior loop, the value true is returned.
			 */
			for (int c = 0; c < squares[r].length; c++) {
				int count = 0;
				while (c < squares[r].length && squares[r][c].equals("b")) {
					count++;
					c++;
				}
				// Does not handle cases with consecutive squares well. It assumes that the
				// ships will need to be islands.
				if (count == len)
					return true;
			}
		}

		// Works as above, but checks each column for vertical runs
		for (int c = 0; c < squares[0].length; c++) {
			for (int r = 0; r < squares.length; r++) {
				int count = 0;
				while (r < squares.length && squares[r][c].equals("b")) {
					count++;
					r++;
				}
				if (count == len)
					return true;
			}
		}

		return false;
	}

	public int shoot(int row, int col) {
		if (!checkBounds(row, col))
			return -1;
		if (squares[row][col].equals("b")) {
			squares[row][col] = "x";
			return 1;
		} else if (squares[row][col].equals("-")) {
			squares[row][col] = "m";
			return 0;
		}
		return 2;
	}

	private boolean checkBounds(int row, int col) {
		if (row >= boardSize || col >= boardSize || row < 0 || col < 0)
			return false;
		return true;
	}

	public boolean gameOver() {
		for (String[] row : squares)
			for (String square : row)
				if (square.equals("b"))
					return false;
		return true;
	}

}

