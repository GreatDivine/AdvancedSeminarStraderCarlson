package gameItems;

import java.awt.Color;
import java.awt.Graphics;

public class Walker extends Zombie {
	
	protected final static int WALKER_SIZE = 10;
	protected final static int WALKER_HP = 100;
	protected final static int WALKER_SPEED = 2;
	protected final static Color WALKER_COLOR = Color.green;

	public Walker(int x, int y)
	{
		super(x, y);
		
		mWidth = WALKER_SIZE;
		mHeight = WALKER_SIZE;
		mHP = WALKER_HP;
		mSpeed = WALKER_SPEED;
		mColor = WALKER_COLOR;
	}

}
