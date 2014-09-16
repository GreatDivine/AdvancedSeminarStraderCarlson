package main;

import graphics.Game;

public class TowerDefense {
	
	private static Game game;
	private static final double UPDATE_TIME = 33.3 / 1000.0;
	private static final double SECONDS_CONVERSION = 1000000000.0;
	
	private double timePassed;
	private double deltaTime;
	
	TowerDefense()
	{
		timePassed = 0;
		deltaTime = UPDATE_TIME;
	}
	
	public static void gameLoop()
	{
		double nextTime = (double)System.nanoTime() / SECONDS_CONVERSION;
		while(game.keepGoing)
		{
			double curTime = (double)System.nanoTime() / SECONDS_CONVERSION;
			if (curTime >= nextTime)
			{
				nextTime += UPDATE_TIME;
				game.update((long)UPDATE_TIME);
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
		game = new Game(512, 512);
		game.init();
		
		gameLoop();
	}

}
