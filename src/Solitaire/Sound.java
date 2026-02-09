package Solitaire;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Sound
{
    private static boolean soundLoop; /*need to be accessed from two methods, 
	if not global all sounds will start to loop or the background
    sound will just play once */
    public static void playBackgroundSound()  {
	File file = new File("resources/audio/newBackground.wav");
	soundLoop = true;
	tryCatch(file);
    }

    public static void playMovedcardSound() {
	File file = new File("resources/audio/movedcard.wav");
	tryCatch(file);
    }

    public static void playWinSound() {
	File file = new File("resources/audio/winningsound.wav");
	tryCatch(file);
    }

    public static void tryCatch(File file){
	try {
	    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioStream);
	    clip.start();
	    if (soundLoop){
	    	clip.loop(clip.LOOP_CONTINUOUSLY);
		    soundLoop = false;
	    }
	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	    System.out.println("Trouble with sound file");
	    e.printStackTrace();
	}
    }
}

