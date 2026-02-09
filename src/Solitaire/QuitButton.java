package Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The class which contains methods and data for the quit button
 */
public class QuitButton extends JButton
{
    /**
     * Sets the location for the quit button
     */
    private static final Point QUIT_BTN = new Point(Klondike.WIDTH / 2, 300); /** constant used for the quit button location */

    public QuitButton(Klondike klondike) {
	super("Quit");
	this.setBounds(0, 0, 40, 40);
	this.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e) {

		System.exit(1);

	    }
	});

	this.setSize(200, 200);
	this.setLocation(QUIT_BTN);
    }
}
