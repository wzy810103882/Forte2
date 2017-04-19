package edu.virginia.lab1test;

import javax.swing.JOptionPane;

import edu.virginia.engine.events.Event;

public class CoinPickedUp extends Event {
	public static String message = "Mario picked up the coin! Quest complete.";
	private boolean completed = false;
	
	public CoinPickedUp(String eventType) {
		super(eventType);
	}

	public void showMessage() {
//		JOptionPane.showMessageDialog(null, message);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	

}
