package game.gameItems.food;

import java.awt.Graphics;

import game.gameItems.GameItem;

public abstract class Food implements GameItem {
	
	protected int mTileX;
	protected int mTileY;
	
	public enum FoodType
	{
		REGULAR,
		POISON
	}
	
	public Food(int tileX, int tileY)
	{
		mTileX = tileX;
		mTileY = tileY;
	}
	
	public abstract int processEaten();

	@Override
	public void paint(Graphics g) 
	{
	}

	@Override
	public void update() 
	{	
	}

}
