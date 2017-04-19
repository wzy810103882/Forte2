package edu.virginia.engine.display;

import java.util.ArrayList;
import java.util.List;

public class Tween {
	
	DisplayObject obj;
	TweenTransitions trans;
	List<TweenParam> animList = new ArrayList<TweenParam>();
	private int front = 0;
	private boolean animDone = true; 
	
	public Tween(DisplayObject object) {
		obj = object;
	}
	
	public Tween(DisplayObject object, TweenTransitions transition) {
		obj = object;
		trans = transition;
	}
	
	public void animate(TweenableParams fieldToAnimate, double startVal, double endVal, double time) {
		TweenParam p = new TweenParam(fieldToAnimate, startVal, endVal, time);
		animList.add(p);
		animDone = false;
	}
	
	//invoked once per frame by the TweenJuggler. Updates this tween / DisplayObject
	public void update() { 
		if(!animList.isEmpty()) {
			TweenParam p = animList.get(front);
			
		}
	}
	
	public boolean isComplete() {
		return animDone;
	}
	
	public void setValue(TweenableParams param, double value) {
		
	}

}
