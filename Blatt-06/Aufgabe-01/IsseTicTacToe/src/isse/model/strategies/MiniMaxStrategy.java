package isse.model.strategies;

import java.util.Vector;

import Structures.TreeNode;
import isse.model.FieldState;
import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;

public class MiniMaxStrategy implements PlayStrategy {

	
	private TreeNode tree;
	
	@Override
	public Move getMove(GameBoard board) {
		
		if(tree==null){
			tree = TreeNode.createTree();
			fillTree(tree);
			rateTree(tree);
		}
		
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
		

		
		//Maximizer (X) turn
		if((crosses.size()+noughts.size())%2==0){
			//set TreePosition according to last move
			if(noughts.size()!=0){
				int last = noughts.lastElement();
					for(int i=0;i<tree.getChildren().size();i++){
						if(tree.getChildren().get(i).getNoughts().contains(last)){
							tree = tree.getChildren().get(i);
							break;
						}
					}
			}
			
			//Check Children with best values
			TreeNode bestNode = tree.getChildren().get(0);
			for(int i=1;i<tree.getChildren().size();i++){
				if(tree.getChildren().get(i).getValue()>bestNode.getValue()){
					bestNode = tree.getChildren().get(i);
				}
			}
			//Check the Position of the Cross that the Child did set
			int cross = -1;
			for(int i : bestNode.getCrosses()){
				if(!tree.getCrosses().contains(i)){
					cross = i;
				}

			}
			//Set new current Gamestate/Treenode
			tree = bestNode;
			//return Move
			return new Move((int) cross/3 , Math.abs(cross % 3));
		}
		//Minimizer Turn
		else{
			
			//set TreePosition according to last move
			if(crosses.size()!=0){
				int last = crosses.lastElement();
					for(int i=0;i<tree.getChildren().size();i++){
						if(tree.getChildren().get(i).getCrosses().contains(last)){
							tree = tree.getChildren().get(i);
							break;
						}
					}
			}
			
			//Check Children with best values
			TreeNode bestNode = tree.getChildren().get(0);
			for(int i=1;i<tree.getChildren().size();i++){
				if(tree.getChildren().get(i).getValue()<bestNode.getValue()){
					bestNode = tree.getChildren().get(i);
				}
			}
			//Check the Position of the Nought that the Child did set
			int nought = -1;
			for(int i : bestNode.getNoughts()){
				if(!tree.getNoughts().contains(i)){
					nought = i;
				}

			}
			//Set new current Gamestate/Treenode
			tree = bestNode;
			//return Move
			return new Move((int) nought/3 , Math.abs(nought % 3));
		}
	}
	
	private void fillTree(TreeNode tree){
		
		int a = tree.getCrosses().size()+tree.getNoughts().size();
		
		//No Space left
		if(a==9)
			return;
		
		//Crosses Turn
		if(a%2 == 0){
			//Check if Noughts won the game
			if(containsFinalState(tree.getNoughts()))
				return;
			
			//Create new Childnodes
			for(int i = 0; i < 9 ;i++){
				if(tree.getCrosses().contains(i)||tree.getNoughts().contains(i))
					continue;
				Vector<Integer> childVectorCrosses = (Vector<Integer>) tree.getCrosses().clone();
				childVectorCrosses.add(i);
				Vector<Integer> childVectorNoughts = (Vector<Integer>) tree.getNoughts().clone();
				TreeNode child = tree.addChild(childVectorCrosses, childVectorNoughts);
				fillTree(child);
			}
		}
		//Noughts Turn
		else{
			//Check if Crosses won the game
			if(containsFinalState(tree.getCrosses()))
				return;
			
			//Create new Childnodes
			for(int i = 0; i < 9 ;i++){
				if(tree.getCrosses().contains(i)||tree.getNoughts().contains(i))
					continue;
				Vector<Integer> childVectorNoughts = (Vector<Integer>) tree.getNoughts().clone();
				childVectorNoughts.add(i);
				Vector<Integer> childVectorCrosses = (Vector<Integer>) tree.getCrosses().clone();
				TreeNode child = tree.addChild(childVectorCrosses, childVectorNoughts);
				fillTree(child);
			}

		}
		
		return;
	}
	
	private void rateTree(TreeNode tree){
		
		Vector<TreeNode> children = tree.getChildren();
		
		for(int i=0;i<children.size();i++){
			if(children.get(i).getValue()==null){
				rateTree(children.get(i));
			}
		}
		
		int height = tree.getCrosses().size()+tree.getNoughts().size();
		
		//TreeNode is leaf
		if(children.size()==0){
			int steps = tree.getCrosses().size()+tree.getCrosses().size();
			
			if(containsFinalState(tree.getCrosses())){
				tree.setValue(20-steps);
				//tree.setValue(20);

				return;
			}
			else if(containsFinalState(tree.getNoughts())){
				tree.setValue(-20 - steps);
				//tree.setValue(-20);
				return;
			}
			else{
				tree.setValue(0-steps);
				//tree.setValue(0);

				return;
			}
		}
		
		//TreeNode is inner Node
		//Maximizer
		if(height%2 == 0){
			/*int val = 0;
			for(int i=0;i<tree.getChildren().size();i++){
				val += tree.getChildren().get(i).getValue();
			}*/
			
			int max = -1;
			for(int i=0;i<tree.getChildren().size();i++){
				if(tree.getChildren().get(i).getValue()==10){
					max = 10;
					break;
				}
				if(tree.getChildren().get(i).getValue()>max){
					max = tree.getChildren().get(i).getValue();
				}
			}
			tree.setValue(max);
			//tree.setValue(val);
		}
		//Minimizer
		else{
			int min = 1;
			for(int i=0;i<tree.getChildren().size();i++){
				if(tree.getChildren().get(i).getValue()== -10){
					min = -10;
					break;
				}
				if(tree.getChildren().get(i).getValue()<min){
					min = tree.getChildren().get(i).getValue();
				}
			}
			tree.setValue(min);
		}
		
		return;
	}
	
	private boolean containsFinalState(Vector<Integer> player){
		
		//3 in einer Spalte
		int[] sameColumn = new int[3];
		for (int i = 0; i < player.size(); i++) {
			int column = player.get(i) % 3;
			if (column < 0)
				column *= -1;
			sameColumn[column]++;
		}
		for (int i : sameColumn){
			if(i==3)
				return true;
		}
		
		//3 in einer Reihe
		int[] sameRow = new int[3];
		for (int i = 0; i < player.size(); i++) {
			int row = (int) (player.get(i) / 3);
			sameRow[row]++;
		}
		for (int i : sameRow){
			if(i==3)
				return true;
		}
		
		//3 in einer Diagonalen
		int[] sameDiag = new int[2];
		for (int i = 0; i < player.size(); i++) {
			if (player.get(i) == 0 || player.get(i) == 4 || player.get(i) == 8) {
				sameDiag[0]++;
			}
			if (player.get(i) == 2 || player.get(i) == 4 || player.get(i) == 6) {
				sameDiag[1]++;
			}
		}
		
		for (int i : sameDiag){
			if(i==3)
				return true;
		}
		
		//keine 3 richtigen
		return false;

	}
}
