package main;

import util.GameSettings;

public class TowerDefense 
{
	
	private static Game game;
	
	TowerDefense()
	{
	}
	
	public static void gameLoop()
	{
		double nextTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		while(game.keepGoing)
		{
			double curTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
			if (curTime >= nextTime)
			{
				nextTime += GameSettings.UPDATE_TIME;
				game.update((long)GameSettings.UPDATE_TIME);
				game.repaint();
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
	
	public static void main(String[] args)
	{
		game = new Game();
		game.init();
		System.out.println(GameSettings.NUM_TILES);
		gameLoop();
	}

}
