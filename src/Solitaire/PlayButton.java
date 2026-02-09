package Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * class for the play button
 */
public class PlayButton extends JButton
{

    /**
     * sets the location for the playbutton and serves as a constant for the playbutton location
     */
    private static final Point PLAY_BTN = new Point(Klondike.WIDTH / 2, 0); //used as a constant

    public PlayButton(Menu menu) {
	super("Play");
	this.setBounds(0, 0, 40, 40);
	this.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		menu.startGame();

	    }
	});

	this.setSize(200, 200);
	this.setLocation(PLAY_BTN);
    }

}
