package Solitaire;


import java.awt.*;

public class Foundation extends Pile
{

    private int cardType;


    public Foundation(int x, int y, int cardType) {
	super(x, y);
	setSize(80, 100);
	this.cardType = cardType;
    }

    //draws the foundation, which starts by showing the base of the foundation
    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	if (this.isEmpty()) {
	    g.drawImage(Card.getFoundationBase(cardType), 0, 0, this.getWidth(), this.getHeight(), this);
	}//shows the top card if the foundation is not empty
	else {
	    g.drawImage(this.topCard().getCardImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}
    }

    //to move cards from waste to foundation, takes accepts into account
    public boolean moveFromWaste(Waste w, Card card) {
	if (accepts(card)) {
	    this.push(w.remove());
	    return true;
	}
	return false;
    }

    //to move cards from foundation to tableau

    public void moveTo(Tableau to, Card card) {
	if (to.accepts(card)) {
	    to.push(this.remove());
	}
    }

    //accepts cards in foundation if it's an ace or if the card is one value higher than the top card and of the same type
    public boolean accepts(Card card) {
	if (!this.isEmpty()) {
	    if (this.topCard().getCardNumber() == card.getCardNumber() - 1 && this.topCard().getCardType().equals(card.getCardType())) {
		return true;
	    }
	}
	if (card.getCardNumber() == 1 && toCardType(card.getCardType())) {
	    return true;
	}
	return false;
    }

    //gives each card type an int value, gives the order of how the cards are placed in foundation
    public boolean toCardType(String FoundationType)
    {
	if (FoundationType.equals("d")) {
	    if (this.cardType == 1) {
		return true;
	    }
	} else if (FoundationType.equals("c")) {
	    if (this.cardType == 2) {
		return true;
	    }
	} else if (FoundationType.equals("h")) {
	    if (this.cardType == 3) {
		return true;
	    }
	} else if (FoundationType.equals("s")) {
	    if (this.cardType == 4) {
		return true;
	    }
	}
	return false;
    }
}
