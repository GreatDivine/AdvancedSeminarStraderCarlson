package game.main;

import game.gameItems.GameItem;
import game.gameItems.hud.HUD;
import game.gameItems.level.Level;
import game.gameItems.player.Player;
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
	private Player mPlayer;
	private HUD mHUD;
	
	public Room()
	{
		mObjects = new ArrayList<GameItem>();
		
		mLevel = new Level();
		addObject(mLevel);
		
		mPlayer = new Player(mLevel);
		addObject(mPlayer);
		
		mHUD = new HUD();
		addObject(mHUD);
		
		mPlayer.addObserver(mHUD);
		
		mLevel.spawnFoodOnTile(0, 0);
		mLevel.spawnFoodOnTile(1, 1);
		mLevel.spawnFoodOnTile(2, 2);
		mLevel.spawnFoodOnTile(3, 3);
		mLevel.spawnFoodOnTile(4, 4);
		mLevel.spawnFoodOnTile(5, 5);
		mLevel.spawnFoodOnTile(6, 6);
		mLevel.spawnFoodOnTile(7, 7);
		mLevel.spawnFoodOnTile(8, 8);
		mLevel.spawnFoodOnTile(9, 9);
		mLevel.spawnFoodOnTile(10, 10);
		mLevel.spawnFoodOnTile(11, 11);
		mLevel.spawnFoodOnTile(12, 12);
		mLevel.spawnFoodOnTile(13, 13);
		mLevel.spawnFoodOnTile(14, 14);
		mLevel.spawnFoodOnTile(15, 15);

		addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
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
			mPlayer.setSnakeDirection(GameSettings.SnakeDirection.UP);
			break;
		case KeyEvent.VK_DOWN:
			mPlayer.setSnakeDirection(GameSettings.SnakeDirection.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			mPlayer.setSnakeDirection(GameSettings.SnakeDirection.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			mPlayer.setSnakeDirection(GameSettings.SnakeDirection.RIGHT);
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
