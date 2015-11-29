package isse.test;

import static org.junit.Assert.*;
import isse.model.GameEngine;
import isse.model.PlayStrategy;
import isse.model.Player;
import isse.model.strategies.InteractiveStrategy;
import isse.model.strategies.NaiveStrategy;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TestGameEngine {

	private GameEngine engine;
	
	@Before
	public void setup() {
		engine = new GameEngine();
	}
	
	@Test
	public void testNaiveStrategies() {
		PlayStrategy naive = new NaiveStrategy();
		engine.registerStrategy(Player.CROSSES, naive);
		engine.registerStrategy(Player.NOUGHTS, naive);
		
		// now play
		engine.play();
	}
	
	@Test
	@Ignore
	public void testHumanStrategies() {
		PlayStrategy interactive = new InteractiveStrategy();
		engine.registerStrategy(Player.CROSSES, interactive);
		engine.registerStrategy(Player.NOUGHTS, interactive);
		
		// now play
		engine.play();
	}

}
