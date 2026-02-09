package Solitaire;


import javax.swing.*;
import java.util.EmptyStackException;
import java.util.Stack;

public abstract class Pile extends JPanel
{
    protected Stack<Card> cards;

    
    protected Pile(int x, int y) {
	setLocation(x, y);
	cards = new Stack<>();
    }

    /**removes and returns the top card of the pile */
    public Card remove() {
	try {
	    return cards.pop();
	} catch (EmptyStackException ESE) {
	    return null;
	}
    }

    /**returns the top card of the pile without removing it */
    public Card topCard() {
	if (!this.cards.isEmpty()) {
	    return this.cards.peek();
	}
	return null;
    }

    public void push(Card theCard) {
	this.cards.push(theCard);
    }

    public boolean isEmpty() {
	return this.cards.isEmpty();
    }
}
