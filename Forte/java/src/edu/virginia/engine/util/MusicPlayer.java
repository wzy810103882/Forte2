package edu.virginia.engine.util;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	
	File file;
	Clip clip;
	float volume;
	FloatControl gainControl;
	
	public MusicPlayer() {	}
	//"java" + File.separator +
	public void playSong(String filename, float volume) {
		file = new File( "resources" + File.separator + filename);
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
            clip.open(audio);
            this.volume = volume;
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    		gainControl.setValue(volume);
            clip.start();
        }
        catch(UnsupportedAudioFileException uae) {
            System.out.println(uae);
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
		catch(LineUnavailableException lua) {
            System.out.println(lua);
        }
	}
	
	public void mute() {
		if (clip != null) {
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(gainControl.getMinimum());
		}
	}

	public void unmute() {
		if (clip != null) {
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(volume);
		}
	}
	
	public float getVolume() {
		return volume;
	}
	
	
}
