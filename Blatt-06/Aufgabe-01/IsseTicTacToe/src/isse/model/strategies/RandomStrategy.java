package isse.model.strategies;
import java.util.Random;
import java.util.List;
import java.util.Vector;

import isse.model.FieldState;
import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;
import isse.model.Player;

public class RandomStrategy implements PlayStrategy{

	private List<Integer> freie = new Vector<Integer>();
	
	public Move getMove(GameBoard board) {
		
		freie.clear();
		
		for(int i = 0; i < board.getSize(); ++i) {
			for(int j = 0; j < board.getSize(); ++j){
				if(board.read(i, j) == FieldState.EMPTY) {
					freie.add(i*3+j);
					//return new Move(i,j);
				}
			}
		}
		if(freie.size()==0)
			return null;
		double rand = Math.random();
		int res = freie.get((int) (rand*freie.size()));
		
		return new Move((int) res/3 , res % 3);
	}
	
}
