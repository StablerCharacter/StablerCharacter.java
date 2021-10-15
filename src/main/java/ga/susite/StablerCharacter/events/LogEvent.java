package ga.susite.StablerCharacter.events;

import tech.fastj.engine.FastJEngine;

/**
 * Logs a message when a dialog is shown.
 */
public class LogEvent implements Event {
	String message;
	
	public LogEvent(String newMessage) {
		message = newMessage;
	}

	@Override
	public void onActionTriggered() {
		FastJEngine.log(message);
	}
}
