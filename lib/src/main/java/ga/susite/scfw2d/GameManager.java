package ga.susite.scfw2d;

import ga.susite.scfw2d.utils.EventArgs;
import ga.susite.scfw2d.utils.MethodEvent;
import ga.susite.scfw2d.utils.OptionDialog;
import tech.fastj.engine.FastJEngine;
import tech.fastj.graphics.dialog.DialogConfig;
import tech.fastj.graphics.dialog.DialogMessageTypes;
import tech.fastj.graphics.dialog.DialogUtil;
import tech.fastj.graphics.display.FastJCanvas;
import tech.fastj.graphics.display.RenderSettings;
import tech.fastj.logging.LogLevel;
import tech.fastj.math.Point;
import tech.fastj.systems.control.Scene;
import tech.fastj.systems.control.SceneManager;

import java.util.ArrayList;

/**
 * TManages the game.
 * @author lines-of-codes
 */
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
	 * a Boolean to indicate to Enable the New chapter screen or not. (Not implemented yet)
	 */
	public boolean newChapterScreen = false;
	/**
	 * Sets if the default option dialog is enabled.<br>
	 * The option could be {@code disabled}, {@code engineArgs}, and {@code enabled}.<br>
	 * <ul>
	 * 	<li>{@code DISABLED} will completely disable the options dialog.</li>
	 * 	<li>{@code ENGINE_ARGS} will enable the options dialog if it's specified in the command line. 
	 * (Currently, this is not implemented)</li>
	 * 	<li>{@code ENABLED} will always enable the options dialog.</li>
	 * </ul>
	 */
	public OptionDialogOption optionDialog = OptionDialogOption.ENABLED;
	/**
	 * The overall rendering quality of FastJ.
	 */
	public RenderSettings renderingQuality = RenderSettings.RenderingQuality.High;
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
	public static MethodEvent<Void> onInit = new MethodEvent<Void>();
	/**
	 * An event triggered when the game update loop is being called.
	 */
	public static MethodEvent<EventData> onUpdate = new MethodEvent<EventData>();
	/**
	 * An event triggered when the story ended.
	 */
	public static MethodEvent<EventData> onStoryEnd = new MethodEvent<EventData>();
	/**
	 * An event triggered when the story started. (The first dialog appeared)
	 */
	public static MethodEvent<EventData> onStoryStart = new MethodEvent<EventData>();
	
	/**
	 * The scenes in the game.
	 */
	public static ArrayList<Scene> scenes = new ArrayList<Scene>();
	
	private static GameManager instance;
	
	/**
	 * Creates the Game manager class instance.
	 * 
	 * @author lines-of-codes
	 * @param nGameName the Game name (Used for Window title, Main menu, etc.)
	 * @param nStory The Story of the Game.
	 */
	private GameManager(String nGameName, StoryManager nStory) {
		gameName = nGameName;
		story = nStory;
	}
	
	/**
	 * Initializes the game.
	 */
	public void start() {
		if(scenes.size() == 0) {
			scenes.add(new GameScene(dialogTextInfo.font, dialogTextInfo));
		}
		if(optionDialog == OptionDialogOption.ENABLED) {
			DialogUtil.showOptionDialog(
				DialogConfig.create()
					.withTitle("Options")
					.withPrompt(new OptionDialog())
					.build(),
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
	 * @return Returns the instance.
	 */
	public static GameManager init(String nGameName, StoryManager nStory) {
		instance = new GameManager(nGameName, nStory);
		return instance;
	}
	
	/**
	 * Get the GameManager instance.
	 * Note: This should only be called _after_ the init() function.
	 * @return Return the instance of the GameManager.
	 */
	public static GameManager get() { return instance; }
	
	/**
	 * Load a scene from its index.
	 * @param index The index of the scene.
	 */
	public void loadScene(int index) {
		this.setCurrentScene(scenes.get(index));
		this.loadCurrentScene();
	}

	/**
	 * Loads a scene from its name.
	 * @param name The name of the scene.
	 */
	public void loadScene(String name) {
		for(int i = 0; i < scenes.size(); i++) {
			if(scenes.get(i).getSceneName().equals(name)) {
				loadScene(i);
				break;
			}
		}
	}

	@Override
	public void init(FastJCanvas display) {
		if(antiAliasing) {
			display.modifyRenderSettings(RenderSettings.Antialiasing.Enable);
		} else display.modifyRenderSettings(RenderSettings.Antialiasing.Disable);
		display.modifyRenderSettings(renderingQuality);
		
		this.addScenes(scenes);
		// Loads the first scene
		loadScene(0);
		
		FastJEngine.trace("onInit event is being invoked...");
		onInit.invoke(EventArgs.none);
	}
}
