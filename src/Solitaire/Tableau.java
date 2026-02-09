package Solitaire;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class Tableau extends Pile
{

    //creates the tableau and adds the cards to it, also shows the top card if there are any cards in the tableau
    public Tableau(GamePanel gamePanel, int x, int y, int cards) {
	super(x, y);
	setSize(80, 450);
	setOpaque(false);
	for (int i = 0; i < cards; i++) {
	    push(gamePanel.getDeck().remove());
	}
	if (cards > 0) {
	    topCard().showFace();
	}
    }

    //base of the tableau, is drawn under the cards in the tableau
    @Override
    protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.BLACK);
	g2d.drawLine(0, 0, this.getWidth(), 0);
	g2d.drawLine(0, 0, 0, 100);
	g2d.drawLine(this.getWidth() - 1, 0, this.getWidth() - 1, 100);
	g2d.setPaint(new GradientPaint(36, 0, new Color(255, 255, 255, 160), 36, 60, new Color(0, 0, 0, 0)));
	g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

	//draws the cards that are placed in the tableau
	int CardPosY = 0;
	if (!this.isEmpty()) {
	    for (Card c : this.cards) {
		if (c.isFaceUp()) {
		    g.drawImage(c.getCardImage(), 0, CardPosY, 80, 100, this);
		} else {
		    g.drawImage(Card.getBackOfCard(), 0, CardPosY, 80, 100, this);
		}
		CardPosY += 20; //goes up 20 pixels for the next card
	    }

	}
    }

    //moves a card from waste to tableau
    public void moveFromWaste(Waste source, Card card) {
	if (this.accepts(card)) {
	    this.push(source.remove());
	}
    }

    //accepts if the card is one value lower and if they do not have the same colour
    public boolean accepts(Card card) {
	if (!this.isEmpty()) {
	    return this.topCard().getCardNumber() == card.getCardNumber() + 1 &&
		   !this.topCard().getCardColour().equals(card.getCardColour()); 
	}
	return card.getCardNumber() == 13; //if the tableau is empty only a king can be placed there
    }

    //Method to get the card that is pressed
    public Card getPressedCard(int y) {
	int stackedCards =
		y / 20; //stackedCards
	if (stackedCards < this.cards.toArray().length) {
	    Card theCard = (Card) cards.toArray()[stackedCards];
	    if (theCard.isFaceUp()) {
		return theCard;
	    }
	}

	if (stackedCards == 0){
	    return null;
	}
	
	else {
	    return (Card) cards.toArray()[cards.toArray().length - 1];
	}
    }

    //method to move cards that are going to foundation
    public boolean moveTo(Foundation too, Card card) {
	if (too.accepts(card)) {
	    too.push(this.remove());
	    if (!this.isEmpty()) {
		this.topCard().showFace();
	    }
	    return true;
	}
	return false;
    }

    //method for moving cards to another tableau
    public void moveTo(Tableau destination, Card card) {
	if (!this.isEmpty() || card.getCardNumber() == 13) {
	    if (destination.accepts(card)) {
		Deque<Card> toBeMoved = new ArrayDeque<>(); //new list for the cards that are going to be moved
		while (!this.isEmpty()) {
		    Card temporary = this.remove();
		    toBeMoved.push(temporary); 
		    if (temporary.equals(card)) { 
			break;
		    }
		}
		while (!toBeMoved.isEmpty()) {
		    destination.push(toBeMoved.pop()); //if toBeMoved has elements in it, they are pushed to the destination
		}
	    }
	}
	if (!this.isEmpty()) {
	    this.topCard().showFace();
	}
    }
}

