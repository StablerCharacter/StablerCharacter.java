package ga.susite.scfw2d.examples;

import java.util.HashMap;

import ga.susite.scfw2d.*;
import ga.susite.scfw2d.events.LogEvent;
import tech.fastj.math.Point;

public class HelloWorld {
    public static void main(String[] args) {
        StoryManager story = getRawStory();
        GameManager gm = GameManager.init("Hello StablerCharacter.java!", story);
        gm.start();
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