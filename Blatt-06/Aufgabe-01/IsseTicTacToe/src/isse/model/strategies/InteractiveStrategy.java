package isse.model.strategies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import isse.model.GameBoard;
import isse.model.Move;
import isse.model.PlayStrategy;

public class InteractiveStrategy implements PlayStrategy {

	private BufferedReader in;

	public InteractiveStrategy() {
		in = new BufferedReader(new InputStreamReader(System.in));
		
	}
	
	@Override
	public Move getMove(GameBoard board) {
		System.out.print("Please enter your move as: \"r c\" ");
		String nextLine = null;
		try {
			 nextLine = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scanner in = new Scanner(nextLine);
		int r = in.nextInt();
		int c = in.nextInt(); 
		in.close();
		
		return new Move(r, c);
	}

}
