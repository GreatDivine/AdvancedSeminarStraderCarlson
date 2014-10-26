package gameItems.zombie;

import gameItems.GameItem;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import player.Observable;
import player.Observer;
import tiles.Level;
import util.GameSettings;

public class Zombie extends GameItem implements Observable 
{
	
	protected float mHP;
	protected float mMaxHP;
	protected int mSpeed;
	protected Color mColor;
	
	protected boolean mIsTarget;
	private boolean mIsOffscreen;
	protected boolean mIsPathFinder;
	protected int mPathIndex;
	protected Point2D.Float mTarget;
	private int mPlayerHPMod;
	private int mPlayerGoldMod;
	
	private List<Observer> mObservers;
	
	private final static int ZOMBIE_SIZE = 10;
	
	public Zombie(int x, int y)
	{
		super(x, y, ZOMBIE_SIZE, ZOMBIE_SIZE);

		mObservers = new ArrayList<Observer>();
	    mColor = Color.black;
		mIsPathFinder = false;
		mPathIndex = 0;
		mTarget = new Point2D.Float(x, y);
		mPlayerHPMod = 0;
		mPlayerGoldMod = 0;
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd)
	{
		super(x, y, w, h);
		
		mHP = hp;
		mMaxHP = hp;
		mSpeed = spd;
		mIsPathFinder = false;
		mPathIndex = 0;
		mTarget = new Point2D.Float(x, y);
	}
	
	public Zombie(int x, int y, int w, int h, int hp, int spd, Color color, int hpMod, int goldMod)
	{
		super(x, y, w, h);
		
		mObservers = new ArrayList<Observer>();
		mHP = hp;
		mMaxHP = hp;
		mSpeed = spd;
		mPathIndex = 0;
		mTarget = new Point2D.Float(x, y);
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
		mTarget.setLocation(x, y);
	}
	
	public Point2D.Float getTarget()
	{
		return mTarget;
	}
	
	public void incrementPathIndex()
	{
		mPathIndex++;
	}
	
	public int getPathIndex()
	{
		return mPathIndex;
	}
	
	public void setpathIndex(int index, Level level)
	{
		mPathIndex = index;
		setTarget(level.getPathIndexed(mPathIndex).getXOrig(), level.getPathIndexed(mPathIndex).getYOrig());
	}
	
	public void move(Point2D.Float dir)
	{
		Point2D move = new Point2D.Float((float)dir.getX() * mSpeed, (float)dir.getY() * mSpeed);
		mPosition.setLocation(mPosition.getX() + move.getX(), mPosition.getY() + move.getY());
	}
	
	@Override
	public void update(long timeNS)
	{
		if(mIsPathFinder)
		{
			Point2D.Float locToTarget = new Point2D.Float((float)(mTarget.getX() - mPosition.getX()), 
					(float)(mTarget.getY() - mPosition.getY()));
			
			float length = (float) mPosition.distance(mTarget);
			
			if (length > 0)
			{
				locToTarget.setLocation(locToTarget.getX() / length, locToTarget.getY() / length);
				move(locToTarget);
			}
		}
		checkIsOffscreen();
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval((int)mPosition.getX() - 2, (int)mPosition.getY() - 2, (int)mDimensions.getX() + 4, (int)mDimensions.getY() + 4);
		
		g.fillRect((int)mPosition.getX() - 4, (int)mPosition.getY() - 8, (int)mDimensions.getX() + 8, 6);
		g.setColor(Color.RED);
		g.fillRect((int)mPosition.getX() - 3, (int)mPosition.getY() - 7, ((int)((mDimensions.getX() + 7) * (mHP/mMaxHP))), 4);
		
		g.setColor(mColor);
		g.fillOval((int)mPosition.getX(), (int)mPosition.getY(), (int)mDimensions.getX(), (int)mDimensions.getY());
	}
	
	public float getHp(){
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
		if (mPosition.getX() > GameSettings.FRAME_WIDTH || 
				mPosition.getY() > GameSettings.FRAME_HEIGHT || 
				mPosition.getX() < 0 || 
				mPosition.getY() < 0)
		{
			mIsOffscreen = true;
		}
		else mIsOffscreen = false;
	}
	
	public boolean isOffscreen()
	{
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
