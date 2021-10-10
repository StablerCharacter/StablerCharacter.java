package ga.susite.StablerCharacter;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.Display;
import tech.fastj.graphics.game.Text2D;
import tech.fastj.graphics.ui.elements.Button;
import tech.fastj.input.keyboard.Keyboard;
import tech.fastj.input.keyboard.Keys;
import tech.fastj.math.Point;
import tech.fastj.math.Pointf;
import tech.fastj.systems.control.Scene;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ga.susite.StablerCharacter.utils.Event;
import ga.susite.StablerCharacter.utils.EventArgs;

public class GameScene extends Scene {
	public static final SceneInfo SCENE_INFO = new SceneInfo("game");
	Font mainFont;
	TextInfo dialogTextInfo;
	Text2D dialogText;
	StoryManager story;
	Pointf screenCenter;
	ExecutorService executor;
	Event<EventData> onUpdate;
	
	public GameScene(String name, Font nMainFont, TextInfo nDialogTextInfo, StoryManager nStory) {
		super(name);
		mainFont = nMainFont;
		dialogTextInfo = nDialogTextInfo;
		story = nStory;
		executor = Executors.newSingleThreadExecutor();
	}
	
	public void withOnUpdate(Event<EventData> onUpdate) {
		this.onUpdate = onUpdate;
	}
	
	void nextDialogWithAnimation() {
		Dialog dialog = story.getNext();
		dialogText.setText("");
		for(char character : dialog.message.toCharArray()) {
			dialogText.setText(dialogText.getText() + Character.toString(character));
			if(dialogTextInfo.textPos == null) {
				Pointf textCenter = screenCenter.copy();
				Pointf[] bounds = dialogText.getBounds();
				textCenter.x -= (bounds[1].x - bounds[0].x) / 2;
				dialogText.setTranslation(textCenter);
			}
			try {
				TimeUnit.MILLISECONDS.sleep(dialogTextInfo.textAnimationInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(dialog.event != null) dialog.event.onActionTriggered();
	}

	/**
	 * This method will advance to the next dialog and set the text.
	 * 
	 * @param screenCenter: the Screen center.
	 * */
	void nextDialog(Pointf screenCenter) {
		this.screenCenter = screenCenter;
		if(dialogTextInfo.isTextAnimated) {
			executor.submit(this::nextDialogWithAnimation);
			return;
		}
		Dialog dialog = story.getNext();
		dialogText.setText(dialog.message);
		if(dialogTextInfo.textPos == null) {
			Pointf[] bounds = dialogText.getBounds();
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			dialogText.setTranslation(screenCenter);
		}
		if(dialog.event != null) dialog.event.onActionTriggered();
	}

	@Override
	public void load(Display display) {
		// Code here
		Point displayRes = display.getInternalResolution();
		
		new Button(this)
			.setFont(mainFont)
			.setText("Next")
			.setFill(Color.GRAY)
			.addOnAction(event -> nextDialog(display.getScreenCenter()))
			.translate(new Pointf(displayRes.x - 125, displayRes.y - 50));
		
		dialogText = dialogTextInfo.build("", display.getScreenCenter());
		drawableManager.addGameObject(dialogText);
		story.setDialogIndex(-1);
		nextDialog(display.getScreenCenter());
	}

	@Override
	public void unload(Display display) {
		// More code here
	}

	@Override
	public void update(Display display) {
		if (Keyboard.isKeyRecentlyPressed(Keys.Enter)) {
			FastJEngine.log("Enter key was pressed!");
		}
		
		onUpdate.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
	}
}
