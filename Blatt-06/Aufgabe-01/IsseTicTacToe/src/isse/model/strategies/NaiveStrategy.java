package isse.model.strategies;

import isse.model.FieldState;
import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;

/**
 * Takes the first free field
 * @author isse-soas
 *
 */
public class NaiveStrategy implements PlayStrategy{

	@Override
	public Move getMove(GameBoard board) {
		
		for(int i = 0; i < board.getSize(); ++i) {
			for(int j = 0; j < board.getSize(); ++j){
				if(board.read(i, j) == FieldState.EMPTY) {
					return new Move(i,j);
				}
			}
		}
		return null;
	}

}
