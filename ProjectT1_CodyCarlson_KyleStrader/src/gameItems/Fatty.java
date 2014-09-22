package gameItems;

import java.awt.Color;

public class Fatty extends Zombie {

	protected final static int FATTY_SIZE = 15;
	protected final static int FATTY_HP = 200;
	protected final static int FATTY_SPEED = 1;
	protected final static Color FATTY_COLOR = Color.gray;
	
	public Fatty(int x, int y)
	{
		super(x, y);
		
		mWidth = FATTY_SIZE;
		mHeight = FATTY_SIZE;
		mHP = FATTY_HP;
		mSpeed = FATTY_SPEED;
		mColor = FATTY_COLOR;
	}

}
