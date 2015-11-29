package isse.model;

/**
 * Just representing the tuple of a move
 * @author isse-soas
 *
 */
public class Move {
	
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int row;
	public int col;
	
	@Override
	public String toString() {
		return String.format("[%d, %d]", row, col);
	}
}
