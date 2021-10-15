package ga.susite.StablerCharacter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import ga.susite.StablerCharacter.events.LogEvent;
import ga.susite.StablerCharacter.events.SpriteEvent;
import ga.susite.StablerCharacter.utils.EventArgs;
import ga.susite.StablerCharacter.utils.MarkdownParser;
import tech.fastj.math.Point;
import tech.fastj.math.Pointf;

public class Game {
	public static void main(String[] args) {
		
		StoryManager story = getRawStory();
		Game instance = new Game();
		GameManager gm = GameManager.init("StablerCharacter.java", story);
		gm.antiAliasing = true;
		gm.newChapterScreen = false;
		gm.onStoryEnd.addEventListener(instance::onStoryEnd);
		gm.start(new Point(800, 600));
	}
	
	Void onStoryEnd(EventArgs<EventData> e) {
		return null;
	}
	
	static StoryManager loadFromMarkdown() throws IOException {
		StoryManager story = null;
		// story = StoryManager.loadFromFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleStory.json");
		MarkdownParser markdownParser = new MarkdownParser();
		story = markdownParser.parseFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleMarkdown.md");
		story.printStoryTree();
		return story;
	}
	
	static StoryManager getRawStory() {
		Dialog[] dialogs = {
			new Dialog("Hello there!", new SpriteEvent(Path.of("./ryan.png"), new Pointf(10, 10)))
			// new Dialog("Hello there!", new LogEvent("hewwo"))
		};
		Branch main = new Branch(dialogs);
		HashMap<String, Branch> branches = new HashMap<String, Branch>();
		branches.put("main", main);
		Chapter chapter = new Chapter(branches, "Chapter 1", "h moment");
		Chapter[] chapters = {chapter};
		return new StoryManager(chapters);
	}
}
