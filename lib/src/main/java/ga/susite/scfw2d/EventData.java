package ga.susite.scfw2d;

import lombok.Getter;
import lombok.Setter;

/**
 * A simple data class for storing informations about current dialog.
 * Used in Events. (ga.susite.StablerCharacter.utils.Event)
 */
public class EventData {
	/**
	 * The information about the current dialog.
	 */
	@Getter @Setter Dialog currentDialog;
	/**
	 * The information about the current scene.
	 */
	@Getter @Setter SceneInfo currentScene;
	
	/**
	 * Initialize a new event data class for the events.
	 * @param currentDialog The information about the current dialog.
	 * @param currentScene The information about the current scene.
	 */
	public EventData(Dialog currentDialog, SceneInfo currentScene) {
		this.currentDialog = currentDialog;
		this.currentScene = currentScene;
	}
}
