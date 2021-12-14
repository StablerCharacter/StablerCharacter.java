package ga.susite.StablerCharacter.events;

import tech.fastj.systems.audio.AudioManager;

import java.nio.file.Path;

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
}
