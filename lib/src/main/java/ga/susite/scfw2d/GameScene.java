package ga.susite.scfw2d;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.graphics.game.Text2D;
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

import ga.susite.scfw2d.utils.EventArgs;

/**
 * The main game scene.
 */
public class GameScene extends Scene {
	/**
	 * The information about the scene.
	 */
	public static final SceneInfo SCENE_INFO = new SceneInfo("GameScene");
	/**
	 * The FastJ's DrawableManager instance.
	 * Used internally in events.
	 */
	public static DrawableManager drawableManagerInstance;
	/**
	 * The main default font used in the game.
	 */
	Font mainFont;
	TextInfo dialogTextInfo;
	Text2D dialogText;
	ButtonInfo nextButtonInfo = new ButtonInfo();
	StoryManager story;
	FastJCanvas canvas;
	/**
	 * The background color of the scene.
	 */
	Color backgroundColor = Color.WHITE;

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		if(canvas != null) canvas.setBackgroundColor(backgroundColor);
	}

	public GameScene withBackgroundColor(Color backgroundColor) {
		setBackgroundColor(backgroundColor);
		return this;
	}

	/**
	 * The screen center. Highly used internally.
	 */
	public static Pointf screenCenter;
	ExecutorService executor;
	
	/**
	 * Initialize the game scene with the specified main font for the game and the specified dialog text information.
	 * @param nMainFont The target main font for the game.
	 * @param nDialogTextInfo The text information about the dialog text.
	 */
	public GameScene(Font nMainFont, TextInfo nDialogTextInfo) {
		super("GameScene");
		if(nDialogTextInfo == null) {
			nDialogTextInfo = new TextInfo();
		}
		if(nMainFont == null) {
			nMainFont = nDialogTextInfo.font;
		}
		mainFont = nMainFont;
		dialogTextInfo = nDialogTextInfo;
		story = GameManager.get().story;
		executor = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * Show the next dialog with animation.
	 */
	void nextDialogWithAnimation() {
		if(story.getDialogIndex() + 1 >= story.getCurrentBranchLength()) {
			FastJEngine.log("Story ended!");
			GameManager.onStoryEnd.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
			return;
		}
		Dialog dialog = story.getNext();
		if(dialog.events != null) dialog.events.invoke();
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
				FastJEngine.warning("InterruptedException received.");
			}
		}
	}

	/**
	 * This method will advance to the next dialog and set the text.
	 * 
	 * @param screenCenter the Screen center.
	 * */
	void nextDialog(Pointf screenCenter) {
		GameScene.screenCenter = screenCenter;
		if(dialogTextInfo.isTextAnimated) {
			executor.submit(this::nextDialogWithAnimation);
			return;
		}
		if(story.getDialogIndex() + 1 >= story.getCurrentBranchLength()) {
			FastJEngine.log("Story ended!");
			GameManager.onStoryEnd.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
			return;
		}
		Dialog dialog = story.getNext();
		if(dialog.events != null) dialog.events.invoke();
		dialogText.setText(dialog.message);
		if(dialogTextInfo.textPos == null) {
			Pointf[] bounds = dialogText.getBounds();
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			dialogText.setTranslation(screenCenter);
		}
	}

	@Override
	public void load(FastJCanvas canvas) {
		canvas.setBackgroundColor(backgroundColor);
		this.canvas = canvas;
		Point displayRes = canvas.getResolution();

		nextButtonInfo.build(
			this, 
			"Next", 
			new Pointf(displayRes.x - 125, displayRes.y - 70), 
			event -> nextDialog(canvas.getCanvasCenter()), 
			false
		);
		
		
		dialogText = dialogTextInfo.build("", canvas.getCanvasCenter());
		drawableManager.addGameObject(dialogText);
		drawableManagerInstance = drawableManager;
		FastJEngine.log("Starting story...");
		story.setDialogIndex(-1);
		nextDialog(canvas.getCanvasCenter());
		FastJEngine.log("Invoking onStoryStart event...");
		GameManager.onStoryStart.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
	}

	@Override
	public void unload(FastJCanvas canvas) {
		// More code here
	}

	@Override
	public void update(FastJCanvas canvas) {
		if (Keyboard.isKeyRecentlyPressed(Keys.Enter)) {
			nextDialog(canvas.getCanvasCenter());
		}
		
		GameManager.onUpdate.invoke(new EventArgs<EventData>(new EventData(story.getCurrentDialog(), SCENE_INFO)));
	}
}
