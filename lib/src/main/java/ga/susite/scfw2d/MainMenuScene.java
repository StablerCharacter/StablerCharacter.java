package ga.susite.scfw2d;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.math.Point;
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
	public static TextInfo titleInfo = new TextInfo().withFont(new Font("Segoe UI", Font.BOLD, 22));
	/**
	 * The button information about the play button.
	 */
	public static ButtonInfo playButtonInfo = new ButtonInfo();
	/**
	 * The button information about the quit button.
	 */
	public static ButtonInfo quitButtonInfo = new ButtonInfo();
	/**
	 * The game's background color.
	 */
	Color backgroundColor;

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public MainMenuScene withBackgroundColor(Color backgroundColor) {
		setBackgroundColor(backgroundColor);
		return this;
	}

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
		GameManager.get().loadScene("GameScene");
	}

	@Override
	public void load(FastJCanvas canvas) {
		Point size = canvas.getResolution();
		Pointf titlePos = canvas.getCanvasCenter();
		titlePos.y = titlePos.y - size.y / 3;
		drawableManager.addGameObject(titleInfo.build(GameManager.get().gameName, titlePos));
		Pointf buttonPos = canvas.getCanvasCenter();
		buttonPos.y = titlePos.y + size.y / 4;
		playButtonInfo.build(this, "Play", buttonPos.copy(), event -> playGame(), true);
		buttonPos.y = buttonPos.y + size.y / 10;
		quitButtonInfo.build(this, "Quit", buttonPos, event -> FastJEngine.closeGame(), true);
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
