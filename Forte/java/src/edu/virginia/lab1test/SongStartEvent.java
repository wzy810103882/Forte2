package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class SongStartEvent extends Event {

    static final String SongStartEvent = "SongStartEvent";

    public SongStartEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
