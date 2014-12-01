package game.gameItems.hud;

import java.awt.Color;
import java.awt.Graphics;

import game.gameItems.GameItem;
import game.util.GameSettings;
import game.util.Observer;
import game.util.messages.Message;
import game.util.messages.ScoreMessage;

public class HUD implements GameItem, Observer{
	
	int mScoreDisplay;
	int mLevelDisplay;
	
	public HUD()
	{
		mScoreDisplay = GameSettings.PLAYER_SCORE_DEFAULT;
		mLevelDisplay = GameSettings.LEVEL_DEFAULT;
	}

	@Override
	public void processMessage(Message msg) 
	{
		switch(msg.mType)
		{
		case SCORE_MESSAGE:
			mScoreDisplay = ((ScoreMessage) msg).getScore();
			break;
		case LEVEL_CHANGE_MESSAGE:
			mLevelDisplay++;
			break;
		}
	}
	
	public int getScoreDisplay()
	{
		return mScoreDisplay;
	}

	@Override
	public void paint(Graphics g) 
	{
		String scoreString = "Score: " + Integer.toString(mScoreDisplay);
		String levelString = "Level: " + Integer.toString(mLevelDisplay);
		
		g.setColor(new Color(0, 0, 0, 155)); // Set color to a slightly transparent black for bg rectangle
		
		g.fillRect(5, 5, 75, 50);
		
		g.setColor(Color.white); // set color to white for text
		
		g.drawString(scoreString, 10, 20);
		g.drawString(levelString, 10, 40);
	}

	@Override
	public void update() 
	{
	
	}

}
