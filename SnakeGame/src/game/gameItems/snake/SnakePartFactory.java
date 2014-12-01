package game.gameItems.snake;

import java.util.ArrayList;

public class SnakePartFactory {
	
	public static SnakeBodyPart createBodyPart(int tileX, int tileY, ArrayList<SnakeBodyPart> snakeBody)
	{
		SnakeBodyPart part = new SnakeBodyPart(tileX, tileY, snakeBody);
		
		return part;
	}

}
