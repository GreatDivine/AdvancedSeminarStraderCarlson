package gameItems;

import java.awt.Graphics;

public abstract class GameItem 
{
	
	protected float mPosX;
	protected float mPosY;
	protected int mWidth;
	protected int mHeight;
	
	protected GameItem()
	{
		mPosX = 0;
		mPosY = 0;
		mWidth = 0;
		mHeight = 0;
	}
	
	protected GameItem(int x, int y, int w, int h)
	{
		mPosX = x;
		mPosY = y;
		mWidth = w;
		mHeight = h;
	}
	
	public float getX()
	{
		return mPosX;
	}
	
	public float getY()
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
	
	public abstract void update(long timeNs);
	
	public abstract void paint(Graphics g);
}
