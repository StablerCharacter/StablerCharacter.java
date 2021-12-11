package ga.susite.StablerCharacter;

/**
 * A simple data class for storing informations about current dialog.
 * Used in Events. (ga.susite.StablerCharacter.utils.Event)
 */
public class EventData {
	public Dialog currentDialog;
	public SceneInfo currentScene;
	
	public EventData(Dialog currentDialog, SceneInfo currentScene) {
		this.currentDialog = currentDialog;
		this.currentScene = currentScene;
	}
}
