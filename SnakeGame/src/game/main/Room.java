package game.main;

import game.gameItems.GameItem;
import game.gameItems.food.FoodFactory;
import game.gameItems.level.Level;
import game.gameItems.snake.Snake;
import game.util.GameSettings;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Room extends JPanel implements KeyListener {
	
	private ArrayList<GameItem> mObjects;
	private Level mLevel;
	
	private Snake testSnake;
	
	public Room()
	{
		mObjects = new ArrayList<GameItem>();
		
		mLevel = new Level();
		addObject(mLevel);
		
		testSnake = new Snake(1, 1, 500);
		addObject(testSnake);
		
		spawnFoodOnTile(5, 5);
			
		addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void spawnFoodOnTile(int tileX, int tileY)
	{
		addObject(FoodFactory.createFood(tileX, tileY));
		mLevel.spawnFoodOnTile(tileX, tileY);
		
		/* Testing */
		//System.out.println("Spawned food on tile: " + tileX + ", " + tileY);
		/*---------*/
	}
	
	public void addObject(GameItem obj)
	{
		mObjects.add(obj);
	}
	
	public void update()
	{
		for (GameItem o:mObjects)
		{
			o.update();
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		for (GameItem o:mObjects)
		{
			o.paint(g);
		}
	}
	
	public void changeSnakeDirection(int keyCode)
	{
		switch(keyCode)
		{
		case KeyEvent.VK_UP:
			testSnake.setSnakeDirection(GameSettings.SnakeDirection.UP);
			break;
		case KeyEvent.VK_DOWN:
			testSnake.setSnakeDirection(GameSettings.SnakeDirection.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			testSnake.setSnakeDirection(GameSettings.SnakeDirection.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			testSnake.setSnakeDirection(GameSettings.SnakeDirection.RIGHT);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keyCode = e.getKeyCode();
		changeSnakeDirection(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{

	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

}
