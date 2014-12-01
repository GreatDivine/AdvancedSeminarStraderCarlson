package game.gameItems.food;

import game.util.GameSettings;

import java.awt.Color;
import java.awt.Graphics;

public class PoisonFood extends Food {
	
	public PoisonFood(int tileX, int tileY) 
	{
		super(tileX, tileY);
	}

	@Override
	public int processEaten() 
	{
		int pointsLost = GameSettings.FOOD_SCORE_DEFAULT * -1;
		return pointsLost;
	}
	
	@Override
	public void paint(Graphics g)
	{
		int posX = mTileX * GameSettings.TILE_SIZE;
		int posY = mTileY * GameSettings.TILE_SIZE;
		g.setColor(Color.red);
		g.fillOval(posX + (GameSettings.TILE_SIZE / 4), posY+ (GameSettings.TILE_SIZE / 4), GameSettings.FOOD_SIZE, GameSettings.FOOD_SIZE);
	}

}
