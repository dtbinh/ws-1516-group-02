package isse.model.strategies;

import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;

/**
 * Decorates another strategy to have better visible gameplay;
 * basically just a UI feature
 * @author isse-soas
 *
 */
public class DelayedPlayStrategy implements PlayStrategy {

	private PlayStrategy decoratedStrategy;
	private int delayInMs;
	
	public DelayedPlayStrategy(PlayStrategy decoratedStrategy, int delayInMs) {
		this.decoratedStrategy = decoratedStrategy;
		this.delayInMs = delayInMs;
	}
	
	@Override
	public Move getMove(GameBoard board) {
		try {
			Thread.sleep(delayInMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return decoratedStrategy.getMove(board);
	}

}
