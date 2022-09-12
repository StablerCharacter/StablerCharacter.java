package ga.susite.scfw2d;

import java.nio.file.Path;
import java.util.HashMap;

import tech.fastj.engine.FastJEngine;
import tech.fastj.systems.audio.Audio;
import tech.fastj.systems.audio.AudioManager;
import tech.fastj.systems.audio.MemoryAudio;
import tech.fastj.systems.audio.StreamedAudio;

/**
 * A code for managing audio files.
 * @author lines-of-codes
 */
public class AudioLoader {
    static HashMap<String, MemoryAudio> memoryAudioMap = new HashMap<String, MemoryAudio>();
    static HashMap<String, StreamedAudio> streamedAudioMap = new HashMap<String, StreamedAudio>();
    static AudioManager audioManager = FastJEngine.getAudioManager();

    /**
     * Load an audio file.
     * @param name The name of the audio. Must be unique.
     * @param audioPath The path of the audio file.
     * @param loadType Specifies how the audio file is played.
     */
    public static void loadAudio(String name, Path audioPath, AudioLoadType loadType) {
        if(loadType == AudioLoadType.MEMORY) {
            memoryAudioMap.put(name, audioManager.loadMemoryAudio(audioPath));
        } else if(loadType == AudioLoadType.STREAMED) {
            streamedAudioMap.put(name, audioManager.loadStreamedAudio(audioPath));
        }
    }

    /**
     * Unload an audio file.
     * @param name The name of the audio file you wanted to unload.
     * @param loadType The type of how the audio file is played.
     */
    public static void unloadAudio(String name, AudioLoadType loadType) {
        if(loadType == AudioLoadType.MEMORY) {
            audioManager.unloadMemoryAudio(memoryAudioMap.get(name).getID());
            memoryAudioMap.remove(name);
        } else if(loadType == AudioLoadType.STREAMED) {
            audioManager.unloadStreamedAudio(streamedAudioMap.get(name).getID());
            streamedAudioMap.remove(name);
        }
    }

    /**
     * Get an audio file object.
     * @param name The name of the audio file you wanted to get.
     * @return The audio file object.
     */
    public static Audio getAudio(String name) {
        if(memoryAudioMap.containsKey(name))
            return getAudio(name, AudioLoadType.MEMORY);
        return getAudio(name, AudioLoadType.STREAMED);
    }

    /**
     * Get an audio file object.
     * @param name The name of the audio file you wanted to get.
     * @param loadType The type of how the audio file is played.
     * @return The audio file object.
     */
    public static Audio getAudio(String name, AudioLoadType loadType) {
        if(loadType == AudioLoadType.MEMORY)
            return memoryAudioMap.get(name);
        return streamedAudioMap.get(name);
    }
}
