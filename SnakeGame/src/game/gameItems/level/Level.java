package game.gameItems.level;

import java.awt.Graphics;

import game.gameItems.GameItem;
import game.util.GameSettings;

public class Level implements GameItem {
	
	Tile[][] mTiles = new Tile[GameSettings.GRID_SIZE][GameSettings.GRID_SIZE];
	
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
	
	public Tile[][] getTiles()
	{
		return mTiles;
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

	public void spawnFoodOnTile(int tileX, int tileY) 
	{
		mTiles[tileX][tileY].setHasFood(true);
	}
	
	public void removeFoodOnTile(int tileX, int tileY)
	{
		mTiles[tileX][tileY].setHasFood(false);
	}

}
