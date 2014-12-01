package game.gameItems.snake;

import game.gameItems.GameItem;
import game.util.GameSettings;
import game.util.GameSettings.SnakeDirection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SnakeBodyPart implements GameItem {

	int mTileX;
	int mTileY;
	int mPosXPix;
	int mPosYPix;
	
	int mBodyIndex;
	ArrayList<SnakeBodyPart> mSnakeBody;
	
	SnakeDirection mDir;
	SnakeDirection mPrevDir;
	
	public SnakeBodyPart(int tileX, int tileY, ArrayList<SnakeBodyPart> snakeBody)
	{
		mTileX = tileX;
		mTileY = tileY;
		mPosXPix = tileX * GameSettings.TILE_SIZE;
		mPosYPix = tileY * GameSettings.TILE_SIZE;

		mBodyIndex = snakeBody.size();
		mSnakeBody = snakeBody;
		
		mDir = GameSettings.SnakeDirection.NONE;
	}
	
	public SnakeDirection getDirection()
	{
		return mDir;
	}
	
	public int getBodyIndex()
	{
		return mBodyIndex;
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
		g.setColor(Color.orange);
		g.fillRect(mPosXPix + 1, mPosYPix + 1, GameSettings.TILE_SIZE - 2, GameSettings.TILE_SIZE - 2);
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
