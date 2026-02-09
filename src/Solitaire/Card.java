package Solitaire;

import javax.swing.*;
import java.awt.*;

/**
 * card utilizes JPanel and will serve as the
 * framework for all cards when assigning to
 * different piles
 */
public class Card extends JPanel
{
    private int cardNumber;
    private static final String FOUNDATION_PILE_FILE = "fpBase0";
    private String cardType = null;
    private Image cardImage;
    private boolean isFaceUp;
    private Colour cardColour = null;
    private final static int CARD_HEIGHT = 100;
    private final static int CARD_WIDTH = 80;

    
    //Method for picking out a specific card of a certain colour, used for the deck and the foundationbase
    public Card(final int value, CardType cardType) {
	this.cardNumber = value;
	switch (cardType) {
	    case CLOVES:
		this.cardType = "c";
		cardColour = Colour.BLACK;
		break;
	    case SPADES:
		this.cardType = "s";
		cardColour = Colour.BLACK;
		break;
	    case HEARTS:
		this.cardType = "h";
		cardColour = Colour.RED;
		break;
	    case DIAMONDS:
		this.cardType = "d";
		cardColour = Colour.RED;
	}
	isFaceUp = false;
	ImageIcon imageIcon = new ImageIcon("resources/cards" + getCardFile(cardType, value));
	cardImage = imageIcon.getImage();
    }

    //method for picking out a specific card of a certain colour, used for the foundationbase
    private String getCardFile(CardType cardType, int cardNumber) {
	char character;
	if (cardNumber < 1 || cardNumber > 13) {
	    throw new IllegalArgumentException("Not a correct cardNumber");
	}

	//make a map instead of if statements
	if (cardType == CardType.CLOVES) {
	    character = 'c';
	} else if (cardType == CardType.HEARTS) {
	    character = 'h';
	} else if (cardType == CardType.DIAMONDS) {
	    character = 'd';
	} else if (cardType == CardType.SPADES) {
	    character = 's';
	} else {
	    throw new IllegalArgumentException("Not a correct cardtype");
	}
	if (cardNumber < 10) { //to get the 0 in the file name for cards 1-9
	    return ("/0" + cardNumber + character + ".gif");
	} else {
	    return ("/" + cardNumber + character + ".gif");
	}
    }

    public Image getCardImage() {
	return cardImage;
    }

    public boolean isFaceUp() {
	return isFaceUp;
    }

    public Colour getCardColour() {
	return cardColour;
    }

    //draw the outline of the card, used for when a card is selected
    public static Image getCardOutline() {
	ImageIcon imageIcon = new ImageIcon("resources/cards/card_outline.png");
	return imageIcon.getImage();
    }

    //draw the back of the card, used for when a card is face down
    public static Image getBackOfCard() {
	ImageIcon imageIcon = new ImageIcon((("resources/cards/Card_back.png")));
	return imageIcon.getImage();
    }

    //draw the base of the foundation, used for when the foundation is empty
    public static Image getFoundationBase(int suit) {
	ImageIcon imageIcon = new ImageIcon("resources/cards" + "/" + FOUNDATION_PILE_FILE + suit + ".png");
	return imageIcon.getImage();
    }

    public int getCardNumber() {
	return cardNumber;
    }

    public String getCardType() {
	return cardType;
    }

    public void showFace() {
	isFaceUp = true;
    }

    protected void paintComponent(final Graphics g) {
	g.drawImage(getCardImage(), 0, 0, CARD_WIDTH, CARD_HEIGHT, this);
	}
}
