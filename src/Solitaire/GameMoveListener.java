package Solitaire;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The file which takes care of mouse movements and inputs.
 */

public class GameMoveListener extends MouseInputAdapter
{
    private Waste waste = null;
    private Card selCard = null;
    private Tableau selTab = null;
    private Foundation selFound = null;
    private GamePanel gamePanel;
    private boolean moveInProgress;
    private boolean cardMoved;

    public GameMoveListener(GamePanel gamePanel) {

	this.gamePanel = gamePanel;
    }

    /* Reads when the mouse button is pressed */
    @Override public void mousePressed(final MouseEvent mouseEvent) {
	Deck deck = gamePanel.getDeck();
	moveInProgress = true;
	Component pressedComponent = mouseEvent.getComponent().getComponentAt(mouseEvent.getPoint());

	if (pressedComponent instanceof Foundation) {
	    moveInProgress = false;
	} 
	
	else if (pressedComponent instanceof Tableau) {
	    selTab = (Tableau) pressedComponent;
	    selCard = selTab.getPressedCard(mouseEvent.getY() - 150);
	    for (Foundation foundation : gamePanel.getFoundation()) {
		
			if (selTab.moveTo(foundation, selCard)) {
				cardMoved = true;
				moveInProgress = false;
				break;
			}
	    }
	} 
	
	else if (pressedComponent instanceof Deck) {
	    moveInProgress = false;
	    Waste waste = gamePanel.getWastePile();
	    if (deck.isEmpty()) {
			if (!waste.isEmpty()) {
				while (!waste.isEmpty()) {
				deck.push(waste.remove());
				}
			}
	    } 
		
		else {
		waste.push(deck.remove());
		waste.topCard().showFace();
		cardMoved = true;
	    }

	} 
	
	else if (pressedComponent instanceof Waste) {
	    waste = gamePanel.getWastePile();
	    selCard = waste.topCard();
	    if (selCard != null) {
			for (Foundation foundation : gamePanel.getFoundation()) {
				if (foundation.moveFromWaste(waste, selCard)) {
				cardMoved = true;
				moveInProgress = false;
				}
			}
	    }
	}
	mouseEvent.getComponent().repaint();
	playSound(cardMoved);
	cardMoved = false;
    }


    //reads when the mouse button is released
    @Override public void mouseReleased(final MouseEvent mouseEvent) {
	if (moveInProgress && selCard != null) {
	    Component relCard = mouseEvent.getComponent().getComponentAt(mouseEvent.getPoint());
	    if (relCard instanceof Tableau) {
			if (waste != null) {
				Tableau dest = (Tableau) relCard;

				if (!waste.isEmpty()) {
				dest.moveFromWaste(waste, selCard);
				cardMoved = true;
				}
				waste.repaint();
			} 
			
			else if (selTab != null) {//to move cards within the tableau
				Tableau source = selTab;
				Tableau destination = (Tableau) relCard;
				source.moveTo(destination, selCard);
				
				if(!source.equals(destination)) {
				cardMoved = true;
				}
				source.repaint();
			} 
			
			else if (selFound != null) {
				Foundation source = selFound;
				Tableau dest = (Tableau) relCard;
				source.moveTo(dest, selCard);
				cardMoved = true;
				source.repaint();
				dest.repaint();
			}
	    }
	}
	playSound(cardMoved);
	mouseEvent.getComponent().repaint();
	cardMoved = false;
	selCard = null;
	selFound = null;
	selTab = null;
	waste = null;
	gamePanel.hasWon();
    }

    private void playSound(boolean cardMoved){
	if (cardMoved) {
	    Sound.playMovedcardSound();
	}
    }

}

