package ga.susite.StablerCharacter;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.graphics.game.Text2D;
import tech.fastj.math.Pointf;

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
	
	TextInfo() {}
	
	TextInfo(Color nColor, Font nFont) {
		color = nColor;
		font = nFont;
		textPos = null;
	}

	TextInfo(Color nColor, Font nFont, Pointf nTextPos) {
		color = nColor;
		font = nFont;
		textPos = nTextPos;
	}
	
	public TextInfo withColor(Color newColor) {
		this.color = newColor;
		return this;
	}
	
	public TextInfo withoutTextAnimation() {
		isTextAnimated = false;
		return this;
	}
	
	public TextInfo withTextAnimation() {
		isTextAnimated = true;
		return this;
	}
	
	public TextInfo withTextAnimationInterval(int interval) {
		textAnimationInterval = interval;
		return this;
	}
	
	
	/**
	* Creates the text object. If textPos is null, then use the screenCenter as the fallback value.
	* This method is only meant to be used internally.
	* @param message: The target message
	* @param screenCenter: the screen center (fallback value if textPos is null)
	*/
	public Text2D build(String message, Pointf screenCenter) {
		Text2D text = Text2D.create(message).withFont(font).withFill(color).build();
		if(textPos != null) {
			text.translate(textPos);
		} else {
			Pointf[] bounds = text.getBounds();
			// Subtract the screen center with the half of the width of the text
			// width = TopRight - TopLeft
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			text.translate(screenCenter);
		}
		return text;
	}
}
