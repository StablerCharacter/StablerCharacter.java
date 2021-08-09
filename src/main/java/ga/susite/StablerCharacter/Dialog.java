package ga.susite.StablerCharacter;

public class Dialog {
	String message = "";
	Event event = null;
	
	Dialog() {}
	
	Dialog(String nMessage) {
		message = nMessage;
	}
	
	Dialog(String nMessage, Event nEvent) {
		message = nMessage;
		event = nEvent;
	}
}
