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
}
