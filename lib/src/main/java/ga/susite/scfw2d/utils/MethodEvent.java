package ga.susite.scfw2d.utils;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * This is a Utility class for a simple events system.
 * @author lines-of-codes
 * @param <T> The type of the EventArgs that will be provided to the subscribers method.
 */
public class MethodEvent<T> {
	ArrayList<Function<EventArgs<T>, Void>> subscribers = new ArrayList<>();
	
	/**
	 * Invoke all of the subscribers.
	 * @param eventArgs The event argument to provide for the subscribers.
	 */
	public void invoke(EventArgs<T> eventArgs) {
		for(Function<EventArgs<T>, Void> subscriber : subscribers) {
			subscriber.apply(eventArgs);
		}
	}
	
	/**
	 * Add a subscriber.
	 * @param subscriber The subscriber to add.
	 */
	public void addEventListener(Function<EventArgs<T>, Void> subscriber) {
		subscribers.add(subscriber);
	}
	
	/**
	 * Add multiple subscribers.
	 * @param subscribers The subscribers to add.
	 */
	@SuppressWarnings("unchecked")
	public void addEventListeners(Function<EventArgs<T>, Void>... subscribers) {
		for(Function<EventArgs<T>, Void> subscriber : subscribers) {
			addEventListener(subscriber);
		}
	}
}
