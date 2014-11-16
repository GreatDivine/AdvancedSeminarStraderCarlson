package game.gameItems.snake;

import java.awt.image.BufferedImage;

public class SnakePartFactory {
	
	public static SnakeBodyPart createBodyPart(int tileX, int tileY, BufferedImage sprite)
	{
		SnakeBodyPart part = new SnakeBodyPart(tileX, tileY, 0, sprite);
		
		return part;
	}

}
