package gameItems.tower;

import gameItems.GameItem;
import gameItems.zombie.Zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;

public class Tower extends GameItem 
{
	
	private int mFireRadius;
	private Zombie mCurrentTarget;
	private float mShotDelay;
	private ProjectileManager mProjectileManager;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	
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
	
	@Override
	public void update(long timeNS)
	{
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > mShotDelay)
		{	
			shootAtCurrentTarget();
			mTimePassed -= mShotDelay;
		}
		
		mProjectileManager.update(timeNS);
	}
	
	public void shootAtCurrentTarget()
	{
		if(mCurrentTarget != null)
		{	
			// get direction vector b/w target and tower
			float aimDirX = mCurrentTarget.getX() - mPosX;
			float aimDirY = mCurrentTarget.getY() - mPosY;
			
			// normalize direction vector
			aimDirX /= Point2D.distance(mPosX, mPosY, mCurrentTarget.getX(), mCurrentTarget.getY());
			aimDirY /= Point2D.distance(mPosX, mPosY, mCurrentTarget.getX(), mCurrentTarget.getY());
			
			// fire projectile in direction
			mProjectileManager.addProjectile((int)mPosX, (int)mPosY, aimDirX, aimDirY);
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval((int)mPosX-(mWidth/2), (int)mPosY-(mHeight/2), mWidth, mHeight);
		drawTargettingRadius(g, (int)mPosX, (int)mPosY, mFireRadius);
		
		mProjectileManager.paint(g);
	}
	
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
