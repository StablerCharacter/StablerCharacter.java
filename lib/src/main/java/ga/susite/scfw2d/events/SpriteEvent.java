package ga.susite.scfw2d.events;

import java.nio.file.Path;
import java.util.Objects;

import ga.susite.scfw2d.GameScene;
import tech.fastj.graphics.game.Sprite2D;
import tech.fastj.math.Pointf;
import tech.fastj.resources.images.ImageResource;

/**
 * An Event that displays a Sprite when a dialog is shown.
 */
public class SpriteEvent implements Event {
	Path imagePath;
	Pointf position;
	
	/**
	 * Create a new instance of a SpriteEvent with the specified sprite path and a centered position.
	 * @param newImagePath The sprite's path.
	 */
	public SpriteEvent(Path newImagePath) {
		imagePath = newImagePath;
	}
	
	/**
	 * Create a new instance of a SpriteEvent with the specified sprite path and the specified position.
	 * @param newImagePath The sprite's path.
	 * @param newPosition The sprite position.	 
	 */
	public SpriteEvent(Path newImagePath, Pointf newPosition) {
		imagePath = newImagePath;
		position = newPosition;
	}

	@Override
	public void onActionTriggered() {
		System.out.println("test");
		try {
			Sprite2D sprite = Sprite2D.create(new ImageResource(imagePath)).build();
			System.out.print("Before sprite.scale(2): ");
			System.out.println(Objects.requireNonNull(sprite.getBounds()));
			sprite.scale(2);
			System.out.print("After sprite.scale(2): ");
			System.out.println(Objects.requireNonNull(sprite.getBounds()));
			if(position != null) sprite.translate(position);
			else {
				Pointf screenCenter = GameScene.screenCenter.copy();
				Pointf[] bounds = sprite.getBounds();
				screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
				sprite.setTranslation(screenCenter);
			}
			GameScene.drawableManagerInstance.addGameObject(sprite);
		} finally {
			System.out.println("something else");
		}
	}

}
