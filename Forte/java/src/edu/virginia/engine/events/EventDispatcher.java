package edu.virginia.engine.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventDispatcher implements IEventDispatcher {
	Map<String, ArrayList<IEventListener>> listeners = new HashMap<String, ArrayList<IEventListener>>();
	
	@Override
	public void addEventListener(IEventListener listener, String eventType) {
		if(!listeners.containsKey(eventType)) {
			listeners.put(eventType, new ArrayList<IEventListener>());
			listeners.get(eventType).add(listener);
		}
		else {
			listeners.get(eventType).add(listener);
		}
	}
	@Override
	public void removeEventListener(IEventListener listener, String eventType) {
		if (listeners.containsKey(eventType)) {
			if (listeners.containsValue(listener)) {
				listeners.get(eventType).remove(listener);
			}
		}
	}
	@Override
	public void dispatchEvent(Event event) {
		String s = event.getEventType();
		if(listeners.containsKey(s)) {
			for(IEventListener lis : listeners.get(s)) {
				if (listeners.get(s) != null)
					lis.handleEvent(event);
			}
		}
	}
	@Override
	public boolean hasEventListener(IEventListener listener, String eventType) {
		if (listeners.containsKey(eventType)) {
			if (listeners.get(eventType).contains(listener))
				return true;
		}
		return false;
	}
}
