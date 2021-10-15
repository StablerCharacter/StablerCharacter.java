package ga.susite.StablerCharacter;

import ga.susite.StablerCharacter.events.Event;

public class Dialog {
	public String message = "";
	public Event event;
	
	Dialog() {}
	
	public Dialog(String nMessage) {
		message = nMessage;
	}
	
	Dialog(String nMessage, Event nEvent) {
		message = nMessage;
		event = nEvent;
	}
}
