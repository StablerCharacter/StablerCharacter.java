package ga.susite.scfw2d.events;

import tech.fastj.systems.audio.AudioManager;

import java.nio.file.Path;

/**
 * Plays an audio when a dialog is reached.
 */
public class AudioEvent implements Event {
	Path audioPath;
	
	/**
	 * @param audioPath The path of the audio.
	 */
	public AudioEvent(Path audioPath) {
		this.audioPath = audioPath;
	}
	
	public void onActionTriggered() {
		AudioManager.playSound(audioPath);
	}

	public String getEventName() {
		return "AudioEvent";
	}
}
