package ga.susite.StablerCharacter;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.graphics.game.Text2D;
import tech.fastj.graphics.ui.elements.Button;
import tech.fastj.input.keyboard.Keyboard;
import tech.fastj.input.keyboard.Keys;
import tech.fastj.math.Point;
import tech.fastj.math.Pointf;
import tech.fastj.systems.control.DrawableManager;
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
	public static DrawableManager drawableManagerInstance;
	Font mainFont;
	TextInfo dialogTextInfo;
	Text2D dialogText;
	StoryManager story;
	public static Pointf screenCenter;
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
		if(story.getDialogIndex() + 1 >= story.getCurrentBranchLength()) {
			FastJEngine.log("Story ended!");
			return;
		}
		Dialog dialog = story.getNext();
		if(dialog.event != null) dialog.event.onActionTriggered();
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
	}

	/**
	 * This method will advance to the next dialog and set the text.
	 * 
	 * @param screenCenter: the Screen center.
	 * */
	void nextDialog(Pointf screenCenter) {
		GameScene.screenCenter = screenCenter;
		if(dialogTextInfo.isTextAnimated) {
			executor.submit(this::nextDialogWithAnimation);
			return;
		}
		if(story.getDialogIndex() + 1 >= story.getCurrentBranchLength()) {
			FastJEngine.log("Story ended!");
			return;
		}
		Dialog dialog = story.getNext();
		if(dialog.event != null) dialog.event.onActionTriggered();
		dialogText.setText(dialog.message);
		if(dialogTextInfo.textPos == null) {
			Pointf[] bounds = dialogText.getBounds();
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			dialogText.setTranslation(screenCenter);
		}
	}

	@Override
	public void load(FastJCanvas canvas) {
		Point displayRes = canvas.getResolution();
		
		new Button(this)
			.setFont(mainFont)
			.setText("Next")
			.setFill(Color.GRAY)
			.addOnAction(event -> nextDialog(canvas.getCanvasCenter()))
			.translate(new Pointf(displayRes.x - 125, displayRes.y - 70));
		
		dialogText = dialogTextInfo.build("", canvas.getCanvasCenter());
		drawableManager.addGameObject(dialogText);
		drawableManagerInstance = drawableManager;
		story.setDialogIndex(-1);
		nextDialog(canvas.getCanvasCenter());
	}

	@Override
	public void unload(FastJCanvas display) {
		// More code here
	}

	@Override
	public void update(FastJCanvas display) {
		if (Keyboard.isKeyRecentlyPressed(Keys.Enter)) {
			FastJEngine.log("Enter key was pressed!");
		}
		
		onUpdate.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
	}
}
