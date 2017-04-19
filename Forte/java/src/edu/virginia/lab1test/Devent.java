package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class Devent extends Event {

    static final String Devent = "D";

    public Devent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
