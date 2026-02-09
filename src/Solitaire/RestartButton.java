package Solitaire;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Restartbutton will serve as our way to restart the game without having to close it
 * each time and the class will paint it on the gamepanel by reflecting its position on
 * the board.
 */
public class RestartButton extends JButton
{
    /**
     * Indicates the position of the restartbutton in the game and serves as a constant.
     */
    public static final Point RESTART_POS = new Point(20, 700); //constant used for restart button location

    /** Creates functionality for the restart button.
     *
     * @param gamePanel contains the current layout of the game which will then serve as the
     * foundation for the restart buttons configuration
     */
    public RestartButton(GamePanel gamePanel) {
	super("Restart");
	this.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(ActionEvent e) {
		int choice = JOptionPane.showConfirmDialog(gamePanel, "Are you sure you want to restart?", "Restart Confirmation",
							   JOptionPane.YES_NO_OPTION);
		if (choice == JOptionPane.YES_OPTION) {
		    gamePanel.restart();
		}
	    }
	});

	this.setSize(90, 20);
	this.setLocation(RESTART_POS);
    }



}
