package ga.susite.scfw2d.utils;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * This is a Utility class for a simple events system.
 * @author lines-of-codes
 * @param <EventArgsType> The type of the EventArgs that will be provided to the subscribers method.
 */
public class MethodEvent<EventArgsType> {
	ArrayList<Function<EventArgs<EventArgsType>, Void>> subscribers = new ArrayList<Function<EventArgs<EventArgsType>, Void>>();
	
	/**
	 * Invoke all of the subscribers.
	 * @param eventArgs The event argument to provide for the subscribers.
	 */
	public void invoke(EventArgs<EventArgsType> eventArgs) {
		for(Function<EventArgs<EventArgsType>, Void> subscriber : subscribers) {
			subscriber.apply(eventArgs);
		}
	}
	
	/**
	 * Add a subscriber.
	 * @param subscriber The subscriber to add.
	 */
	public void addEventListener(Function<EventArgs<EventArgsType>, Void> subscriber) {
		subscribers.add(subscriber);
	}
	
	/**
	 * Add multiple subscribers.
	 * @param subscribers The subscribers to add.
	 */
	@SuppressWarnings("unchecked")
	public void addEventListeners(Function<EventArgs<EventArgsType>, Void>... subscribers) {
		for(Function<EventArgs<EventArgsType>, Void> subscriber : subscribers) {
			addEventListener(subscriber);
		}
	}
}
