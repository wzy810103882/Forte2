package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class LevelThreeEvent extends Event {

    static final String LevelThreeEvent = "LevelThree";

    public LevelThreeEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
