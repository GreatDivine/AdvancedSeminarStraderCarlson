package game.gameItems.snake;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import game.util.GameSettings.SnakeDirection;;

/*
 * SnakeHead.java
 * 
 * This class will be the gameobject representing the player controlled Snake Head. It will be
 * the snake piece that moves and changes directions, the rest of the body pieces will simply
 * follow the head.
 * 
 * Cody Carlson - Nov 13, 2014
 */

public class SnakeHead extends SnakeBodyPart {
	
	SnakeDirection mDir;
	
	public SnakeHead(Point2D.Float pos, float speed, BufferedImage sprite)
	{
		super(pos, speed, sprite);
		
		mDir = SnakeDirection.RIGHT;
	}
	
	public void setDirection(SnakeDirection newDir)
	{
		switch(newDir)
		{
		case RIGHT:
			if (mDir != SnakeDirection.LEFT) mDir = newDir;
			break;
		case LEFT:
			if (mDir != SnakeDirection.RIGHT) mDir = newDir;
			break;
		case UP:
			if (mDir != SnakeDirection.DOWN) mDir = newDir;
			break;
		case DOWN:
			if (mDir != SnakeDirection.UP) mDir = newDir;
			break;
		}
	}
	
	public SnakeDirection getDirection()
	{
		return mDir;
	}

	@Override
	public void update() 
	{
		move();
	}
	
	public void move()
	{
		switch(mDir)
		{
		case RIGHT:
			mPos.setLocation(new Point2D.Float((float)mPos.getX() + mSpeed, (float)mPos.getY()));
			break;
		case LEFT:
			mPos.setLocation(new Point2D.Float((float)mPos.getX() - mSpeed, (float)mPos.getY()));
			break;
		case UP:
			mPos.setLocation(new Point2D.Float((float)mPos.getX(), (float)mPos.getY() - mSpeed));
			break;
		case DOWN:
			mPos.setLocation(new Point2D.Float((float)mPos.getX(), (float)mPos.getY() + mSpeed));
			break;
		}
	}

}
