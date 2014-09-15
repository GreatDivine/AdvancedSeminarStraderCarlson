package gameItems;

import graphics.GraphicsWindow;

import java.awt.geom.Point2D;

public class GameItem {
	
	private Point2D mPos;
	
	
	GameItem()
	{
		mPos.setLocation(0,0);
	}
	
	GameItem(double x, double y)
	{
		mPos.setLocation(x, y);
	}
	
	GameItem(Point2D pos)
	{
		mPos.setLocation(pos);
	}
	
	public Point2D getPos()
	{
		return mPos;
	}
	
	public void update(long timeNs)
	{
		
	}
	
	public void draw(GraphicsWindow g)
	{
		
	}

}
