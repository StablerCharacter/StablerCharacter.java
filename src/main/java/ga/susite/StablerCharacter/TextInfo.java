package ga.susite.StablerCharacter;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.graphics.game.Text2D;
import tech.fastj.math.Pointf;

public class TextInfo {
	public Color color;
	public Font font;
	public Pointf textPos;
	
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
