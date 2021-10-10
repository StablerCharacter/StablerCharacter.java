package ga.susite.StablerCharacter;

import java.io.IOException;

import ga.susite.StablerCharacter.utils.EventArgs;
import ga.susite.StablerCharacter.utils.MarkdownParser;
import tech.fastj.math.Point;

public class Game {
	public static void main(String[] args) {
		// Dialog dialog = new Dialog("Hello, world");
		// Dialog[] dialogs = {dialog};
		// Branch main = new Branch(dialogs);
		// HashMap<String, Branch> branches = new HashMap<String, Branch>();
		// branches.put("main", main);
		// Chapter chapter = new Chapter(branches, "Chapter 1", "h moment");
		// Chapter[] chapters = {chapter};
		//StoryManager story = new StoryManager(chapters);
		StoryManager story = null;
		try {
			// story = StoryManager.loadFromFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleStory.json");
			MarkdownParser markdownParser = new MarkdownParser();
			story = markdownParser.parseFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleMarkdown.md");
			story.printStoryTree();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
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
}
