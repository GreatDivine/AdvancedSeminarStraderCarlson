package gameItems.zombie;

import java.awt.Color;

public class Walker extends Zombie
{
	
	protected final static int WALKER_SIZE = 10;
	public final static int WALKER_HP = 100;
	public final static int WALKER_SPEED = 2;
	protected final static Color WALKER_COLOR = Color.green;
	private final static int WALKER_DAMAGE = 5;
	private final static int WALKER_REWARD = 50;

	public Walker(int x, int y)
	{
		super(x, y, WALKER_SIZE, WALKER_SIZE, WALKER_HP, WALKER_SPEED, WALKER_COLOR, WALKER_DAMAGE, WALKER_REWARD);
	
		mIsPathFinder = true;
	}

}
