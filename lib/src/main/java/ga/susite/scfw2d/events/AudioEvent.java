package ga.susite.scfw2d.events;

import ga.susite.scfw2d.AudioLoader;

/**
 * Plays an audio when a dialog is reached.
 * @author lines-of-codes
 */
public class AudioEvent implements Event {
	String audioName;

	/**
	 * @param targetAudio The name of the target audio in the AudioLoader class.
	 */
	public AudioEvent(String targetAudio) {
		audioName = targetAudio;
	}
	
	public void onActionTriggered() {
		AudioLoader.getAudio(audioName).play();
	}

	public String getEventName() {
		return "AudioEvent";
	}
}
