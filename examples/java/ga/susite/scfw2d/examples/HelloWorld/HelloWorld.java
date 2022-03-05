package ga.susite.scfw2d.examples;

import java.util.HashMap;

import ga.susite.scfw2d.*;
import ga.susite.scfw2d.events.LogEvent;
import tech.fastj.math.Point;

public class HelloWorld {
    public static void main(String[] args) {
        GameManager gm = GameManager.init("Hello StablerCharacter.java!", getRawStory());
        gm.start();
    }

    static StoryManager getRawStory() {
        Dialog[] dialogs = {
            new Dialog("Hello there!", new LogEvent("Hello from the log event."))
        };
        Branch main = new Branch(dialogs);
        HashMap<String, Branch> branches = new HashMap<String, Branch>();
        branches.put("main", main);
        Chapter chapter = new Chapter(branches, "Chapter 1", "Hello world");
        Chapter[] chapters = { chapter };
        return new StoryManager(chapters);
    }
}