package ga.susite.StablerCharacter;

import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.dialog.DialogConfig;
import tech.fastj.graphics.dialog.DialogMessageTypes;
import tech.fastj.graphics.dialog.DialogOptions;
import tech.fastj.graphics.dialog.DialogUtil;
import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.graphics.display.RenderSettings;
import tech.fastj.logging.LogLevel;
import tech.fastj.math.Point;
import tech.fastj.systems.control.SceneManager;

import ga.susite.StablerCharacter.utils.Event;
import ga.susite.StablerCharacter.utils.EventArgs;
import ga.susite.StablerCharacter.utils.OptionDialog;

public class GameManager extends SceneManager {
	/**
	 * The game project name. This will be used for the Window title.
	 */
	public String gameName;
	/**
	 * a Boolean to indicate to Enable Anti-aliasing or not.
	 */
	public boolean antiAliasing = true;
	/**
	 * a Boolean to indicate to Enable the New chapter screen or not.
	 */
	public boolean newChapterScreen = false;
	/**
	 * Sets if the default option dialog is enabled.<br>
	 * The option could be {@code disabled}, {@code engineArgs}, and {@code enabled}.<br>
	 * <ul>
	 * 	<li>{@code disabled} will completely disable the options dialog.</li>
	 * 	<li>{@code engineArgs} will enable the options dialog if it's specified in the command line. (Currently, this is not implemented)</li>
	 * 	<li>{@code enabled} will always enable the options dialog.</li>
	 * </ul>
	 */
	public OptionDialogOption optionDialog = OptionDialogOption.enabled;
	/**
	 * The overall rendering quality of FastJ.
	 */
	public RenderSettings renderingQuality = RenderSettings.GeneralRenderingQuality.High;
	/**
	 * The game story manager.
	 */
	public StoryManager story;
	/**
	 * The information about the dialog text info.
	 */
	public TextInfo dialogTextInfo = new TextInfo();
	/**
	 * The game window resolution.
	 */
	public static Point gameWindowResolution = new Point(800, 600);
	
	// - Events -
	/**
	 * An event triggered when the game manager is initialized.
	 */
	public static Event<Void> onInit = new Event<Void>();
	/**
	 * An event triggered when the game update loop is being called.
	 */
	public static Event<EventData> onUpdate = new Event<EventData>();
	/**
	 * An event triggered when the story ended.
	 */
	public static Event<EventData> onStoryEnd = new Event<EventData>();
	/**
	 * An event triggered when the story started. (The first dialog appeared)
	 */
	public static Event<EventData> onStoryStart = new Event<EventData>();
	
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
	
	/**
	 * Initializes the game.
	 */
	public void start() {
		if(optionDialog == OptionDialogOption.enabled) {
			DialogUtil.showOptionDialog(
				DialogConfig.create()
					.withTitle("Options")
					.withPrompt(new OptionDialog())
					.build(),
				DialogOptions.Default,
				DialogMessageTypes.Question,
				null,
				null
			);
		}
		FastJEngine.init(gameName, this);
		FastJEngine.configureWindowResolution(gameWindowResolution);
		FastJEngine.configureCanvasResolution(gameWindowResolution);
		FastJEngine.run();
	}
	
	/**
	 * Initializes the game with the specified LogLevel.
	 * @param logLevel The logging level you wanted to target.
	 */
	public void start(LogLevel logLevel) {
		FastJEngine.configureLogging(logLevel);
		start();
	}
	
	/**
	 * Initialize the Game manager class.
	 * Note: This method should be called **only** once.
	 * 
	 * @author lines-of-codes
	 * @param nGameName the Game name (Used for Window title, Main menu, etc.)
	 * @param nStory The Story of the Game.
	 */
	public static GameManager init(String nGameName, StoryManager nStory) {
		instance = new GameManager(nGameName, nStory);
		return instance;
	}
	
	/**
	 * Get the GameManager instance.
	 * Note: This should only be called _after_ the init() function.
	 */
	public static GameManager get() { return instance; }

	@Override
	public void init(FastJCanvas display) {
		if(antiAliasing) {
			display.modifyRenderSettings(RenderSettings.Antialiasing.Enable);
		} else display.modifyRenderSettings(RenderSettings.Antialiasing.Disable);
		display.modifyRenderSettings(renderingQuality);
		
//		display.getJFrame().setResizable(false);
		
		GameScene gameScene = new GameScene("Game scene", dialogTextInfo.font, dialogTextInfo, story);
		this.addScene(gameScene);
		this.setCurrentScene(gameScene);
		this.loadCurrentScene();
		
		FastJEngine.trace("onInit event is being invoked...");
		onInit.invoke(EventArgs.none);
	}
}
