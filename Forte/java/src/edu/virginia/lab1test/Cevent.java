package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class Cevent extends Event {

    static final String Cevent = "C";

    public Cevent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
