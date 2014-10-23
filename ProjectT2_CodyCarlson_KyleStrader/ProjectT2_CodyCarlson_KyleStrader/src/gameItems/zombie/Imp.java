package gameItems.zombie;

import java.awt.Color;

public class Imp extends Zombie {
	
	protected final static int IMP_SIZE = 10;
	protected final static int IMP_HP = 80;
	protected final static int IMP_SPEED = 1;
	protected final static Color IMP_COLOR = Color.orange;
	private final static int IMP_DAMAGE = 2;
	private final static int IMP_REWARD = 20;
	
	public Imp(int x, int y)
	{
		super(x, y, IMP_SIZE, IMP_SIZE, IMP_HP, IMP_SPEED, IMP_COLOR, IMP_DAMAGE, IMP_REWARD);
	}
}
