package gameItems;

import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameItem {
	
	protected int mHP;
	protected int mSpeed;
	protected Color mColor;
	
	public Zombie(int x, int y)
	{
		mPosX = x;
		mPosY = y;
		mWidth = 10;
	    mHeight = 10;
	    mColor = Color.black;
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd)
	{
		super(x, y, w, h);
		
		mHP = hp;
		mSpeed = spd;
	}
	
	public void move(String dir)
	{
		switch (dir)
		{
			case "right":
			{
				mPosX += mSpeed;
				break;
			}
			case "left":
			{
				mPosX -= mSpeed;
				break;
			}
			case "up":
			{
				mPosY -= mSpeed;
				break;
			}
			case "down":
			{
				mPosY += mSpeed;
				break;
			}
		}
	}
	
	@Override
	public void update(long timeNS)
	{
		move("right");
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(mColor);
		g.fillOval(mPosX, mPosY, mWidth, mHeight);
	}
	
	public int getHp(){
		return mHP;
	}

}
