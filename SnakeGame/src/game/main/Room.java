package game.main;

import game.gameItems.GameItem;
import game.gameItems.food.Food.FoodType;
import game.gameItems.hud.HUD;
import game.gameItems.level.Level;
import game.gameItems.player.Player;
import game.util.GameSettings;
import game.util.momento.*;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Room extends JPanel implements KeyListener {
	
	private ArrayList<GameItem> mObjects;
	private Level mLevel;
	private Player mPlayer;
	private HUD mHUD;
	
	private CareTaker mCareTaker;
	private Originator mOriginator;
	
	public Room()
	{
		initialize();
		addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void initialize()
	{
		mObjects = new ArrayList<GameItem>();
		
		mLevel = new Level();
		addObject(mLevel);
		
		mCareTaker = new CareTaker(GameSettings.GAME_SAVE_FILE, false);
		
		try {
			mCareTaker.load(GameSettings.GAME_SAVE_FILE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		mOriginator = new Originator();
		
		mPlayer = new Player(mLevel, mCareTaker.getCurMomento().getStateInt());
		addObject(mPlayer);
		
		mHUD = new HUD(mCareTaker.getCurMomento().getStateInt());
		addObject(mHUD);
		
		mPlayer.addObserver(mHUD);
		
		mLevel.spawnFoodOnTile(2, 2, FoodType.REGULAR);
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
		
		if (!mPlayer.isAlive())
		{
			if (mPlayer.getScore() > mCareTaker.getCurMomento().getStateInt())
			{
				mOriginator.setState(String.valueOf(mPlayer.getScore()));
				mCareTaker.add(mOriginator.saveStateToMomento());
				try {
					mCareTaker.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			initialize();
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