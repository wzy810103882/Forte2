package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class BossEvent extends Event {

    static final String BossEvent = "boss";

    public BossEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
