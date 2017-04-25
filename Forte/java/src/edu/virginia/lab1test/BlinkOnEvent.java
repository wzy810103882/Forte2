package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class BlinkOnEvent extends Event {

    static final String BlinkOnEvent = "BlinkOn";

    public BlinkOnEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
