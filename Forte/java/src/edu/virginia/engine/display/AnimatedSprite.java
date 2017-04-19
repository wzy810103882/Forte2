package edu.virginia.engine.display;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AnimatedSprite extends Sprite {

	private List<Frame> frames = new ArrayList<>();
	private int frameCount = 0;
	private int startInd;
	private int endInd;
	private int currInd;
	private int delay;
	private int numFrames; // Number of frames the animation contains
	private int direction = 1; // 1 = left-to-right, -1 = right-to-left
	private boolean playing;
	
	public AnimatedSprite(String id) {
		super(id);
	}
	
	public AnimatedSprite(String id, String imageFileName) {
		super(id, imageFileName);
	}
	
	/**
	 * Constructor for an animated sprite
	 * @param id - string id
	 * @param frs - array of frames to be animated
	 * @param duration - how long each frame lasts
	 * @param start - starting index of animation
	 * @param end - last index of animation
	 */
	public AnimatedSprite(String id, BufferedImage[] frs, int duration, int start, int end) {
		super(id);
		if (frs != null) this.setImage(frs[0]);
		startInd = start;
		endInd = end;
		currInd = start;
		playing = false;
		delay = duration;
		
		if (frs != null) {
			for (BufferedImage f : frs) {
				frames.add(new Frame(f, duration));
			}
		}
		
		numFrames = frames.size();
	}

	public int getStartInd() {
		return startInd;
	}

	public void setStartInd(int startInd) {
		this.startInd = startInd;
	}

	public int getEndInd() {
		return endInd;
	}

	public void setEndInd(int endInd) {
		this.endInd = endInd;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void setNumFrames(int duration) {
		this.numFrames = duration;
	}
	
	public int getNumFrames() {
		return numFrames;
	}

	public int getCurrInd() {
		return currInd;
	}

	public void setCurrInd(int currInd) {
		this.currInd = currInd;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void animate() {
		if (frames != null)	playing = true;
	}
	
	public void stop() {
		if (frames != null) playing = false;
	}
	
	public void restart() {
		if (frames != null)	{
			playing = true;
			currInd = startInd;
		}
	}
	
	public void reset() {
		playing = false;
		frameCount = 0;
		currInd = 0;
	}
	
	public void update() {
		if (playing) {
			frameCount++;
			
			if (frameCount > delay) {
				frameCount = 0;
				currInd += direction;
				// Wrap around animation when array index out of bounds
				if (currInd > endInd) currInd = startInd; // Forward
				else if (currInd < startInd) currInd = endInd; // Backward
				if (!frames.isEmpty()) setImage(frames.get(currInd).getFrame());
			}
		}
	}
}
