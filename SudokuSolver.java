/**
 * Esta clase implementa un solucionador de sudokus.
 * 
 * @author <a href="sbl1003@alu.ubu.es">Sergio Buil</a>
 * @version 1.0
 */
public class SudokuSolver {

	private static final int SIZE_BOARD = 9;

	public static void main(String[] args) {

		int[][] board = { 
				{ 5, 0, 0, 8, 2, 0, 0, 4, 0 }, 
				{ 0, 7, 0, 0, 0, 6, 3, 0, 0 }, 
				{ 2, 0, 0, 0, 3, 0, 0, 0, 0 },
				{ 4, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				{ 6, 0, 9, 4, 0, 1, 2, 0, 7 }, 
				{ 0, 5, 0, 0, 0, 0, 0, 0, 3 },
				{ 0, 0, 0, 0, 6, 0, 0, 0, 9 }, 
				{ 0, 0, 5, 9, 0, 0, 0, 6, 0 }, 
				{ 0, 2, 0, 0, 1, 8, 0, 0, 4 } 
			};

		if (solveBoard(board)) {
			System.out.println("Solved succesfully");
		} else {
			System.out.println("Unsolvable board:");
		}

		printBoard(board);

	}

	private static void printBoard(int[][] board) {

		for (int row = 0; row < SIZE_BOARD; row++) {

			if (row % 3 == 0 && row != 0) {
				System.out.println("-----------");
			}
			for (int column = 0; column < SIZE_BOARD; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("|");
				}
				System.out.print(board[row][column]);
			}
			System.out.println();
		}
	}

	private static boolean isOnRow(int[][] board, int number, int row) {

		for (int i = 0; i < SIZE_BOARD; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isOnColumn(int[][] board, int number, int column) {

		for (int i = 0; i < SIZE_BOARD; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isOnSquare(int[][] board, int number, int row, int column) {

		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;

		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isLegal(int[][] board, int number, int row, int column) {
		return !isOnRow(board, number, row) && !isOnColumn(board, number, column)
				&& !isOnSquare(board, number, row, column);
	}

	private static boolean solveBoard(int[][] board) {
	
		for (int row = 0; row < SIZE_BOARD; row++) {
			for (int column = 0; column < SIZE_BOARD; column++) {
			
				if (board[row][column] == 0) {
				
					for (int numberToTry = 1; numberToTry <= SIZE_BOARD; numberToTry++) {
					
						if (isLegal(board, numberToTry, row, column)) {
							
							board[row][column] = numberToTry;
							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					
					return false;
				}
			}
		}
		
		return true;
	}
}
