package gameItems.zombie;

import java.awt.Color;

public class Fatty extends Zombie 
{

	protected final static int FATTY_SIZE = 15;
	protected final static int FATTY_HP = 200;
	protected final static int FATTY_SPEED = 1;
	protected final static Color FATTY_COLOR = Color.CYAN;
	private final static int FATTY_DAMAGE = 10;
	private final static int FATTY_REWARD = 100;
	
	public Fatty(int x, int y)
	{
		super(x, y, FATTY_SIZE, FATTY_SIZE, FATTY_HP, FATTY_SPEED, FATTY_COLOR, FATTY_DAMAGE, FATTY_REWARD);

		mIsPathFinder = true;
	}

}
