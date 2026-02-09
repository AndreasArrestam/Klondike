package Solitaire;

import javax.swing.*;
import java.awt.*;

/**
 * This class handles the GUI for the menu and it's functionality
 */
public class Menu extends JPanel
{
    private Klondike klondike;
    private DifficultyBox difficultyBox;

    /**
     * Constructor which adds all the needed buttons
     */
    public Menu(Klondike klondike) {
	super();
	this.klondike = klondike;
	addLabel();
	addPlayButton();
	addQuitButton();
	addDifficultyButton();

    }

    /**
     * Paints the blue background
     */
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(Color.blue);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());

    }

    /**
     * Methods for adding the buttons
     */
    private void addPlayButton() {
	PlayButton playButton = new PlayButton(this);
	add(playButton);
    }

    private void addQuitButton() {
	QuitButton quitButton = new QuitButton(klondike);
	add(quitButton);
    }

    private void addDifficultyButton() {
	JLabel label = new JLabel("Select difficulty");
	difficultyBox = new DifficultyBox(this);
	add(label);
	add(difficultyBox);

    }

    private void addLabel() {
	JLabel label = new JLabel("Klondike");
	Font font0 = new Font("arial", Font.BOLD, 150);
	label.setFont(font0);
	add(label);
    }

    public void startGame() {
	//starts the game and removes the menu from the screen, this is called from the menu when the user clicks start
	Difficulty difficulty = Difficulty.valueOf((String) difficultyBox.getSelectedItem());
	klondike.startGame(difficulty);
    }


}
