package tiles;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.GameSettings;


public class Level {
	
	private List<Tile> mTiles;
	private List<Tile> mTilePath;
	
	public Level()
	{
		mTiles = new ArrayList<Tile>();
		mTilePath = new ArrayList<Tile>();
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

		mTilePath.add(new PathTile(-1 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(0 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(1 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(2 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(2 * GameSettings.TILE_SIZE, 6 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(2 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(2 * GameSettings.TILE_SIZE, 4 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(2 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(3 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(4 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(4 * GameSettings.TILE_SIZE, 4 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(4 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(5 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(6 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(7 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(8 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(9 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(9 * GameSettings.TILE_SIZE, 6 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(9 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(10 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(11 * GameSettings.TILE_SIZE, 7 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(11 * GameSettings.TILE_SIZE, 6 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(11 * GameSettings.TILE_SIZE, 5 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(11 * GameSettings.TILE_SIZE, 4 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(11 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(12 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(13 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(14 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
		mTilePath.add(new PathTile(15 * GameSettings.TILE_SIZE, 3 * GameSettings.TILE_SIZE));
	}

	public void paint(Graphics g)
	{	
		for(Tile t:mTilePath)
		{
			t.paint(g);
		}
		for(Tile t:mTiles)
		{
			t.paint(g);
		}
	}
	
	public Tile getPathIndexed(int index)
	{
		return mTilePath.get(index);
	}
	
	public int getPathLength()
	{
		return mTilePath.size();
	}
}
