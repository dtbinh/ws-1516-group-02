package isse.model.strategies;

import isse.model.FieldState;
import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;
import isse.model.Player;

import java.util.List;
import java.util.Vector;

public class SmartStrategy implements PlayStrategy {

	public Move getMove(GameBoard board) {
		
		Vector<Integer> crosses = new Vector<Integer>();
		Vector<Integer> noughts = new Vector<Integer>();
		Vector<Integer> empty = new Vector<Integer>();
		
		//Count Crosses, Noughts and Emptys
		for (int i = 0; i < board.getSize(); ++i) {
			for (int j = 0; j < board.getSize(); ++j) {
				if (board.read(i, j) == FieldState.CROSSES) {
					crosses.add(i * 3 + j);
				}
				else if(board.read(i, j) == FieldState.NOUGHTS) {
					noughts.add(i * 3 + j);
				}
				else{
					empty.add(i * 3 + j);
				}
			}
		}
		
		//Make the move for the player
		if(crosses.size()==noughts.size()){
			//Crosses' turn
			//Check for instant win
			Move m = findPlaceInThree(board,crosses);
			if(m==null)
				//No instant win possible, check for defense move
				m = findPlaceInThree(board,noughts);
			if(m==null)
				//No defense move possibility detected, make random move
				m = randomMove(board,empty);
			return m;
		}
		else{
			//Noughts' turn
			//Check for instant win
			Move m = findPlaceInThree(board,noughts);
			if(m==null)
				//No instant win possible, check for defense move
				m = findPlaceInThree(board,crosses);
			if(m==null)
				//No defense move possibility detected, make random move
				m = randomMove(board,empty);
			return m;
		}

	}
	
