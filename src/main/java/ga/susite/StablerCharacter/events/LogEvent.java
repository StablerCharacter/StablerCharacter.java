package ga.susite.StablerCharacter.events;

import tech.fastj.engine.FastJEngine;

/**
 * Logs a message when a dialog is shown.
 */
public class LogEvent implements Event {
	String message;
	
	/**
	 * Creates a new LogEvent instance with the specified logging message.
	 * @param newMessage The message that is going to be logged when the dialog got triggered.
	 */
	public LogEvent(String newMessage) {
		message = newMessage;
	}

	@Override
	public void onActionTriggered() {
		FastJEngine.log(message);
	}
}
