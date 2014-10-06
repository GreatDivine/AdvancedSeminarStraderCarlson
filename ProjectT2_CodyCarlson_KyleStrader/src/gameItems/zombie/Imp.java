package gameItems.zombie;

import java.awt.Color;

public class Imp extends Zombie {
	
	protected final static int IMP_SIZE = 10;
	protected final static int IMP_HP = 80;
	protected final static int IMP_SPEED = 2;
	protected final static Color IMP_COLOR = Color.orange;
	
	public Imp(int x, int y)
	{
		super(x, y);
		
		mWidth = IMP_SIZE;
		mHeight = IMP_SIZE;
		mHP = IMP_HP;
		mSpeed = IMP_SPEED;
		mColor = IMP_COLOR;
	}
}
