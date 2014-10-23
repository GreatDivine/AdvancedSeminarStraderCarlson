package gameItems.zombie;

import java.awt.Color;

public class Runner extends Zombie 
{
	
	protected final static int RUNNER_SIZE = 8;
	protected final static int RUNNER_HP = 50;
	protected final static int RUNNER_SPEED = 4;
	protected final static Color RUNNER_COLOR = Color.blue;
	private final static int RUNNER_DAMAGE = 3;
	private final static int RUNNER_REWARD = 30;
	
	public Runner(int x, int y)
	{
		super(x, y, RUNNER_SIZE, RUNNER_SIZE, RUNNER_HP, RUNNER_SPEED, RUNNER_COLOR, RUNNER_DAMAGE, RUNNER_REWARD);
	
		mIsPathFinder = true;
	}

}
