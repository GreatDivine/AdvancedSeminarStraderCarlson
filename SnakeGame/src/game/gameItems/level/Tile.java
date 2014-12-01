package game.gameItems.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import game.gameItems.GameItem;
import game.gameItems.food.Food;
import game.gameItems.food.FoodFactory;
import game.gameItems.food.Food.FoodType;
import game.util.GameSettings;

public class Tile implements GameItem {
	
	int mIndexX;
	int mIndexY;
	int mPosX;
	int mPosY;
	
	boolean mHasFood;
	Food mFood;
	
	public Tile()
	{
		mIndexX = 0;
		mIndexY = 0;
		
		mPosX = mIndexX * GameSettings.TILE_SIZE;
		mPosY = mIndexY * GameSettings.TILE_SIZE;
		
		mHasFood = false;
		mFood = null;
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
	
	public void addFood(FoodType type)
	{
		mHasFood = true;
		
		switch(type)
		{
		case REGULAR:
			mFood = FoodFactory.createRegularFood(mIndexX, mIndexY);
			break;
		case POISON:
			mFood = FoodFactory.createPoisonFood(mIndexX, mIndexY);
			break;
		}
	}
	
	public int consumeFood()
	{
		if (mHasFood)
		{
			mHasFood = false;
			int points = mFood.processEaten();
			mFood = null;
			return points;
		}
		
		return 0;
	}
	
	public void removeFood()
	{
		if (mHasFood)
		{
			mHasFood = false;
			mFood = null;
		}
	}

	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.black);
		g.drawRect(mPosX, mPosY, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE);
		
		if (mHasFood)
		{
			mFood.paint(g);
		}
	}

	@Override
	public void update() 
	{
		
	}

}
