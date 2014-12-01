package game.main;

import java.awt.Dimension;

import javax.swing.JFrame;

import game.util.GameSettings;

public class Game extends JFrame {
	
	public static Room mRoom;
	
	public boolean mKeepGoing;
	public boolean mReset;
	
	public Game()
	{
		mRoom = new Room();
		mKeepGoing = true;
		mReset = false;
	}

	public void init()
	{
		add(mRoom);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(GameSettings.FRAME_WIDTH + 16, GameSettings.FRAME_HEIGHT + 6));
		setVisible(true);
	}

	public void update() 
	{
		mRoom.update();
		//mReset = mRoom.shouldReset();
	}
	
	public void gameLoop()
	{
		double nextTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		while(mKeepGoing)
		{
			double curTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
			if (curTime >= nextTime)
			{
				nextTime += GameSettings.UPDATE_TIME;
				update();
				repaint();
			}
			else
			{
				int sleepTime = (int)(1000.0 * (nextTime - curTime));
				if (sleepTime > 0)
				{
					try
					{
						Thread.sleep(sleepTime);
					}
					catch(InterruptedException e)
					{
						//we got an error
					}
				}
			}
		}
	}
}
