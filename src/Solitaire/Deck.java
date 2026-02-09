package Solitaire;

import java.awt.*;
import java.util.Collections;

/**
 * Since there are a lot of different cardpiles in the game
 * we base all of the piles on the Deck clas.
 */
public class Deck extends Pile
{
    /**
     * generates the deck of cards, 1(ace) - 13 for all card types
     */
    public Deck(final int x, final int y) {
	super(x, y);
	setSize(80, 100);
	for (CardType s : CardType.values()) {
	    for (int i = 1; i <= 13; i++) {
		push(new Card(i, s));
	    }
	}
	Collections.shuffle(cards); //shuffles the deck using java's own method "shuffle"
    }

    /**
     * draws the deck, which starts by showing the back of the card
     */

    @Override
    protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	Graphics2D g2d = (Graphics2D) g;
	g2d.setStroke(new BasicStroke(5));
	g2d.setColor(Color.blue);
	g2d.drawRect(0, 0, this.getWidth(), this.getHeight());

	if (!isEmpty()) {
	    g.drawImage(Card.getBackOfCard(), 0, 0, 80, this.getHeight(), this);
	}
    }

}
