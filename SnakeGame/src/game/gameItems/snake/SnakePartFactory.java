package game.gameItems.snake;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SnakePartFactory {
	
	public static SnakeBodyPart createBodyPart(int tileX, int tileY, ArrayList<SnakeBodyPart> snakeBody, BufferedImage sprite)
	{
		SnakeBodyPart part = new SnakeBodyPart(tileX, tileY, snakeBody, sprite);
		
		return part;
	}

}
