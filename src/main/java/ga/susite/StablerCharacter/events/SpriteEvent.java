package ga.susite.StablerCharacter.events;

import java.nio.file.Path;

import ga.susite.StablerCharacter.GameScene;
import tech.fastj.graphics.game.Sprite2D;
import tech.fastj.math.Pointf;
import tech.fastj.resources.images.ImageResource;

public class SpriteEvent implements Event {
	Path imagePath;
	Pointf position;
	
	public SpriteEvent(Path newImagePath) {
		imagePath = newImagePath;
	}
	
	public SpriteEvent(Path newImagePath, Pointf newPosition) {
		imagePath = newImagePath;
		position = newPosition;
	}

	@Override
	public void onActionTriggered() {
		Sprite2D sprite = Sprite2D.create(new ImageResource(imagePath)).build();
		if(position != null) sprite.translate(position);
		else {
			Pointf screenCenter = GameScene.screenCenter.copy();
			Pointf[] bounds = sprite.getBounds();
			screenCenter.x -= (bounds[1].x - bounds[0].x) / 2;
			sprite.setTranslation(screenCenter);
		}
		GameScene.drawableManagerInstance.addGameObject(sprite);
	}

}
