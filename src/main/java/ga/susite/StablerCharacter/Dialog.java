package ga.susite.StablerCharacter;

public class Dialog {
	public String message = "";
	public Event event = null;
	
	Dialog() {}
	
	public Dialog(String nMessage) {
		message = nMessage;
	}
	
	Dialog(String nMessage, Event nEvent) {
		message = nMessage;
		event = nEvent;
	}
}
