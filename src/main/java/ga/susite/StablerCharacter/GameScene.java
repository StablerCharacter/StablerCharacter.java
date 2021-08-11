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
	
	public GameScene(String name, Font nMainFont, TextInfo nDialogTextInfo) {
        super(name);
        mainFont = nMainFont;
        dialogTextInfo = nDialogTextInfo;
    }

    @Override
    public void load(Display display) {
        // Code here
    	Point displayRes = display.getInternalResolution();
    	
    	new Button(this)
    		.setFont(mainFont)
    		.setText("Next")
    		.setFill(Color.GRAY)
            .addOnAction(event -> FastJEngine.log("it's working"))
            .translate(new Pointf(displayRes.x - 125, displayRes.y - 50));

    	Text2D dialogText = dialogTextInfo.build("Hello, world!", display.getScreenCenter());
		drawableManager.addGameObject(dialogText);
    }

    @Override
    public void unload(Display display) {
        // More code here
    }

    @Override
    public void update(Display display) {
        if (Keyboard.isKeyDown(Keys.W)) {
            FastJEngine.log("W key is held down");
        }

        if (Keyboard.isKeyRecentlyPressed(Keys.W)) {
            FastJEngine.log("W key was pressed");
        }

        if (Keyboard.isKeyRecentlyReleased(Keys.W)) {
            FastJEngine.log("W key was released");
        }

        if ("W".equals(Keyboard.getLastKeyPressed())) {
            FastJEngine.log("Last key pressed was W");
        }
    }
}
