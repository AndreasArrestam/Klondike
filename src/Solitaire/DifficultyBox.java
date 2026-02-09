package Solitaire;

import javax.swing.*;
/**
 * The class which contains the information for the combobox
 */
public class DifficultyBox extends JComboBox<String>
{
    private static final String[] Difficulties = { "EASY", "HARD" };

    public DifficultyBox(Menu menu) {
		/**
		 * A combobox which allows the user to choose between the two difficulties, easy and hard.
		 *  This is used in the menu and is not visible during the game.
		 */
		super(Difficulties);
		final int width = 50;
		final int height = 50;
		final int yPos = 400;
		final int xPos = 300;
		this.setBounds(xPos, yPos, width, height);

    }
}
