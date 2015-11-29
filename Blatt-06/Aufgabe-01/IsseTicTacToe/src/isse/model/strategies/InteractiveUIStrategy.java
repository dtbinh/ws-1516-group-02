package isse.model.strategies;

import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;

/**
 * Waits for a button click and then plays it 
 * @author isse-soas
 *
 */
public class InteractiveUIStrategy implements PlayStrategy {

	// only for synchronisation
	private Move uiMove;
	
	public void setUIMove(Move uiMove) {
		this.uiMove = uiMove;
	}
	
	@Override
	public synchronized Move getMove(GameBoard board) {
		// wait for the uiMove object
		try {
			synchronized (uiMove) {
				uiMove.wait();
			}			
			return uiMove;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new Move(0, 0); // some stupid default move
	}

}
