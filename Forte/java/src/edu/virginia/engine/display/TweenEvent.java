package edu.virginia.engine.display;

import edu.virginia.engine.events.Event;

public class TweenEvent extends Event {

	Tween tween;
	
	public TweenEvent(String eventType, Tween tween) {
		super(eventType);
	}
	
	public Tween getTween() {
		return tween;
	}
	
}
