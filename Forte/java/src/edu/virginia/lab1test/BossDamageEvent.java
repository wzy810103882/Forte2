package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class BossDamageEvent extends Event {

    static final String BossDamageEvent = "bossDamage";

    public BossDamageEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
