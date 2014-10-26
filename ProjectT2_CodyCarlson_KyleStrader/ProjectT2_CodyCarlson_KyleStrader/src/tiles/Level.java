package tiles;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import util.GameSettings;


@SuppressWarnings("serial")
public class Level extends JPanel{
	
	private List<Tile> mTiles;
	private List<Tile> mTilePath;
	private List<Point2D.Float> mTilePathCoords;
	
	public Level()
	{
		mTiles = new ArrayList<Tile>();
		mTilePath = new ArrayList<Tile>();
		mTilePathCoords = new ArrayList<Point2D.Float>();
		initTiles();
	}
	
	private void initTiles()
	{
		mTilePathCoords.add(new Point2D.Float(0, 7));
		mTilePathCoords.add(new Point2D.Float(1, 7));
		mTilePathCoords.add(new Point2D.Float(2, 7));
		mTilePathCoords.add(new Point2D.Float(2, 6));
		mTilePathCoords.add(new Point2D.Float(2, 5));
		mTilePathCoords.add(new Point2D.Float(2, 4));
		mTilePathCoords.add(new Point2D.Float(2, 3));
		mTilePathCoords.add(new Point2D.Float(3, 3));
		mTilePathCoords.add(new Point2D.Float(4, 3));
		mTilePathCoords.add(new Point2D.Float(4, 4));
		mTilePathCoords.add(new Point2D.Float(4, 5));
		mTilePathCoords.add(new Point2D.Float(5, 5));
		mTilePathCoords.add(new Point2D.Float(6, 5));
		mTilePathCoords.add(new Point2D.Float(7, 5));
		mTilePathCoords.add(new Point2D.Float(8, 5));
		mTilePathCoords.add(new Point2D.Float(9, 5));
		mTilePathCoords.add(new Point2D.Float(9, 6));
		mTilePathCoords.add(new Point2D.Float(9, 7));
		mTilePathCoords.add(new Point2D.Float(10, 7));
		mTilePathCoords.add(new Point2D.Float(11, 7));
		mTilePathCoords.add(new Point2D.Float(11, 6));
		mTilePathCoords.add(new Point2D.Float(11, 5));
		mTilePathCoords.add(new Point2D.Float(11, 4));
		mTilePathCoords.add(new Point2D.Float(11, 3));
		mTilePathCoords.add(new Point2D.Float(12, 3));
		mTilePathCoords.add(new Point2D.Float(13, 3));
		mTilePathCoords.add(new Point2D.Float(14, 3));
		mTilePathCoords.add(new Point2D.Float(15, 3));
		mTilePathCoords.add(new Point2D.Float(16, 3));
		
		for (int i = 0; i < mTilePathCoords.size(); i++)
		{
			mTilePath.add(new Tile(0,0,false));
		}
		
		for (int i = 0; i < 17; i++)
		{
			for (int j = 0; j < 17; j++)
			{
				boolean ispath = false;
				int index = 0;
				
				//for (Point2D.Float p:mTilePathCoords)
				for (int k = 0; k < mTilePathCoords.size(); k++)
				{
					Point2D.Float p = mTilePathCoords.get(k);
					if(j == p.getX() && i == p.getY())
					{
						ispath = true;
						index = k;
						break;
					}
				}
				
				if (ispath)
				{
					Tile tmp = new Tile(j * GameSettings.TILE_SIZE, i * GameSettings.TILE_SIZE, ispath);
					mTilePath.set(index, tmp);
					this.add(tmp);
				}
				
				else
				{
					Tile tmp = new Tile(j * GameSettings.TILE_SIZE, i * GameSettings.TILE_SIZE, ispath);
					mTiles.add(tmp);
					this.add(tmp);
				}
			}
		}
	}
	
	public void checkTileClick(int x, int y)
	{
		for(Tile t: mTiles)
		{
			if (t.containsPoint(x, y))
			{
				t.buttonClicked();
			}
		}
	}
	
	public List<Tile> getTiles()
	{
		return mTiles;
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
