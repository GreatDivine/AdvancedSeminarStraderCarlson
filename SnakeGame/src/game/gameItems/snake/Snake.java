package game.gameItems.snake;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import game.gameItems.GameItem;
import game.gameItems.food.Food.FoodType;
import game.gameItems.level.Level;
import game.gameItems.level.Tile;
import game.gameItems.player.Player;
import game.util.GameSettings;
import game.util.GameSettings.SnakeDirection;

public class Snake implements GameItem {
	
	private ArrayList<SnakeBodyPart> mSnakeBody;
	
	private Player mParent;
	
	private Level mLevel;
	private boolean mIsAlive;
	
	//update time variables
	private int mSnakeUpdateSpeed;
	private float mUpdateStartTime;
	private float mUpdateCurTime;
	private float mUpdateTimePassed;
	private boolean newCycle;
	
	public Snake(int tileX, int tileY, int speed, Level level)
	{
		mIsAlive = true;
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		
		mSnakeBody.add(new SnakeHead(tileX, tileY, mSnakeBody));
		
		mLevel = level;
		
		mSnakeUpdateSpeed = speed;
		mUpdateStartTime = 0;
		mUpdateCurTime = 0;
		mUpdateTimePassed = 0;
		newCycle = true;
	}
	
	public Snake(int tileX, int tileY, int speed, Level level, Player playerParent)
	{
		mIsAlive = true;
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		
		mSnakeBody.add(new SnakeHead(tileX, tileY, mSnakeBody));
		
		mLevel = level;
		
		mSnakeUpdateSpeed = speed;
		mUpdateStartTime = 0;
		mUpdateCurTime = 0;
		mUpdateTimePassed = 0;
		mParent = playerParent;
		newCycle = true;
	}
	
	public boolean isAlive()
	{
		return mIsAlive;
	}
	
	public SnakeHead getHead()
	{
		return (SnakeHead)mSnakeBody.get(0);
	}
	
	public ArrayList<SnakeBodyPart> getSnakeBody()
	{
		return mSnakeBody;
	}
	
	public int getSnakeSize()
	{
		return mSnakeBody.size();
	}
	
	public void modifyScore(int amount)
	{
		mParent.modifyScore(amount);
	}
	
	public void setSnakeDirection(GameSettings.SnakeDirection dir)
	{
		((SnakeHead) mSnakeBody.get(0)).setDirection(dir);
	}
	
	public SnakeDirection getSnakeDirection()
	{
		return getHead().getDirection();
	}

	@Override
	public void paint(Graphics g) 
	{
		for (SnakeBodyPart p:mSnakeBody)
		{
			p.paint(g);
		}
	}
	
	public void addSnakePart()
	{
		int posX = mSnakeBody.get(mSnakeBody.size() - 1).getTileX(); 
		int posY = mSnakeBody.get(mSnakeBody.size() - 1).getTileY();  
		mSnakeBody.add(SnakePartFactory.createBodyPart(posX, posY, mSnakeBody));
		mSnakeUpdateSpeed = (int)(mSnakeUpdateSpeed * GameSettings.SPEED_INCREASE_FRACTION);
	}
	
	public void processFood(int headTileX, int headTileY)
	{
		int pointsChange = mLevel.getTile(headTileX, headTileY).consumeFood();
		
		if (pointsChange > 0)
		{
			addSnakePart();
		}
		else if (pointsChange < 0)
		{
			addSnakePart();
			addSnakePart();
		}
		
		modifyScore(pointsChange);
		
		mLevel.generateNewFood(mParent.getLevel());
	}
	
	public boolean checkBodyCollision(int tileX, int tileY)
	{
		for (SnakeBodyPart p:mSnakeBody)
		{
			if (tileX == p.getTileX() 
					&& tileY == p.getTileY()
					&& p.getBodyIndex() != 0)
				return true;
		}
		return false;
	}
	
	public boolean checkOutOfBounds(int tileX, int tileY)
	{
		if (tileX >= GameSettings.GRID_SIZE 
				|| tileX < 0 
				|| tileY >= GameSettings.GRID_SIZE 
				|| tileY < 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void update() 
	{
		if (mIsAlive)
		{
			if (newCycle)
			{
				mUpdateStartTime = (float)(System.nanoTime() / GameSettings.NANOSECONDS_TO_MILLISECONDS);
				newCycle = false;
				for (SnakeBodyPart p:mSnakeBody)
				{
					p.update(); 
				}
				
				int headTileX = mSnakeBody.get(0).getTileX();
				int headTileY = mSnakeBody.get(0).getTileY();
				
				if (checkOutOfBounds(headTileX, headTileY) == true || checkBodyCollision(headTileX, headTileY) == true)
				{
					mIsAlive = false;
				}
				
				else
				{
					if (mLevel.getTile(headTileX, headTileY).hasFood())
					{
						processFood(headTileX, headTileY);
					}
				}
			}
			
			else
			{
				mUpdateCurTime = (float)(System.nanoTime() / GameSettings.NANOSECONDS_TO_MILLISECONDS);
				mUpdateTimePassed = mUpdateCurTime - mUpdateStartTime;
				
				if(mUpdateTimePassed >= mSnakeUpdateSpeed)
				{
					mUpdateStartTime = 0;
					newCycle = true;
				}
			}
		}
	}
}
