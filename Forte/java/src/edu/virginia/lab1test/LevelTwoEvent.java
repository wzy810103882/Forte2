package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class LevelTwoEvent extends Event {

    static final String LevelTwoEvent = "LevelTwo";

    public LevelTwoEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
