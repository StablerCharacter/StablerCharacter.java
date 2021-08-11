package ga.susite.StablerCharacter;

import java.io.IOException;
import java.util.HashMap;

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
			story = MarkdownParser.parseFile("D:\\eclipse-workspace\\StablerCharacter-java\\src\\main\\java\\ga\\susite\\StablerCharacter\\sampleMarkdown.md");
			story.printStoryTree();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		GameManager gm = GameManager.init("h game", story);
		gm.antiAliasing = true;
		gm.newChapterScreen = false;
		gm.start(new Point(800, 600));
	}
}
