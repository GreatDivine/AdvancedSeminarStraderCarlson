package gameItems.tower;

import gameItems.GameItem;
import gameItems.zombie.Zombie;

import java.awt.Graphics;

import util.GameSettings;
import waves.WaveManager;

public abstract class Tower extends GameItem{
	
	protected int mFireRadius;
	protected Zombie mCurrentTarget;
	protected WaveManager mWaves;
	protected float mShotDelay;
	protected ProjectileManager mProjectileManager;
	protected double mTimePassed;
	protected double mCurTime;
	protected double mPrevTime;
	protected double mStartTime;
	protected int mBuyCost;
	protected int mUpgradeCost;
	protected int mDamage;
	
	public Tower(int x, int y, int w, int h, int fireRad, float shotDelay, WaveManager waves)
	{
		super(x, y, w, h);
		
		mFireRadius = fireRad;
		mShotDelay = shotDelay;
		mProjectileManager = new ProjectileManager();
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		mWaves = waves;
	}
	
	public Tower(int x, int y, int w, int h, int fireRad, float shotDelay, WaveManager waves, int damage, int upCost, int buyCost)
	{
		super(x, y, w, h);
		
		mFireRadius = fireRad;
		mShotDelay = shotDelay;
		mProjectileManager = new ProjectileManager();
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		mWaves = waves;
		mUpgradeCost = upCost;
		mBuyCost = buyCost;
		mDamage = damage;
	}
	
	// Constructor for decorator upgrading
	public Tower(Tower originalTower)
	{
		super((int)originalTower.getPosition().getX(), (int)originalTower.getPosition().getY(), (int)originalTower.getDimensions().getX(), (int)originalTower.getDimensions().getY());
		
		mFireRadius = originalTower.mFireRadius;
		mShotDelay = originalTower.mShotDelay;
		mProjectileManager = originalTower.mProjectileManager;
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
		mWaves = originalTower.mWaves;
		mUpgradeCost = originalTower.mUpgradeCost * 2;
		mBuyCost = originalTower.mBuyCost;
		mDamage = (int) (originalTower.mDamage + (originalTower.mDamage * .25));
	}
	
	public abstract void update(long timens);
	public abstract void paint(Graphics g);
	public abstract void shootAtCurrentTarget();
	
	public void checkIfTargettable(Zombie z)
	{		
		float oppositeLength = (float) (z.getPosition().getY() - mPosition.getY());
		float hypotenuseLength = (float) (z.getPosition().getX() - mPosition.getX());
		float dist = (float)Math.sqrt(Math.pow(hypotenuseLength, 2) + Math.pow(oppositeLength, 2)); 
		
		if (dist <= mFireRadius && mCurrentTarget == null)
		{
			mCurrentTarget = z;
			z.setIsTargeted(true);
		}
		else if (dist > mFireRadius && mCurrentTarget == z)
		{
			mCurrentTarget = null;
			z.setIsTargeted(false);
		}
		else if (mCurrentTarget != null && (mCurrentTarget.isDead() || mCurrentTarget.isOffscreen()))
		{
			mCurrentTarget = null;
			z.setIsTargeted(false);
		}
	}
	
	public Zombie getCurrentTarget()
	{
		return mCurrentTarget;
	}
	
	public int getDamage()
	{
		return mDamage;
	}
	
	public int getCost()
	{
		return mBuyCost;
	}
	
	public int getUpgradeCost()
	{
		return mUpgradeCost;
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
