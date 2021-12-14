package ga.susite.StablerCharacter;

/**
 * A simple data class for storing informations about current dialog.
 * Used in Events. (ga.susite.StablerCharacter.utils.Event)
 */
public class EventData {
	/**
	 * The information about the current dialog.
	 */
	public Dialog currentDialog;
	/**
	 * The information about the current scene.
	 */
	public SceneInfo currentScene;
	
	public EventData(Dialog currentDialog, SceneInfo currentScene) {
		this.currentDialog = currentDialog;
		this.currentScene = currentScene;
	}
}
