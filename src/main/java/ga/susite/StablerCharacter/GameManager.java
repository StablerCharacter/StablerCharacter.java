package ga.susite.StablerCharacter;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.Display;
import tech.fastj.graphics.display.RenderSettings;
import tech.fastj.math.Point;
import tech.fastj.systems.control.SceneManager;

public class GameManager extends SceneManager {
	public String gameName;
	public boolean antiAliasing = true; // a Boolean to indicate to Enable Anti-aliasing or not.
	public boolean newChapterScreen = false; // a Boolean to indicate to Enable the New chapter screen or not.
	public StoryManager story;
	public TextInfo dialogTextInfo = new TextInfo(Color.BLACK, new Font("Segoe UI", Font.PLAIN, 16));
	private static GameManager gm;
	
	/**
	 * Instantiate the Game manager class.
	 * 
	 * @author lines-of-codes
	 * @param nGameName: the Game name (Used for Window title, Main menu, etc.)
	 * @param story: The Story of the Game.
	 */
	private GameManager(String nGameName, StoryManager nStory) {
		gameName = nGameName;
		story = nStory;
	}
	
	public void start(Point windowResolution) {
		FastJEngine.init(gameName, this);
		FastJEngine.configureWindowResolution(windowResolution);
		FastJEngine.configureInternalResolution(windowResolution);
		// FastJEngine.configureDebugging(true);
		FastJEngine.run();
	}
	
	public static GameManager init(String nGameName, StoryManager nStory) {
		gm = new GameManager(nGameName, nStory);
		return gm;
	}

	@Override
	public void init(Display display) {
		// TODO Auto-generated method stub
		if(antiAliasing) {
			display.modifyRenderSettings(RenderSettings.Antialiasing.Enable);
		}
		
		display.getJFrame().setResizable(false);
		
		GameScene gameScene = new GameScene("Game scene", dialogTextInfo.font, dialogTextInfo, story);
		this.addScene(gameScene);
		this.setCurrentScene(gameScene);
		this.loadCurrentScene();
	}
}
