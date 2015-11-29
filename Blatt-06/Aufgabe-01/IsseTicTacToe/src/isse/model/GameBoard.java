package isse.model;

/**
 * Represents a board for 
 * given n at a current state
 * @author isse-soas
 *
 */
public class GameBoard {
	
	private FieldState[][] board;
	
	public GameBoard(int n) {
		board = new FieldState[n][n];
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j ) {
				board[i][j] = FieldState.EMPTY;
			}
		}
	}
	
	/**
	 * Default constructor for standard tic tac toe
	 */
	public GameBoard() {
		this(3);
	}
	
	public void move(Player player, int row, int col) {
		if(board[row][col] != FieldState.EMPTY)
			throw new RuntimeException("Invalid move, field already taken!");
		
		board[row][col] = player.getFieldState();
	}
	
	/**
	 * Read method for play strategies
	 * @return
	 */
	public int getSize() {
		return board.length;
	}
	
	public FieldState read(int row, int col) {
		if(row < 0 || row >= board.length || col < 0 || col >= board.length)
			return null;
		else 
			return board[row][col];
	}
	
	/**
	 * Pretty printing the game board
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(board.length*board.length*4);
		sb.append('-');
		for(int i = 0; i < board.length; ++i) {
			sb.append("----");
		}
		sb.append("\n");
		
		for(int i = 0; i < board.length; ++i) {
			for(int j = 0; j < board.length; ++j) {
				if(j == 0)
					sb.append('|');
				sb.append(' ');
				sb.append(board[i][j]);
				sb.append(" |");
			}
			sb.append("\n");
		}
		sb.append('-');
		for(int i = 0; i < board.length; ++i) {
			sb.append("----");
		}
		return sb.toString();
	}

	public void move(Player turn, Move move) {
		move(turn, move.row, move.col);		
	}

	public boolean isFull() {
		for(int i = 0; i < board.length; ++i) {
			for(int j = 0; j < board.length; ++j)
				if(board[i][j] == FieldState.EMPTY)
					return false;
		}
		return true;
	}

	/**
	 * Need to check only the last move 
	 * @param turn
	 * @param lastMove
	 * @return
	 */
	public boolean isWonBy(Player turn, Move lastMove) {
		// check row
		boolean seenDifferent = false;
		for(int col = 0; col < board.length && !seenDifferent; ++col) {
			seenDifferent = seenDifferent || (board[lastMove.row][col] != turn.getFieldState());
		}
		if(!seenDifferent)
			return true;
		
		// check col
		seenDifferent = false;
		for(int row = 0; row < board.length && !seenDifferent; ++row) {
			seenDifferent = seenDifferent || (board[row][lastMove.col] != turn.getFieldState());
		}
		if(!seenDifferent)
			return true;
		
		// check diagonal (only if lastMove was actually on one of the diagonals)
		if(lastMove.row == lastMove.col) { // main diagonal
			seenDifferent = false;
			for(int d = 0; d < board.length && !seenDifferent; ++d) {
				seenDifferent = seenDifferent || (board[d][d] != turn.getFieldState());
			}			
			if(!seenDifferent)
				return true;
		}

		// secondary diagonal
		if(lastMove.row + lastMove.col == board.length-1) {
			seenDifferent = false;
			for(int d = 0; d < board.length && !seenDifferent; ++d) {
				seenDifferent = seenDifferent || (board[d][(board.length - 1) - d] != turn.getFieldState());
			}	
			if(!seenDifferent)
				return true;
		}
		
		return false;
	}
}
