package game.gameItems.snake;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.gameItems.GameItem;
import game.gameItems.level.Level;
import game.gameItems.level.Tile;
import game.gameItems.player.Player;
import game.util.GameSettings;

public class Snake implements GameItem {
	
	private ArrayList<SnakeBodyPart> mSnakeBody;
	
	private BufferedImage mHeadSprite;
	private BufferedImage mBodySprite;
	
	private Player mParent;
	
	Level mLevel;
	boolean mIsAlive;
	
	//update time variables
	int mSnakeUpdateSpeed;
	float mUpdateStartTime;
	float mUpdateCurTime;
	float mUpdateTimePassed;
	boolean newCycle;
	
	public Snake(int tileX, int tileY, int speed, Level level)
	{
		mIsAlive = true;
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		//loadSprites();
		
		mSnakeBody.add(new SnakeHead(tileX, tileY, mSnakeBody,null));
		
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
		//loadSprites();
		
		mSnakeBody.add(new SnakeHead(tileX, tileY, mSnakeBody,null));
		
		mLevel = level;
		
		mSnakeUpdateSpeed = speed;
		mUpdateStartTime = 0;
		mUpdateCurTime = 0;
		mUpdateTimePassed = 0;
		mParent = playerParent;
		newCycle = true;
	}
	
	public void increaseScore(int amount)
	{
		mParent.increaseScore(amount);
	}
	
	public void loadSprites()
	{
		try 
		{
			mHeadSprite = ImageIO.read(new File("E:/tmp/SnakeGame/src/assets/narwhal.png"));
			mBodySprite = ImageIO.read(new File("E:/tmp/SnakeGame/src/assets/narwhal.png"));
		}
		catch (IOException e)
		{
			System.out.println("Error loading snake sprites!");
		}
	}
	
	public void setSnakeDirection(GameSettings.SnakeDirection dir)
	{
		SnakeHead head = (SnakeHead)mSnakeBody.get(0);
		head.setDirection(dir);
	}

	@Override
	public void paint(Graphics g) 
	{
		for (SnakeBodyPart p:mSnakeBody)
		{
			p.paint(g);
		}
	}
	
	public void addSnakePart(int headTileX, int headTileY)
	{
		int posX = mSnakeBody.get(mSnakeBody.size() - 1).getTileX(); 
		int posY = mSnakeBody.get(mSnakeBody.size() - 1).getTileY();  
		mSnakeBody.add(new SnakeBodyPart(posX, posY, mSnakeBody, null));
		mSnakeUpdateSpeed = (int)(mSnakeUpdateSpeed * GameSettings.SPEED_INCREASE_FRACTION);
		mLevel.getTile(headTileX, headTileY).removeFood();
		
		increaseScore(GameSettings.FOOD_SCORE_DEFAULT);
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
				
				if (headTileX >= GameSettings.GRID_SIZE || headTileX < 0 || headTileY >= GameSettings.GRID_SIZE || headTileY < 0)
				{
					mIsAlive = false;
				}
				
				else
				{
					if (mLevel.getTile(headTileX, headTileY).hasFood())
					{
						addSnakePart(headTileX, headTileY);
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
