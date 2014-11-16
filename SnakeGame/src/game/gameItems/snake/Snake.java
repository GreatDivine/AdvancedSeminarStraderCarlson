package game.gameItems.snake;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import game.gameItems.GameItem;
import game.util.GameSettings;

public class Snake implements GameItem {
	
	private ArrayList<SnakeBodyPart> mSnakeBody;
	
	private BufferedImage mHeadSprite;
	private BufferedImage mBodySprite;
	
	//update time variables
	int mSnakeUpdateSpeed;
	float mUpdateStartTime;
	float mUpdateCurTime;
	float mUpdateTimePassed;
	boolean newCycle;
	
	public Snake(int tileX, int tileY, int speed)
	{
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		//loadSprites();
		
		mSnakeBody.add(new SnakeHead(tileX, tileY, null));
		
		mSnakeUpdateSpeed = speed;
		mUpdateStartTime = 0;
		mUpdateCurTime = 0;
		mUpdateTimePassed = 0;
		newCycle = true;
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

	@Override
	public void update() 
	{
		if (newCycle)
		{
			mUpdateStartTime = (float)(System.nanoTime() / GameSettings.NANOSECONDS_TO_MILLISECONDS);
			newCycle = false;
			for (SnakeBodyPart p:mSnakeBody)
			{
				p.update();
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
