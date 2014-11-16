package game.gameItems.snake;

import game.util.GameSettings;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
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

	public SnakeHead(int tileX, int tileY, BufferedImage sprite)
	{
		super(tileX, tileY, sprite);
		
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
			mTileX++;
			break;
		case LEFT:
			mTileX--;		
			break;
		case UP:
			mTileY--;
			break;
		case DOWN:
			mTileY++;
			break;
		}
		
		mPosXPix = mTileX * GameSettings.TILE_SIZE;		
		mPosYPix = mTileY * GameSettings.TILE_SIZE;
	}

}
