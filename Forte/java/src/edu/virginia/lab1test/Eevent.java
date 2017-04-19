package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class Eevent extends Event {

    static final String Eevent = "E";

    public Eevent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
