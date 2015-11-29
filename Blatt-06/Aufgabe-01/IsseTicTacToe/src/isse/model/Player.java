package isse.model;

/**
 * Represents properties of a player
 * @author isse-soas
 *
 */
public enum Player {
	NOUGHTS,
	CROSSES;
	
	public FieldState getFieldState() {
		switch(this) {
		case NOUGHTS:
			return FieldState.NOUGHTS;
		case CROSSES:
			return FieldState.CROSSES;
		default:
			return null;
		}
	}
	
	public boolean isMaxmizer() {
		return this == CROSSES;
	}
	
	@Override
	public String toString() {
		return getFieldState().toString();
	}
}	
