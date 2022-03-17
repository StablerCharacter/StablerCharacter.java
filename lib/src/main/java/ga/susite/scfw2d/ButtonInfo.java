package ga.susite.scfw2d;

import java.awt.Color;
import java.awt.Font;
import java.util.function.Consumer;

import tech.fastj.graphics.ui.elements.Button;
import tech.fastj.input.mouse.events.MouseButtonEvent;
import tech.fastj.math.Pointf;
import tech.fastj.systems.control.Scene;

/**
 * A class that stores informations about a button.
 * @author lines-of-codes
 */
public class ButtonInfo {
	/**
	 * The label of the button.
	 * If this is not being set, Then the value will be the value of the default label.
	 * @author lines-of-codes
	 */
	public String label;
	/**
	 * The fill color of the button.
	 */
	public Color fillColor = Color.WHITE;
	/**
	 * The font of the text.
	 */
	public Font font = new Font("Segoe UI", Font.PLAIN, 16);
	/**
	 * The position of the button. By default will be at the center of the screen.
	 */
	public Pointf buttonPos;
	
	/**
	 * Set the fill color of the button.
	 * @param newColor The new target fill color.
	 * @return The current instance. (Allowing chaining)
	 */
	public ButtonInfo withFillColor(Color newColor) {
		this.fillColor = newColor;
		return this;
	}
	
	/**
	 * Set the label of the button.
	 * @param newLabel The new label.
	 * @return The current instance. (Allowing chaining)
	 */
	public ButtonInfo withLabel(String newLabel) {
		this.label = newLabel;
		return this;
	}
	
	/**
	 * Set the position of the button.
	 * @param newPos The new position.
	 * @return The current instance. (Allowing chaining)
	 */
	public ButtonInfo withPos(Pointf newPos) {
		this.buttonPos = newPos;
		return this;
	}
	
	/**
	 * Build the Button game object.
	 * @param origin The scene where the button will be created.
	 * @param defaultText The default text of the button if there is no label.
	 * @param defaultButtonPos The default button position.
	 * @param onClickAction The action to be performed when the button is clicked.
	 * @param xCentered Tells if you wanted to center the button horizontally.
	 */
	public void build(Scene origin, String defaultText, Pointf defaultButtonPos, Consumer<MouseButtonEvent> onClickAction, boolean xCentered) {
		if(label == null) label = defaultText;
		Button button = new Button(origin);
		button.setFont(font);
		button.setText(label);
		button.setFill(fillColor);
		button.addOnAction(onClickAction);
		if(buttonPos == null)
			buttonPos = defaultButtonPos;
		if(xCentered) {
			Pointf[] bounds = button.getBounds();
			buttonPos.x -= (bounds[1].x - bounds[0].x) / 2;
			button.translate(buttonPos);
		} else button.translate(buttonPos);
	}
}
