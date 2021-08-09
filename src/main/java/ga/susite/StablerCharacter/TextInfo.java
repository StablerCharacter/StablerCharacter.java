package ga.susite.StablerCharacter;

import java.awt.Color;
import java.awt.Font;

import tech.fastj.graphics.game.Text2D;

public class TextInfo {
	Color color;
	Font font;
	
	TextInfo(Color nColor, Font nFont) {
		color = nColor;
		font = nFont;
	}
	
	public Text2D build(String message) {
		return Text2D.create(message).withFont(font).withFill(color).build();
	}
}
