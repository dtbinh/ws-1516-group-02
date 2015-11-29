package isse.control;

import isse.model.GameEngine;
import isse.model.Move;
import isse.model.PlayStrategy;
import isse.model.Player;
import isse.model.strategies.InteractiveUIStrategy;
import isse.ui.TicTacToeUI;

/**
 * Starts up everything, connects model and ui
 * 
 * @author isse-soas
 *
 */
public class Controller {
	private TicTacToeUI ticTacToeUI; // view
	private GameEngine gameEngine; // model
	
	public static void main(String[] args) {
		// first, start up the UI 
		setupNimbus();
		
		Controller controller = new Controller();
		controller.startUI();
		
	}

	private void startUI() {
		ticTacToeUI = new TicTacToeUI();
		
		ticTacToeUI.setController(this);
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				ticTacToeUI.setVisible(true);
			}
		});
	}

	private static void setupNimbus() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TicTacToeUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TicTacToeUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TicTacToeUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TicTacToeUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public void startGame(final PlayStrategy firstStrategy,
			final PlayStrategy secondStrategy) {
		final GameEngine gameEngine = new GameEngine();
		
		gameEngine.addObserver(ticTacToeUI);
		gameEngine.registerStrategy(Player.CROSSES, firstStrategy);
		gameEngine.registerStrategy(Player.NOUGHTS, secondStrategy);

		/* Create and display the form */
		Runnable runnableForThread = new Runnable() {
			public void run() {
				Move strategyMove = new Move(0, 0);
				ticTacToeUI.strategySyncMove = strategyMove;
				if (firstStrategy instanceof InteractiveUIStrategy) {
					((InteractiveUIStrategy) firstStrategy)
							.setUIMove(strategyMove);
				}
				if (secondStrategy instanceof InteractiveUIStrategy) {
					((InteractiveUIStrategy) secondStrategy)
							.setUIMove(strategyMove);
				}
				gameEngine.play();
			}
		};
		Thread gameThread = new Thread(runnableForThread);
		gameThread.start();
		
	}
}
