package ga.susite.StablerCharacter;

import ga.susite.StablerCharacter.events.Event;

public class Dialog {
	/**
	 * The content of the dialog.
	 */
	public String message = "";
	/**
	 * All of the actions that is going to be triggered in the dialog.
	 */
	public Event[] event;
	
	public Dialog(String nMessage) {
		message = nMessage;
	}
	
	Dialog(String nMessage, Event... nEvent) {
		message = nMessage;
		event = nEvent;
	}
}
