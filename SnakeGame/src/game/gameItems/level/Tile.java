package game.gameItems.level;

import java.awt.Color;
import java.awt.Graphics;
import game.gameItems.GameItem;
import game.util.GameSettings;

public class Tile implements GameItem {
	
	int mIndexX;
	int mIndexY;
	int mPosX;
	int mPosY;
	
	boolean mHasFood;
	
	public Tile()
	{
		mIndexX = 0;
		mIndexY = 0;
		
		mPosX = mIndexX * GameSettings.TILE_SIZE;
		mPosY = mIndexY * GameSettings.TILE_SIZE;
		
		mHasFood = false;
	}
	
	public Tile(int indexX, int indexY)
	{
		mIndexX = indexX;
		mIndexY = indexY;
		
		mPosX = mIndexX * GameSettings.TILE_SIZE;
		mPosY = mIndexY * GameSettings.TILE_SIZE;
		
		mHasFood = false;
	}
	
	public boolean hasFood()
	{
		return mHasFood;
	}
	
	public void setHasFood(boolean val)
	{
		mHasFood = val;
	}

	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.black);
		g.drawRect(mPosX, mPosY, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
		
		if (mHasFood)
		{
			g.setColor(Color.green);
			g.fillOval(mPosX + (GameSettings.TILE_SIZE / 4), mPosY + (GameSettings.TILE_SIZE / 4), GameSettings.FOOD_SIZE, GameSettings.FOOD_SIZE);
		}
	}

	@Override
	public void update() 
	{
		
	}

}
