package ga.susite.scfw2d.examples.HelloWorld;

import java.util.HashMap;

import ga.susite.scfw2d.*;
import ga.susite.scfw2d.events.LogEvent;

public class Main {
    public static void main(String[] args) {
        StoryManager story = getRawStory();
        // GameManager gm = GameManager.init("Hello StablerCharacter.java!", story);
        // gm.start();
        HashMap<String, Branch> branches = new HashMap<String, Branch>();
        branches.put("main", new Branch(new Dialog[] { new Dialog("Hello there!", new LogEvent("Hello from the log event!")) }));
        Chapter[] chapters = { new Chapter(branches, "Chapter 1", "") };
        StoryManager sameStory = new StoryManager(chapters);
        story.printStoryTree();
        sameStory.printStoryTree();
        System.out.println(story.equals(sameStory));
    }

    static StoryManager getRawStory() {
        StoryConstructor storyconstructor = new StoryConstructor() {
            @Override
            public void buildContent() {
                chapter("Chapter 1", null);
                branch("main");
                dialog("Hello there!", new LogEvent("Hello from the log event!"));
            }
        };
        storyconstructor.buildContent();
        return storyconstructor.finalizeStory();
    }
}