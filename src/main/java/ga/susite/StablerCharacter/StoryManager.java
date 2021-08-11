package ga.susite.StablerCharacter;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StoryManager {
	Chapter[] chapters;
	Integer chapterIndex = 0;
	
	StoryManager() {}
	
	StoryManager(Chapter[] nChapters) {
		chapters = nChapters;
	}
	
	public Dialog getNext() {
		chapters[chapterIndex].advanceDialog();
		return chapters[chapterIndex].getCurrentDialog();
	}
	
	/*
	 * Print the Story tree.
	 * Mainly used in debugging.*/
	public void printStoryTree() {
		System.out.println("- StoryManager - ");
		int currentChapterIndex = 0;
		for(Chapter i : chapters) {
			System.out.println("- Chapter index " + currentChapterIndex);
			i.printChapterTree();
		}
	}
	
	@Deprecated
	public static StoryManager loadFromFile(String filePath) throws IOException {
		Gson gson = new Gson();
		return gson.fromJson(Files.readString(Paths.get(filePath), StandardCharsets.UTF_8), StoryManager.class);
	}
}
