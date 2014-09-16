package gameItems;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class GameItem {
	
	protected int mPosX;
	protected int mPosY;
	protected int mWidth;
	protected int mHeight;
	
	GameItem()
	{
		mPosX = 0;
		mPosY = 0;
		mWidth = 0;
		mHeight = 0;
	}
	
	GameItem(int x, int y, int w, int h)
	{
		mPosX = x;
		mPosY = y;
		mWidth = w;
		mHeight = h;
	}
	
	public int getX()
	{
		return mPosX;
	}
	
	public int getY()
	{
		return mPosY;
	}
	
	public int getWidth()
	{
		return mWidth;
	}
	
	public int getHeight()
	{
		return mHeight;
	}
	
	public void update(long timeNs)
	{
		
	}
	
	public void paint(Graphics g)
	{
		
	}

}
