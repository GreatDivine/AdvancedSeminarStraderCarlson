package tiles;

import java.awt.Color;
import java.awt.Graphics;

import util.GameSettings;

public class Tile {
	
	private int mPosX;
	private int mPosY;
	private int mWidth;
	private int mHeight;
	
	private boolean mIsPath;
	
	public Tile(int x, int y)
	{
		mPosX = x;
		mPosY = y;
		
		mWidth = GameSettings.TILE_SIZE;
		mHeight = GameSettings.TILE_SIZE;
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(mPosX, mPosY, mWidth, mHeight);
	}
}
