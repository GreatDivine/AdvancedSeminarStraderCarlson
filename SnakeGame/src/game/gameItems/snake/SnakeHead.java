package game.gameItems.snake;

import game.util.GameSettings;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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

	private int mX_Vel;
	private int mY_Vel;

	public SnakeHead(int tileX, int tileY, ArrayList<SnakeBodyPart> snakeBody)
	{
		super(tileX, tileY, snakeBody);
		
		mDir = SnakeDirection.RIGHT;
	}
	
	public void setDirection(SnakeDirection newDir)
	{
		switch(newDir)
		{
		case RIGHT:
			if (mX_Vel != -1) mDir = newDir;
			break;
		case LEFT:
			if (mX_Vel != 1) mDir = newDir;
			break;
		case UP:
			if (mY_Vel != 1) mDir = newDir;
			break;
		case DOWN:
			if (mY_Vel != -1) mDir = newDir;
			break;
		case NONE:
			mY_Vel = 0;
			mX_Vel = 0;
			mDir = newDir;
			break;
		}
	}

	@Override
	public void update() 
	{
		move();	
		mPrevDir = mDir;
	}
	
	public void move()
	{
		switch(mDir)
		{
		case RIGHT:
			mTileX++;
			mX_Vel = 1;
			mY_Vel = 0;
			break;
		case LEFT:
			mTileX--;
			mX_Vel = -1;	
			mY_Vel = 0;	
			break;
		case UP:
			mTileY--;
			mY_Vel = -1;
			mX_Vel = 0;
			break;
		case DOWN:
			mTileY++;
			mY_Vel = 1;
			mX_Vel = 0;
			break;
		case NONE:
			mY_Vel = 0;
			mX_Vel = 0;
			break;
		}
		
		mPosXPix = mTileX * GameSettings.TILE_SIZE;		
		mPosYPix = mTileY * GameSettings.TILE_SIZE;
	}

}
