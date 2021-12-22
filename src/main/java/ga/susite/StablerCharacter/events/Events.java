/**
 * 
 */
package ga.susite.StablerCharacter.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lines-of-codes
 * Similar to the MethodEvent class. But this is for storing dialog events.
 */
public class Events {
	List<Event> events;
	
	public Events() {
		events = new ArrayList<Event>();
	}
	
	public Events(Event... events) {
		this.events = Arrays.asList(events);
	}
	
	public void invoke() {
		for(Event event : events) {
			event.onActionTriggered();
		}
	}
}
