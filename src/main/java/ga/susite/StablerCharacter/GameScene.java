package ga.susite.StablerCharacter;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.Display;
import tech.fastj.graphics.Drawable;
import tech.fastj.graphics.game.Text2D;
import tech.fastj.graphics.ui.elements.Button;
import tech.fastj.input.keyboard.Keyboard;
import tech.fastj.input.keyboard.Keys;
import tech.fastj.math.Point;
import tech.fastj.math.Pointf;
import tech.fastj.systems.control.Scene;

import java.awt.Color;
import java.awt.Font;

public class GameScene extends Scene {
	Font mainFont;
	TextInfo dialogTextInfo;
	Text2D dialogText;
	StoryManager story;
	
	public GameScene(String name, Font nMainFont, TextInfo nDialogTextInfo, StoryManager nStory) {
		super(name);
		mainFont = nMainFont;
		dialogTextInfo = nDialogTextInfo;
		story = nStory;
	}

	/**
	 * This method will advance to the next dialog and set the text.
	 * 
	 * @param screenCenter: the Screen center.
	 * */
	void nextDialog(Pointf screenCenter) {
		Dialog dialog = story.getNext();
		FastJEngine.log(dialog.message);
		FastJEngine.log(dialogText.getCollisionPath().getBounds2D());
		dialogText.setText(dialog.message);
		FastJEngine.log(dialogText.getCollisionPath().getBounds2D());
		if(dialogTextInfo.textPos == null) {
			Pointf[] bounds = dialogText.getBounds();
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			FastJEngine.log(screenCenter);
			dialogText.setTranslation(screenCenter);
			FastJEngine.log(dialogText.getCollisionPath().getBounds2D());
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

		Dialog firstDialog = story.getCurrentDialog();
		dialogText = dialogTextInfo.build(firstDialog.message, display.getScreenCenter());
		if(firstDialog.event != null) firstDialog.event.onActionTriggered();
		drawableManager.addGameObject(dialogText);
	}

	@Override
	public void unload(Display display) {
		// More code here
	}

	@Override
	public void update(Display display) {
		if (Keyboard.isKeyRecentlyPressed(Keys.W)) {
			FastJEngine.log("W key was pressed");
		}

		if (Keyboard.isKeyRecentlyReleased(Keys.W)) {
			FastJEngine.log("W key was released");
		}
	}
}
