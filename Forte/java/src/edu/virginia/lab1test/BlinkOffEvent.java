package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class BlinkOffEvent extends Event {

    static final String BlinkOffEvent = "BlinkOff";

    public BlinkOffEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
