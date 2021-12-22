package ga.susite.StablerCharacter;

import java.io.IOException;
import java.util.HashMap;

import ga.susite.StablerCharacter.events.LogEvent;
import ga.susite.StablerCharacter.utils.MarkdownParser;
import tech.fastj.math.Point;

public class Game {
	public static void main(String[] args) {
		GameManager gm = GameManager.init("StablerCharacter.java", getRawStory());
		gm.start();
	}
	
	static StoryManager loadFromMarkdown() throws IOException {
		StoryManager story = null;
		MarkdownParser markdownParser = new MarkdownParser();
		story = markdownParser.parseFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleMarkdown.md");
		story.printStoryTree();
		return story;
	}
	
	static StoryManager getRawStory() {
		Dialog[] dialogs = {
			 new Dialog("Hello there!", new LogEvent("hewwo"))
		};
		Branch main = new Branch(dialogs);
		HashMap<String, Branch> branches = new HashMap<String, Branch>();
		branches.put("main", main);
		Chapter chapter = new Chapter(branches, "Chapter 1", "h moment");
		Chapter[] chapters = {chapter};
		return new StoryManager(chapters);
	}
}
