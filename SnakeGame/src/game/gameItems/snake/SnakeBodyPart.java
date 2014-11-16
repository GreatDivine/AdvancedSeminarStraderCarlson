package game.gameItems.snake;

import game.gameItems.GameItem;
import game.util.GameSettings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SnakeBodyPart implements GameItem {
	
	BufferedImage mSprite;
	
	int mTileX;
	int mTileY;
	int mPosXPix;
	int mPosYPix;
	
	public SnakeBodyPart(int tileX, int tileY, BufferedImage sprite)
	{
		mTileX = tileX;
		mTileY = tileY;
		mPosXPix = tileX * GameSettings.TILE_SIZE;
		mPosYPix = tileY * GameSettings.TILE_SIZE;
		mSprite = sprite;
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
		
	}

}
