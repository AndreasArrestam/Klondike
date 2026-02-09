package Solitaire;

import java.awt.*;

public class Waste extends Pile
{
    //set the size of the waste pile
    public Waste(int x, int y) {
	super(x, y);
	setSize(80, 100);
    }

    //draws the waste pile, which starts by showing the base of the waste pile
    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	if (this.isEmpty()) {
	    g.drawImage(Card.getCardOutline(), 0, 0, this.getWidth(), this.getHeight(), this);
	} else {
	    g.drawImage(this.topCard().getCardImage(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

    }
}
