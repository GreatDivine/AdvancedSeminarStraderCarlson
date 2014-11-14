package game.gameItems.snake;

import game.gameItems.GameItem;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SnakeBodyPart implements GameItem {
	
	BufferedImage mSprite;
	Point2D.Float mPos;
	float mSpeed;
	
	public SnakeBodyPart(Point2D.Float pos, float speed, BufferedImage sprite)
	{
		mPos = pos;
		mSpeed = speed;
		mSprite = sprite;
	}

	@Override
	public void paint(Graphics g) 
	{
		g.drawImage(mSprite, (int)mPos.getX(), (int)mPos.getY(), null);
	}

	@Override
	public void update() 
	{
		
	}

}
