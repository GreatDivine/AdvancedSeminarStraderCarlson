package game.gameItems.snake;

import game.gameItems.GameItem;
import game.util.GameSettings;
import game.util.GameSettings.SnakeDirection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SnakeBodyPart implements GameItem {
	
	BufferedImage mSprite;
	
	int mTileX;
	int mTileY;
	int mPosXPix;
	int mPosYPix;
	
	int mBodyIndex;
	ArrayList<SnakeBodyPart> mSnakeBody;
	
	SnakeDirection mDir;
	SnakeDirection mPrevDir;
	
	public SnakeBodyPart(int tileX, int tileY, ArrayList<SnakeBodyPart> snakeBody, BufferedImage sprite)
	{
		mTileX = tileX;
		mTileY = tileY;
		mPosXPix = tileX * GameSettings.TILE_SIZE;
		mPosYPix = tileY * GameSettings.TILE_SIZE;
		mSprite = sprite;
		
		mBodyIndex = snakeBody.size();
		mSnakeBody = snakeBody;
		
		mDir = GameSettings.SnakeDirection.NONE;
	}
	
	public SnakeDirection getPrevDirection()
	{
		return mPrevDir;
	}
	
	public int getTileX()
	{
		return mTileX;
	}
	
	public int getTileY()
	{
		return mTileY;
	}

	@Override
	public void paint(Graphics g) 
	{
		//g.drawImage(mSprite, (int)mPos.getX(), (int)mPos.getY(), null);
		g.setColor(Color.orange);
		g.fillRect(mPosXPix, mPosYPix, GameSettings.TILE_SIZE - 2, GameSettings.TILE_SIZE - 2);
	}

	@Override
	public void update() 
	{
		move();
		mPrevDir = mDir;
		mDir = mSnakeBody.get(mBodyIndex - 1).getPrevDirection();
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
