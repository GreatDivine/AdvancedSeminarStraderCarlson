package tiles;

import java.awt.Color;
import java.awt.Graphics;

import util.GameSettings;

public class Tile {
	
	protected int mPosX;
	protected int mPosY;
	protected int mWidth;
	protected int mHeight;
	
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
	
	public int getXPos()
	{
		return mPosX;
	}
	
	public int getYPos()
	{
		return mPosY;
	}
}
