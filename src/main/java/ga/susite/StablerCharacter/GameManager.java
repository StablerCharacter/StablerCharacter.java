package ga.susite.StablerCharacter;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.display.Display;
import tech.fastj.graphics.display.RenderSettings;
import tech.fastj.math.Point;
import tech.fastj.systems.control.SceneManager;

import ga.susite.StablerCharacter.utils.Event;
import ga.susite.StablerCharacter.utils.EventArgs;

public class GameManager extends SceneManager {
	public String gameName;
	public boolean antiAliasing = true; // a Boolean to indicate to Enable Anti-aliasing or not.
	public boolean newChapterScreen = false; // a Boolean to indicate to Enable the New chapter screen or not.
	public StoryManager story;
	public TextInfo dialogTextInfo = new TextInfo();
	
	// - Events -
	public Event<Void> onInit = new Event<Void>();
	public Event<EventData> onUpdate = new Event<EventData>();
	public Event<EventData> onStoryEnd = new Event<EventData>();
	public Event<EventData> onStoryStart = new Event<EventData>();
	
	private static GameManager instance;
	
	/**
	 * Creates the Game manager class instance.
	 * 
	 * @author lines-of-codes
	 * @param nGameName: the Game name (Used for Window title, Main menu, etc.)
	 * @param nStory: The Story of the Game.
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
	
	/**
	 * Initialize the Game manager class.
	 * 
	 * @author lines-of-codes
	 * @param nGameName the Game name (Used for Window title, Main menu, etc.)
	 * @param nStory The Story of the Game.
	 */
	public static GameManager init(String nGameName, StoryManager nStory) {
		instance = new GameManager(nGameName, nStory);
		return instance;
	}

	@Override
	public void init(Display display) {
		if(antiAliasing) {
			display.modifyRenderSettings(RenderSettings.Antialiasing.Enable);
		}
		
		display.getJFrame().setResizable(false);
		
		GameScene gameScene = new GameScene("Game scene", dialogTextInfo.font, dialogTextInfo, story);
		gameScene.withOnUpdate(onUpdate);
		this.addScene(gameScene);
		this.setCurrentScene(gameScene);
		this.loadCurrentScene();
		
		onInit.invoke(EventArgs.none);
	}
}
