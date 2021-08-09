package ga.susite.StablerCharacter;

import tech.fastj.systems.audio.AudioManager;

import java.nio.file.Path;

public class AudioEvent implements Event {
	Path audioPath;
	
	AudioEvent(Path audioPath) {
		this.audioPath = audioPath;
	}
	
	public void onActionTriggered() {
		AudioManager.playSound(audioPath);
	}
}
