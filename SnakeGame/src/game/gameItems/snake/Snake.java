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
	
	public Snake()
	{
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		loadSprites();
		
		mSnakeBody.add(new SnakeHead(new Point2D.Float(0,0), 1, mHeadSprite));
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
	
	public GameSettings.SnakeDirection getSnakeDirection()
	{
		SnakeHead head = (SnakeHead)mSnakeBody.get(0);
		return head.getDirection();
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
		for (SnakeBodyPart p:mSnakeBody)
		{
			p.update();
		}
	}

}
