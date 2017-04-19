package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class Fevent extends Event {

    static final String Fevent = "F";

    public Fevent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
