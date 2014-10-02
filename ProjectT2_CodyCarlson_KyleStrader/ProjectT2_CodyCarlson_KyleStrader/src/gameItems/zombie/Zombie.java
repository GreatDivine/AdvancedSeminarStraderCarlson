package gameItems.zombie;

import gameItems.GameItem;

import java.awt.Color;
import java.awt.Graphics;

import util.GameSettings;

public class Zombie extends GameItem 
{
	
	protected int mHP;
	protected int mSpeed;
	protected Color mColor;
	
	protected boolean mIsTarget;
	private boolean mIsOffscreen;
	
	private final static int ZOMBIE_SIZE = 10;
	
	public static enum MoveDirections
	{
		RIGHT,
		LEFT,
		UP,
		DOWN
	};
	
	public Zombie(int x, int y)
	{
		super(x, y, ZOMBIE_SIZE, ZOMBIE_SIZE);

	    mColor = Color.black;
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd)
	{
		super(x, y, w, h);
		
		mHP = hp;
		mSpeed = spd;
	}
	
	public void move(MoveDirections dir)
	{
		switch (dir)
		{
			case RIGHT:
			{
				mPosX += mSpeed;
				break;
			}
			case LEFT:
			{
				mPosX -= mSpeed;
				break;
			}
			case UP:
			{
				mPosY -= mSpeed;
				break;
			}
			case DOWN:
			{
				mPosY += mSpeed;
				break;
			}
		}
	}
	
	@Override
	public void update(long timeNS)
	{
		move(MoveDirections.RIGHT);
		checkIsOffscreen();
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(mColor);
		g.fillOval((int)mPosX, (int)mPosY, mWidth, mHeight);
	}
	
	public int getHp(){
		return mHP;
	}
	
	public void takeDamage(int dmg)
	{
		mHP -= dmg;
	}
	
	public void setIsTargeted(boolean v)
	{
		mIsTarget = v;
	}
	
	public boolean getIsTargeted()
	{
		return mIsTarget;
	}
	
	public boolean isDead()
	{
		return mHP <= 0;
	}
	
	public void checkIsOffscreen()
	{
		if (mPosX > GameSettings.FRAME_WIDTH || mPosY > GameSettings.FRAME_HEIGHT || mPosX < 0 || mPosY < 0)
		{
			mIsOffscreen = true;
		}
		else mIsOffscreen = false;
	}
	
	public boolean isOffscreen()
	{
		return mIsOffscreen;
	}

}
