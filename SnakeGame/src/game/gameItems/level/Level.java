package game.gameItems.level;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Random;

import game.gameItems.GameItem;
import game.gameItems.food.Food.FoodType;
import game.util.GameSettings;

public class Level implements GameItem {
	
	private Tile[][] mTiles = new Tile[GameSettings.GRID_SIZE][GameSettings.GRID_SIZE];
	
	public Level()
	{
		initTiles();
	}
	
	private void initTiles()
	{
		for (int i = 0; i < GameSettings.GRID_SIZE; i++)
		{
			for (int j = 0; j < GameSettings.GRID_SIZE; j++)
			{
				Tile t = new Tile(j, i);
				
				mTiles[j][i] = t;
			}
		}
	}
	
	public Tile getTile(int x, int y)
	{
		return mTiles[x][y];
	}
	
	@Override
	public void paint(Graphics g) 
	{
		for (int i = 0; i < GameSettings.GRID_SIZE; i++)
		{
			for (int j = 0; j < GameSettings.GRID_SIZE; j++)
			{
				mTiles[j][i].paint(g);
			}
		}
	}

	@Override
	public void update() 
	{
		
	}

	public boolean hasFood(int tileX, int tileY)
	{
		return mTiles[tileX][tileY].hasFood();
	}
	
	public void generateNewFood(int currentLevel)
	{
		Random rand = new Random();
		
		for (int i = 0; i < currentLevel; i++)
		{
			int x = rand.nextInt(GameSettings.GRID_SIZE);
			int y = rand.nextInt(GameSettings.GRID_SIZE);
			int t = rand.nextInt(10);
			
			
			FoodType type;
			if (t <= 3 && currentLevel != 1)
			{
				type = FoodType.POISON;
			}
			else
			{
				type = FoodType.REGULAR;
			}
			
			spawnFoodOnTile(x, y, type);
		}
	}
	
	public void spawnFoodOnTile(int tileX, int tileY, FoodType type) 
	{
		switch(type)
		{
		case REGULAR:
			mTiles[tileX][tileY].addFood(FoodType.REGULAR);
			break;
		case POISON:
			mTiles[tileX][tileY].addFood(FoodType.POISON);
			break;
		}
	}
	
	public void removeFoodOnTile(int tileX, int tileY) 
	{
		mTiles[tileX][tileY].removeFood();
	}

}
