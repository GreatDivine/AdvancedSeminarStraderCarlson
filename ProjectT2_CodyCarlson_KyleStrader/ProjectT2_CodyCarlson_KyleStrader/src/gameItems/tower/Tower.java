package gameItems.tower;

import gameItems.GameItem;
import gameItems.zombie.Zombie;

import java.awt.Graphics;

import util.GameSettings;

public abstract class Tower extends GameItem{
	
	protected int mFireRadius;
	protected Zombie mCurrentTarget;
	protected float mShotDelay;
	protected ProjectileManager mProjectileManager;
	protected double mTimePassed;
	protected double mCurTime;
	protected double mPrevTime;
	protected double mStartTime;
	
	public Tower(int x, int y, int w, int h, int fireRad, float shotDelay)
	{
		super(x, y, w, h);
		
		mFireRadius = fireRad;
		mShotDelay = shotDelay;
		mProjectileManager = new ProjectileManager();
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
	}
	
	public abstract void update(long timens);
	public abstract void paint(Graphics g);
	public abstract void shootAtCurrentTarget();
	
	public void checkIfTargettable(Zombie z)
	{
		float dist = (float)Math.sqrt(Math.pow((z.getX() - mPosX), 2) + Math.pow((z.getY() - mPosY), 2));
		
		if (dist < mFireRadius && mCurrentTarget == null)
		{
			mCurrentTarget = z;
			z.setIsTargeted(true);
		}
		else if (dist > mFireRadius && mCurrentTarget == z)
		{
			mCurrentTarget = null;
			z.setIsTargeted(false);
		}
		else if (mCurrentTarget == z && z.isDead() == true)
		{
			mCurrentTarget = null;
			z.setIsTargeted(false);
		}
	}
	
	public Zombie getCurrentTarget()
	{
		return mCurrentTarget;
	}
	
	public void drawTargettingRadius(Graphics g, int xCenter, int yCenter, int r)
	{
		g.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}
	
	public ProjectileManager getProjectileManager()
	{
		return mProjectileManager;
	}

}
