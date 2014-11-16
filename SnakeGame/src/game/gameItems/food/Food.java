package game.gameItems.food;

import java.awt.Color;
import java.awt.Graphics;

import game.gameItems.GameItem;
import game.util.GameSettings;

public class Food implements GameItem {
	
	int mTileX;
	int mTileY;
	
	public Food(int tileX, int tileY)
	{
		mTileX = tileX;
		mTileY = tileY;
	}

	@Override
	public void paint(Graphics g) 
	{
		int posX = mTileX * GameSettings.TILE_SIZE;
		int posY = mTileY * GameSettings.TILE_SIZE;
		g.setColor(Color.green);
		g.fillOval(posX + (GameSettings.TILE_SIZE / 4), posY+ (GameSettings.TILE_SIZE / 4), GameSettings.FOOD_SIZE, GameSettings.FOOD_SIZE);
	}

	@Override
	public void update() 
	{
		
	}

}