	private Move findPlaceInThree(GameBoard board, Vector<Integer> player){
		
		// 2 in einer Spalte
		int[] sameColumn = new int[3];
		for (int i = 0; i < player.size(); i++) {
			int column = player.get(i) % 3;
			if (column < 0)
				column *= -1;
			sameColumn[column]++;
		}

		for (int i = 0; i < 3; i++) {
			if (sameColumn[i] == 2) {
				for (int j = 0; j < 3; j++) {
					if (board.read(j, i) == FieldState.EMPTY) {
						return new Move(j, i);
					}
				}
			}
		}

		// 2 in einer Reihe
		int[] sameRow = new int[3];
		for (int i = 0; i < player.size(); i++) {
			int row = (int) (player.get(i) / 3);
			sameRow[row]++;
		}

		for (int i = 0; i < 3; i++) {
			if (sameRow[i] == 2) {
				for (int j = 0; j < 3; j++) {
					if (board.read(i, j) == FieldState.EMPTY) {
						return new Move(i, j);
					}
				}
			}
		}

		// 2 diagonal

		int[] sameDiag = new int[2];
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i) == 0 || player.get(i) == 4 || player.get(i) == 8) {
				sameDiag[0]++;
			}
			if (player.get(i) == 2 || player.get(i) == 4 || player.get(i) == 6) {
				sameDiag[1]++;
			}
		}

		if (sameDiag[0] == 2) {
			for (int j = 0; j < 3; j++) {
				if (board.read(j, j) == FieldState.EMPTY)
					return new Move(j, j);
			}
		}
		if (sameDiag[1] == 2) {
			for (int j = 0; j < 3; j++) {
				if (board.read(j, 2 - j) == FieldState.EMPTY)
					return new Move(j, 2 - j);
			}
		}
		
		return null;
		
	}

	private Move randomMove(GameBoard board, Vector<Integer> empty){
		
		double rand = Math.random();
		int res = empty.get((int) (rand * empty.size()));
		return new Move((int) res / 3, res % 3);
	}
	
	
	/**
	 * veraltet
	 * @param board
	 * @param mine
	 * @param his
	 * @return
	 */
	/*private Move makeDecision(GameBoard board, Vector<Integer> mine, Vector<Integer> his){
		// 2 in einer Spalte
				int[] sameColumn = new int[3];
				for (int i = 0; i < mine.size(); i++) {
					int column = mine.get(i) % 3;
					if (column < 0)
						column *= -1;
					sameColumn[column]++;
				}

				for (int i = 0; i < 3; i++) {
					if (sameColumn[i] == 2) {
						for (int j = 0; j < 3; j++) {
							if (board.read(j, i) == FieldState.EMPTY) {
								return new Move(j, i);
							}
						}
					}
				}

				// 2 in einer Reihe
				int[] sameRow = new int[3];
				for (int i = 0; i < mine.size(); i++) {
					int row = (int) (mine.get(i) / 3);
					sameRow[row]++;
				}

				for (int i = 0; i < 3; i++) {
					if (sameRow[i] == 2) {
						for (int j = 0; j < 3; j++) {
							if (board.read(i, j) == FieldState.EMPTY) {
								return new Move(i, j);
							}
						}
					}
				}

				// 2 diagonal

				int[] sameDiag = new int[2];
				for (int i = 0; i < mine.size(); i++) {
					if (mine.get(i) == 0 || mine.get(i) == 4 || mine.get(i) == 8) {
						sameDiag[0]++;
					}
					if (mine.get(i) == 2 || mine.get(i) == 4 || mine.get(i) == 6) {
						sameDiag[1]++;
					}
				}

				if (sameDiag[0] == 2) {
					for (int j = 0; j < 3; j++) {
						if (board.read(j, j) == FieldState.EMPTY)
							return new Move(j, j);
					}
				}
				if (sameDiag[1] == 2) {
					for (int j = 0; j < 3; j++) {
						if (board.read(j, 2 - j) == FieldState.EMPTY)
							return new Move(j, 2 - j);
					}
				}
				

				//Kein sofortiger Sieg möglich, wehre Gegner ab

				// 2 in einer Spalte
				int[] enemyColumn = new int[3];
				for (int i = 0; i < his.size(); i++) {
					int column = his.get(i) % 3;
					if (column < 0)
						column *= -1;
					enemyColumn[column]++;
				}

				for (int i = 0; i < 3; i++) {
					if (enemyColumn[i] == 2) {
						for (int j = 0; j < 3; j++) {
							if (board.read(j, i) == FieldState.EMPTY) {
								return new Move(j, i);
							}
						}
					}
				}

				// 2 in einer Reihe
				int[] enemyRow = new int[3];
				for (int i = 0; i < his.size(); i++) {
					int row = (int) (his.get(i) / 3);
					enemyRow[row]++;
				}

				for (int i = 0; i < 3; i++) {
					if (enemyRow[i] == 2) {
						for (int j = 0; j < 3; j++) {
							if (board.read(i, j) == FieldState.EMPTY) {
								return new Move(i, j);
							}
						}
					}
				}

				// 2 diagonal

				int[] enemyDiag = new int[2];
				for (int i = 0; i < his.size(); i++) {
					if (his.get(i) == 0 || his.get(i) == 4 || his.get(i) == 8) {
						enemyDiag[0]++;
					}
					if (his.get(i) == 2 || his.get(i) == 4 || his.get(i) == 6) {
						enemyDiag[1]++;
					}
				}

				if (enemyDiag[0] == 2) {
					for (int j = 0; j < 3; j++) {
						if (board.read(j, j) == FieldState.EMPTY)
							return new Move(j, j);
					}
				}
				if (enemyDiag[1] == 2) {
					for (int j = 0; j < 3; j++) {
						if (board.read(j, 2 - j) == FieldState.EMPTY)
							return new Move(j, 2 - j);
					}
				}
				
				// sonst wähle zufällig

				for (int i = 0; i < board.getSize(); ++i) {
					for (int j = 0; j < board.getSize(); ++j) {
						if (board.read(i, j) == FieldState.EMPTY) {
							freie.add(i * 3 + j);
							// return new Move(i,j);
						}
					}
				}
				if (freie.size() == 0)
					return null;
				double rand = Math.random();
				int res = freie.get((int) (rand * freie.size()));

				return new Move((int) res / 3, res % 3);
		
	}*/

}
