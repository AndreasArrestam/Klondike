package Solitaire;

import javax.swing.*;
import java.awt.*;

/**
 *Creates the gamefield
 */
public class GamePanel extends JPanel
{
    final protected static int X_OFFSET = 80;
    private Tableau[] tableau;
    private Deck deck;
    private Waste wastePile;
    private Foundation[] foundation;
    private Difficulty difficulty;

    /**
     * Adds all needed elements for the gamepanel.
     */
    public GamePanel(Difficulty diff) {
	this.difficulty = diff;
	setLayout(null);
	initializePiles();
	addRestartButton();
	GameMoveListener read = new GameMoveListener(this);
	addMouseListener(read);
	addMouseMotionListener(read);

    }

    /**
     * Initializes the positioning for the foundation, deck, wastePile and for the tableau
     */
    private void initializePiles() {
	int tableauAmount = 7;
	int foundationAmount = 4;
	int tableauOffset = 80;
	final Point DECK_POS = new Point(550, 20); //constant used for position of the deck
	final Point TABLEAU_POS = new Point(20, 150); //constant used for position of the tableau

	foundation = new Foundation[foundationAmount];
	deck = new Deck(DECK_POS.x, DECK_POS.y);
	wastePile = new Waste(DECK_POS.x - X_OFFSET, DECK_POS.y);
	tableau = new Tableau[tableauAmount];

	add(deck);
	add(wastePile);

	for (int f = 0; f < foundationAmount; f++) {
	    foundation[f] = new Foundation(20 + X_OFFSET * f, 20, f + 1);
	    add(foundation[f]);
	}

	if (difficulty == Difficulty.EASY) {

	    for (int tableauIndex = 0; tableauIndex < 4; tableauIndex++) {
			tableau[tableauIndex] = new Tableau(this, TABLEAU_POS.x + tableauOffset * tableauIndex, TABLEAU_POS.y, tableauIndex + 1);
			add(tableau[tableauIndex]);
	    }
	} 
	
	else if (difficulty == Difficulty.HARD) {
	    for (int tableauIndex = 0; tableauIndex < tableauAmount; tableauIndex++) {
			tableau[tableauIndex] = new Tableau(this, TABLEAU_POS.x + tableauOffset * tableauIndex, TABLEAU_POS.y, tableauIndex + 1);
			add(tableau[tableauIndex]);
	    }
	}
	}


    //method to restart the game, removes all components and initializes them again
    protected void restart() {
	remove(deck);
	remove(wastePile);

	for (Foundation f : foundation) {
	    if (f != null) {
			remove(f);
	    }
	}

	for (Tableau t : tableau) {
	    if(t!=null) {
			remove(t);
	    }
	}
	initializePiles();
	this.repaint();
    }

    public Foundation[] getFoundation() {
	return foundation;
    }

    public Waste getWastePile() {
	return wastePile;
    }

    public Deck getDeck() {
	return deck;
    }

    /**
     * Creates the background for the visual representation
     */

    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(Color.green);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Checks for victory if all piles are empty
     */
    protected void hasWon() {
	if (deck.isEmpty() && wastePile.isEmpty() && hasEmptyTableaus()) {
	    Sound.playWinSound();
	    int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Congratulations!", JOptionPane.YES_NO_OPTION);
	    
		if (choice == JOptionPane.YES_OPTION) {
		restart();
	    } 
		
		else {
		System.exit(0);
	    }
	}
    }

    /**
     * Checks if all piles in the Tableau is empty
     */
    private boolean hasEmptyTableaus() {
	for (Tableau t : tableau) {
	    if (t != null){
			if (!t.isEmpty()) {
			return false;
	    	}
		}
	}
	return true;
    }

    /**
     * Adds the restart button
     */
    private void addRestartButton() {
	RestartButton restartButton = new RestartButton(this);
	add(restartButton);
    }

}
