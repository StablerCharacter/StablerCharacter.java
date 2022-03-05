package ga.susite.scfw2d;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.graphics.game.Text2D;
import tech.fastj.math.Pointf;

/**
 * The class used for storing information about Text objects.
 */
public class TextInfo {
	/**
	 * The color of the text.
	 */
	public Color color = Color.BLACK;
	/**
	 * The font of the text.
	 */
	public Font font = new Font("Segoe UI", Font.PLAIN, 16);
	/**
	 * The position of the text. By default will be at the center of the screen.
	 */
	public Pointf textPos;
	/**
	 * Tells if the text is supposed to be animated.
	 * This property does not affect some system.
	 */
	public boolean isTextAnimated = true;
	/**
	 * The duration of sleeping between characters in text animation rendering in milliseconds.
	 */
	public int textAnimationInterval = 50;
	
	/**
	 * Initialize a new TextInfo class instance with default options.
	 */
	public TextInfo() {}
	
	/**
	 * Initialize the class with the specified color and font.
	 * @param nColor The color of the text.
	 * @param nFont The font of the text.
	 */
	public TextInfo(Color nColor, Font nFont) {
		color = nColor;
		font = nFont;
		textPos = null;
	}

	/**
	 * Initialize the TextInfo class with the specified color, font, and position.
	 * @param nColor The color of the text.
	 * @param nFont The font of the text.
	 * @param nTextPos The position of the text.
	 */
	public TextInfo(Color nColor, Font nFont, Pointf nTextPos) {
		color = nColor;
		font = nFont;
		textPos = nTextPos;
	}
	
	/**
	 * Set the color of the text.
	 * @return The current instance. (Allowing chaining)
	 */
	public TextInfo withColor(Color newColor) {
		this.color = newColor;
		return this;
	}
	 
	/**
	 * Make so that the text will never be animated.
	 * @return The current instance. (Allowing chaining)
	 */
	public TextInfo withoutTextAnimation() {
		isTextAnimated = false;
		return this;
	}
	
	/**
	 * Make so that the text will be animated (if supported).
	 * @return The current instance. (Allowing chaining)
	 */
	public TextInfo withTextAnimation() {
		isTextAnimated = true;
		return this;
	}
	
	/**
	 * Set an interval between character of the animation for the text.
	 * @param interval The interval between character.
	 * @return The current instance. (Allowing chaining)
	 */
	public TextInfo withTextAnimationInterval(int interval) {
		textAnimationInterval = interval;
		return this;
	}
	
	
	/**
	* Creates the text object. If textPos is null, then use the screenCenter as the fallback value.
	* This method is only meant to be used internally.
	* @param message The target message
	* @param defaultPosition the screen center or the default text position. (fallback value if textPos is null)
	* @return The text game object.
	*/
	public Text2D build(String message, Pointf defaultPosition) {
		Text2D text = Text2D.create(message).withFont(font).withFill(color).build();
		if(textPos != null) {
			text.translate(textPos);
		} else {
			Pointf[] bounds = text.getBounds();
			// Subtract the screen center with the half of the width of the text
			// width = TopRight - TopLeft
			defaultPosition.x -= (bounds[1].x - bounds[0].x) / 2;
			text.translate(defaultPosition);
		}
		return text;
	}
}
