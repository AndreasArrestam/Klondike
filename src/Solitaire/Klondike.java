package Solitaire;
import javax.management.RuntimeErrorException;
import javax.swing.*;
import java.awt.*;

/*Class which contains the main method and serves as the outline for the game, 
it also contains the method for starting the game and switching from menu to game screen. */

public class Klondike extends JFrame
{
    protected GamePanel gamePanel = null;
	/**
	 * serves as the outline measurements of the board and will not change
	 */
    private final static Point WINDOW_POS = new Point(400, 0); //Constant used for placing the gamescreen at a conveniant place
	/**
	 * These measurements are used across different files for the size of the window.
	 */
    public static final int WIDTH = 700, HEIGHT = 750;
    final private Menu menu;

    public Klondike() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLocation(WINDOW_POS);
	menu = new Menu(this);
	menu.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	add(menu);
	pack();

    }
	//starts the game and removes the menu from the screen, this is called from the menu when the user clicks start
    public void startGame(Difficulty diff) {
	gamePanel = new GamePanel(diff);
	gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	remove(menu);
	add(gamePanel);
	pack();

    }

    public static void main(String[] args) {
	try {
	    new Klondike().setVisible(true);
	    Sound.playBackgroundSound();
	} catch (RuntimeErrorException runtimeError) {
	    System.err.println("Game did not run correctly " + runtimeError.getMessage());
	    runtimeError.printStackTrace();
	}

    }

}
