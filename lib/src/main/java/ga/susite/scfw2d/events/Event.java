package ga.susite.scfw2d.events;

/**
 * The base interface for all events.
 */
public interface Event {
	/**
	 * Triggered when a dialog is reached and an event is being invoked.
	 */
	void onActionTriggered();

	/**
	 * Returns the name of the event.
	 * @return The name of the event.
	 */
	String getEventName();
}
