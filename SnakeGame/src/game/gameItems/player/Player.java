package game.gameItems.player;

import java.awt.Graphics;
import java.util.ArrayList;

import game.gameItems.GameItem;
import game.gameItems.level.Level;
import game.gameItems.snake.Snake;
import game.util.GameSettings;
import game.util.Observable;
import game.util.Observer;
import game.util.messages.LevelChangeMessage;
import game.util.messages.Message.MessageType;
import game.util.messages.ScoreMessage;

public class Player implements Observable, GameItem {
	
	private int mScore;
	private int mLevel;
	private int mHighScore;
	private boolean mIsAlive;
	private Snake mSnake;
	
	private ArrayList<Observer> mObservers;
	
	public Player(Level level, int highScore)
	{
		mHighScore = highScore; 
		mObservers = new ArrayList<Observer>();
		mScore = GameSettings.PLAYER_SCORE_DEFAULT;
		mLevel = GameSettings.LEVEL_DEFAULT;
		
		mSnake = new Snake(1, 1, 500, level, this);
		mIsAlive = mSnake.isAlive();
	}
	
	public Player(int x, int y, Level level, int highScore)
	{
		mHighScore = highScore; 
		mObservers = new ArrayList<Observer>();
		mScore = GameSettings.PLAYER_SCORE_DEFAULT;
		mLevel = GameSettings.LEVEL_DEFAULT;
		
		mSnake = new Snake(x, y, 500, level, this);
		mIsAlive = mSnake.isAlive();
	}
	
	public boolean isAlive()
	{
		return mIsAlive;
	}
	
	public Snake getSnake()
	{
		return mSnake;
	}
	
	public int getScore()
	{
		return mScore;
	}
	
	public int getLevel()
	{
		return mLevel;
	}
	
	public int getHighScore()
	{
		return mHighScore;
	}
	
	public void modifyScore(int amount)
	{
		mScore += amount;
		sendMessage(MessageType.SCORE_MESSAGE);
	}
	
	public void increaseLevel()
	{
		mLevel++;
		sendMessage(MessageType.LEVEL_CHANGE_MESSAGE);
	}
	
	public void addObserver(Observer o)
	{
		mObservers.add(o);
	}
	
	public void setSnakeDirection(GameSettings.SnakeDirection dir)
	{
		mSnake.setSnakeDirection(dir);
	}

	@Override
	public void sendMessage(MessageType type) 
	{
		if (type == MessageType.SCORE_MESSAGE)
		{
			for (Observer o:mObservers)
			{
				ScoreMessage msg = new ScoreMessage(mScore);
				o.processMessage(msg);
			}
		}
		else if (type == MessageType.LEVEL_CHANGE_MESSAGE)
		{
			for (Observer o:mObservers)
			{
				LevelChangeMessage msg = new LevelChangeMessage(mLevel);
				o.processMessage(msg);
			}
		}
	}

	@Override
	public void paint(Graphics g) 
	{
		mSnake.paint(g);
		mIsAlive = mSnake.isAlive();
	}

	@Override
	public void update() 
	{
		mSnake.update();
		
		if (mScore >= GameSettings.POINTS_FOR_LEVEL * mLevel)
		{
			increaseLevel();
		}
	}

}
