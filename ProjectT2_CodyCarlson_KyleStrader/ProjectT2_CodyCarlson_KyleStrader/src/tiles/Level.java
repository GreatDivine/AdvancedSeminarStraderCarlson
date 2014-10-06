package tiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.GameSettings;


public class Level {
	
	private List<Tile> mTiles;
	
	public Level()
	{
		mTiles = new ArrayList<Tile>();
		initTiles();
	}
	
	private void initTiles()
	{
		for (int i = 0; i < 16; i++)
		{
			for (int j = 0; j < 16; j++)
			{
				mTiles.add(new Tile(j * GameSettings.TILE_SIZE, i * GameSettings.TILE_SIZE));
			}
		}
	}

	public void paint(Graphics g)
	{
		for(Tile t:mTiles)
		{
			t.paint(g);
		}
	}
}
