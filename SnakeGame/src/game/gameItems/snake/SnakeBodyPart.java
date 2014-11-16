package game.gameItems.snake;

import game.gameItems.GameItem;
import game.util.GameSettings;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class SnakeBodyPart implements GameItem {
	
	BufferedImage mSprite;
	Point2D.Float mPos;
	float mSpeed;
	
	int mTileX;
	int mTileY;
	
	public SnakeBodyPart(int tileX, int tileY, float speed, BufferedImage sprite)
	{
		mTileX = tileX;
		mTileY = tileY;
		mSpeed = speed;
		mSprite = sprite;
	}
	
	public SnakeBodyPart(Point2D.Float pos, float speed, BufferedImage sprite)
	{
		mPos = pos;
		mSpeed = speed;
		mSprite = sprite;
	}

	@Override
	public void paint(Graphics g) 
	{
		//g.drawImage(mSprite, (int)mPos.getX(), (int)mPos.getY(), null);
		g.setColor(Color.orange);
		g.fillOval((int)mPos.getX(), (int)mPos.getY(), GameSettings.TILE_SIZE - 2, GameSettings.TILE_SIZE - 2);
	}

	@Override
	public void update() 
	{
		
	}

}
