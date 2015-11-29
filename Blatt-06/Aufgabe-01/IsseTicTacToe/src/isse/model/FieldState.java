package isse.model;

/**
 * Denotes whether noughts ( O )
 * or crosses ( X ) mark a field
 * or if it is empty
 * @author isse-soas
 *
 */
public enum FieldState {
	EMPTY,
	NOUGHTS,
	CROSSES;
	
	public String toString() {
		switch(this) {
		case EMPTY:
			return " ";
		case NOUGHTS:
			return "O";
		case CROSSES:
			return "X";
		default:
			return " ";	
		}
	}
}
