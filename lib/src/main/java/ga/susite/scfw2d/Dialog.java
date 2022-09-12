package ga.susite.scfw2d;

import ga.susite.scfw2d.events.Event;
import ga.susite.scfw2d.events.Events;

import lombok.Getter;
import lombok.Setter;

/**
 * The dialog class. Used for displaying dialogs.
 */
public class Dialog {
	/**
	 * The content of the dialog.
	 */
	@Getter @Setter String message = "";
	/**
	 * All of the actions that is going to be triggered in the dialog.
	 */
	@Getter @Setter Events events;
	
	/**
	 * Initialize a new dialog with the specified text.
	 * @param nMessage The text the dialog is going to show.
	 */
	public Dialog(String nMessage) {
		message = nMessage;
	}
	
	/**
	 * Initialize a new dialog with the specified text and the provided event(s).
	 * @param nMessage The text the dialog is going to show.
	 * @param nEvents The event(s) that will be triggered when the dialog is being shown.
	 */
	public Dialog(String nMessage, Event... nEvents) {
		message = nMessage;
		events = new Events(nEvents);
	}

	public boolean equals(Object other) {
		if(this == other) return true;
		if(other == null || getClass() != other.getClass()) return false;
		Dialog dialog = (Dialog)other;
		if(!message.equals(dialog.message)) return false;
		return events.equals(dialog.events);
	}
}
