package gameItems.zombie;

import java.awt.Color;

public class Runner extends Zombie 
{
	
	protected final static int RUNNER_SIZE = 8;
	protected final static int RUNNER_HP = 50;
	protected final static int RUNNER_SPEED = 4;
	protected final static Color RUNNER_COLOR = Color.blue;
	
	public Runner(int x, int y)
	{
		super(x, y);
		
		mWidth = RUNNER_SIZE;
		mHeight = RUNNER_SIZE;
		mHP = RUNNER_HP;
		mSpeed = RUNNER_SPEED;
		mColor = RUNNER_COLOR;
		mIsPathFinder = true;
	}

}
