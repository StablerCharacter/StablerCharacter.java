package ga.susite.scfw2d;

import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.math.Pointf;
import tech.fastj.systems.control.Scene;

/**
 * The main menu scene. (WIP, DO NOT USE)
 * @author lines-of-codes
 */
public class MainMenuScene extends Scene {
	/**
	 * The text information about the game title.
	 */
	public static TextInfo titleInfo;
	/**
	 * The button information about the play button.
	 */
	public static ButtonInfo playButtonInfo;

	/**
	 * Initialize the main menu.
	 */
	public MainMenuScene() {
		super("MainMenu");
	}
	
	/**
	 * Play the game.
	 */
	public void playGame() {
		// TODO: Implement this method.
	}

	@Override
	public void load(FastJCanvas canvas) {
		Pointf titlePos = canvas.getCanvasCenter();
		titlePos.y = 40;
		drawableManager.addGameObject(titleInfo.build(GameManager.get().gameName, titlePos));
		Pointf buttonPos = canvas.getCanvasCenter();
		buttonPos.y = 80;
		playButtonInfo.build(this, "Play", buttonPos.copy(), event -> playGame());
	}

	@Override
	public void unload(FastJCanvas canvas) {
		// No need for any unload code.
	}

	@Override
	public void update(FastJCanvas canvas) {
		// There's not any logic that needs to be run in every frame yet.
	}
}
