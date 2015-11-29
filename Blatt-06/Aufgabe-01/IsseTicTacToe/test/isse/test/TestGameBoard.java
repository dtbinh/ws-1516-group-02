package isse.test;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import isse.model.GameBoard;
import isse.model.Move;
import isse.model.Player;

public class TestGameBoard {

	@Rule
	public ExpectedException thrown=ExpectedException.none();
	
	private GameBoard board;
	
	@Before
	public void setup() {
		board = new GameBoard();
	}
	
	@Test
	public void testPrinting() {
		System.out.println(board);
		board.move(Player.CROSSES, 0, 0);

		System.out.println(board);
		board.move(Player.NOUGHTS, 0, 1);
		System.out.println(board);
	}
	
	@Test 
	public void testHasWon() {
		board.move(Player.CROSSES, 0, 0);

		board.move(Player.CROSSES, 0, 1);
		System.out.println(board);
		
		board.move(Player.CROSSES, 0, 2);
		Assert.assertTrue(board.isWonBy(Player.CROSSES, new Move(0,2)));
	}
	
	@Test
	public void testInvalidMove() {
		board.move(Player.CROSSES, 0, 0);
		
		// second time assigning [0,0]
		thrown.expect(RuntimeException.class);
		board.move(Player.NOUGHTS, 0, 0);
	}

}
