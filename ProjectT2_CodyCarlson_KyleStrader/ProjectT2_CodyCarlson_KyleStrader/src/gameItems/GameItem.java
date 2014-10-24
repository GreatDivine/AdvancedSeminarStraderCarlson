package gameItems;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public abstract class GameItem 
{
	
	protected Point2D.Float mPosition;
	protected Point2D.Float mDimensions;
	
	protected GameItem()
	{
		mPosition = new Point2D.Float(0, 0);
		mDimensions = new Point2D.Float(0, 0);
	}
	
	protected GameItem(int x, int y, int w, int h)
	{
		mPosition = new Point2D.Float(x, y);
		mDimensions = new Point2D.Float(w, h);
	}
	
	public Point2D.Float getPosition()
	{
		return mPosition;
	}
	
	public Point2D.Float getDimensions()
	{
		return mDimensions;
	}
	
	public Point2D.Float getOrigin()
	{
		Point2D.Float origin = new Point2D.Float((float)(mPosition.getX() + (mDimensions.getX() / 2)), 
				(float)(mPosition.getY() + (mDimensions.getY() / 2)));
		return origin;
	}
	
	public abstract void update(long timeNs);
	
	public abstract void paint(Graphics g);
}
