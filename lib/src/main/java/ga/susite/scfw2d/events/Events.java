package ga.susite.scfw2d.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lines-of-codes
 * Similar to the MethodEvent class. But this is for storing dialog events.
 */
public class Events {
	List<Event> events;
	
	/**
	 * Initialize an empty list of events.
	 */
	public Events() {
		events = new ArrayList<Event>();
	}
	
	/**
	 * Initialize a list of events with the specified events.
	 * @param events The events to add.
	 */
	public Events(Event... events) {
		this.events = Arrays.asList(events);
	}
	
	/**
	 * Invoke the events.
	 */
	public void invoke() {
		for(Event event : events) {
			event.onActionTriggered();
		}
	}

	public boolean equals(Events other) {
		if(events.size() != other.events.size()) return false;
		for(int i = 0; i < events.size(); i++) {
			if(!events.get(i).getEventName().equals(other.events.get(i).getEventName())) return false;
		}
		return true;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("[");
		for(Event event : events) {
			sb.append(event.getEventName() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();
	}
}
