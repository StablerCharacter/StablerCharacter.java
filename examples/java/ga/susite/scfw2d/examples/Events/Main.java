package ga.susite.scfw2d.examples.Events;

import java.awt.Font;

import ga.susite.scfw2d.GameManager;
import ga.susite.scfw2d.GameScene;
import ga.susite.scfw2d.MainMenuScene;
import ga.susite.scfw2d.StoryConstructor;
import ga.susite.scfw2d.TextInfo;
import tech.fastj.engine.FastJEngine;

public class Main {
    public static void main(String[] args) {
        StoryConstructor story = new StoryConstructor() {
            @Override
            public void buildContent() {
                chapter("Chapter 1", null);
                branch("main");
                dialog("Hello, world!");
                dialog("This is now the last dialog, If you clicked Next, the game should close.");
            }
        };
        story.buildContent();
        GameManager gm = GameManager.init("Main menu example", story.finalizeStory());
        // Triggered when the game is initialized.
        GameManager.onInit.addEventListener(eventArgs -> {
            FastJEngine.log("On initialize triggered!");
            return null;
        });
        // Triggered when the story is being started.
        GameManager.onStoryStart.addEventListener(eventArgs -> {
            FastJEngine.log("Story started! The current name of the scene is " + eventArgs.data.currentScene.name);
            return null;
        });
        // This event is triggered when the story has ended.
        GameManager.onStoryEnd.addEventListener(eventArgs -> {
            FastJEngine.closeGame();
            return null;
        });
        GameManager.scenes.add(new MainMenuScene());
        GameManager.scenes.add(new GameScene(new Font("Ubuntu", Font.PLAIN, 16), new TextInfo()));
        gm.start();
    }
}
