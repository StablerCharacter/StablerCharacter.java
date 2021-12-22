package ga.susite.StablerCharacter;

import ga.susite.StablerCharacter.events.Event;
import ga.susite.StablerCharacter.events.Events;

public class Dialog {
	/**
	 * The content of the dialog.
	 */
	public String message = "";
	/**
	 * All of the actions that is going to be triggered in the dialog.
	 */
	public Events events;
	
	public Dialog(String nMessage) {
		message = nMessage;
	}
	
	public Dialog(String nMessage, Event... nEvent) {
		message = nMessage;
		events = new Events(nEvent);
	}
}
