package edu.virginia.lab1test;
import edu.virginia.engine.display.Game;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.util.GameClock;
import edu.virginia.lab1test.CoinPickedUp;
import edu.virginia.engine.display.Sprite;
import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventListener;
import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.util.MusicPlayer;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


import java.util.ArrayList;

public class QuestManager implements IEventListener {
	private Sprite C;
	private Sprite D;
	private Sprite E;
	private Sprite F;
	private boolean Cismute = true;
	private boolean Dismute = true;
	private boolean Eismute = true;
	private boolean Fismute=true;
	private GameClock clock;
	private MusicPlayer musicPlayer;
	private ArrayList<Sprite> list = new ArrayList<Sprite>();
	public QuestManager(Sprite C, Sprite D, Sprite E, Sprite F, MusicPlayer musicPlayer, GameClock clock){
		this.C = C;
		this.D = D;
		this.E = E;
		this.F = F;
		this.musicPlayer = musicPlayer;
		this.clock = clock;
		list.add(C);
		list.add(D);
		list.add(E);
		list.add(F);
	}


	public boolean isCismute() {
		return Cismute;
	}

	public boolean isDismute() {
		return Dismute;
	}

	public boolean isEismute() {
		return Eismute;
	}

	public boolean isFismute() {
		return Fismute;
	}

	public void nextPosition(Sprite temp){
		if (temp.getCurrentIndex() < temp.getxArray().length){
			temp.setPosition(temp.getxArray()[temp.getCurrentIndex()],temp.getyArray()[temp.getCurrentIndex()]);
			temp.setVisible(true);
			temp.setCurrentIndex(temp.getCurrentIndex()+1);
		}
	}

	@Override
	public void handleEvent(Event event) {
		DisplayObject source = (DisplayObject) event.getSource();
		if (source == C) {
			if (C.isVisible()) {
				C.setVisible(false);
				if (clock.getElapsedTime() % 2000 > C.getStart() && clock.getElapsedTime() % 2000 <C.getFinish()) {
					//musicPlayer.playSong("piano_c5.wav", -20);
					Cismute = false;
				}
				else {
					musicPlayer.playSong("Error.wav",-20);

				}
				nextPosition(C);
			}
		}
		else if(source == D){
			if (D.isVisible()) {
				D.setVisible(false);
				//musicPlayer.playSong("Am6.wav",-20);
				if (clock.getElapsedTime() % 2000 > D.getStart() && clock.getElapsedTime() % 2000 <D.getFinish()) {

				//	musicPlayer.playSong("piano_g4.wav", -20);
					Dismute = false;
				}
				else {
					musicPlayer.playSong("Error.wav",-20);

				}
				nextPosition(D);

			}
		}
		else if(source == E){
			if (E.isVisible()) {
				E.setVisible(false);
				//  musicPlayer.playSong("Chord_four.wav",-20);
				if (clock.getElapsedTime() % 2000 > E.getStart() && clock.getElapsedTime() % 2000 <E.getFinish()) {
					//musicPlayer.playSong("piano_a4.wav", -20);
					Eismute = false;
				}
				else {
					musicPlayer.playSong("Error.wav",-20);
				}
				nextPosition(E);

			}
		}
		else if(source == F){
			if (F.isVisible()) {
				F.setVisible(false);
				// musicPlayer.playSong("Ab9.wav",-20);
				if (clock.getElapsedTime() % 2000 > F.getStart() && clock.getElapsedTime() % 2000 <F.getFinish()) {
					//musicPlayer.playSong("piano_d4.wav", -20);
					Fismute = false;
				}
				else {
					musicPlayer.playSong("Error.wav",-20);

				}
				nextPosition(F);

			}
		}
		else {
			;
		}
	}
}
