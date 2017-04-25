package edu.virginia.lab1test;


import edu.virginia.engine.events.Event;
import edu.virginia.engine.events.IEventDispatcher;

public class BossKillsPlayerEvent extends Event {

    static final String BossKillsPlayerEvent = "bosskillsplayer";

    public BossKillsPlayerEvent(String eventType, IEventDispatcher source) {
        super(eventType, source);
    }

}
