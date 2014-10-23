package gameItems.zombie;

import gameItems.GameItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import player.Observable;
import player.Observer;
import util.GameSettings;

public class Zombie extends GameItem implements Observable 
{
	
	protected int mHP;
	protected int mSpeed;
	protected Color mColor;
	
	protected boolean mIsTarget;
	private boolean mIsOffscreen;
	protected boolean mIsPathFinder;
	protected int mPathIndex;
	protected int m_xTarget;
	protected int m_yTarget;
	private int mPlayerHPMod;
	private int mPlayerGoldMod;
	
	private List<Observer> mObservers;
	
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

		mObservers = new ArrayList<Observer>();
	    mColor = Color.black;
		mIsPathFinder = false;
		mPathIndex = 0;
		m_xTarget = x;
		m_yTarget = y;
		mPlayerHPMod = 0;
		mPlayerGoldMod = 0;
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd)
	{
		super(x - (w/2), y - (h/2), w, h);
		
		mHP = hp;
		mSpeed = spd;
		mIsPathFinder = false;
		mPathIndex = 0;
		m_xTarget = x;
		m_yTarget = y;
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd, Color color, int hpMod, int goldMod)
	{
		super(x, y, w, h);
		
		mObservers = new ArrayList<Observer>();
		mHP = hp;
		mSpeed = spd;
		mPathIndex = 0;
		m_xTarget = x;
		m_yTarget = y;
		mColor = color;
		mPlayerHPMod = hpMod;
		mPlayerGoldMod = goldMod;
	}
	
	public void addObserver(Observer o)
	{
		mObservers.add(o);
	}
	
	public void setTarget(int x, int y)
	{
		m_xTarget = x;
		m_yTarget = y;
	}
	
	public int getXTarget()
	{
		return m_xTarget;
	}
	
	public int getYTarget()
	{
		return m_yTarget;
	}
	
	public void incrementPathIndex()
	{
		mPathIndex++;
	}
	
	public int getPathIndex()
	{
		return mPathIndex;
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
		if(mIsPathFinder)
		{
			if(m_xTarget > mPosX)
			{
				move(MoveDirections.RIGHT);
			}
			else if(m_xTarget < mPosX)
			{
				move(MoveDirections.LEFT);
			}
			else if(m_yTarget > mPosY)
			{
				move(MoveDirections.DOWN);
			}
			else if(m_yTarget < mPosY)
			{
				move(MoveDirections.UP);
			}	
			else
			{
				move(MoveDirections.RIGHT);
			}
		}
		
		else
		{
			move(MoveDirections.RIGHT);
		}
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
			//mPlayerHPMod = GameSettings.ZOMBIE_DAMAGE * -1;
			//sendMessage();
			//mPlayerHPMod = 0;
		}
		else mIsOffscreen = false;
	}
	
	public boolean isOffscreen()
	{
		//mPlayerGoldMod = GameSettings.ZOMBIE_WORTH;
		//sendMessage();
		//mPlayerGoldMod = 0;
		return mIsOffscreen;
	}
	
	@Override
	public void sendMessage() 
	{
		if (isDead())
		{
			for(Observer o: mObservers)
			{
				o.process(0, mPlayerGoldMod);
			}
		}
		else if (isOffscreen())
		{
			for(Observer o: mObservers)
			{
				o.process(mPlayerHPMod *-1, 0);
			}
		}
	}

}
