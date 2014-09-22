package gameItems;

import java.awt.Color;
import java.awt.Graphics;

import util.GameSettings;
import util.ProjectileManager;

public class Tower extends GameItem {
	
	private int mFireRadius;
	private Zombie currentTarget;
	private float mShotDelay;
	private ProjectileManager mProjectileManager;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private int mShotDamage;
	
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
		mShotDamage = GameSettings.TOWER_SHOT_DAMAGE;
	}
	
	@Override
	public void update(long timeNS)
	{
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > mShotDelay){	
			//mProjectileManager.addProjectile((int)mPosX, (int)mPosY, 0.23f, 0.75f);
			mTimePassed -= mShotDelay;
		}
		
		mProjectileManager.update(timeNS);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval((int)mPosX-(mWidth/2), (int)mPosY-(mHeight/2), mWidth, mHeight);
		drawCircle(g, (int)mPosX, (int)mPosY, mFireRadius);
		
		mProjectileManager.paint(g);
	}
	
	public void checkForTarget(Zombie z)
	{
		float dist = (float)Math.sqrt(Math.pow((z.getX() - mPosX), 2) + Math.pow((z.getY() - mPosY), 2));
		
		if (dist < mFireRadius && currentTarget == null)
		{
			currentTarget = z;
			z.setIsTargeted(true);
		}
		else if (dist > mFireRadius && currentTarget == z)
		{
			currentTarget = null;
			z.setIsTargeted(false);
		}
		else if (currentTarget == z && z.checkIsDead() == true)
		{
			currentTarget = null;
			z.setIsTargeted(false);
		}
	}
	
	public Zombie getCurrentTarget()
	{
		return currentTarget;
	}
	
	public void damageCurrentTarget()
	{
		if (currentTarget != null)
		{
			currentTarget.takeDamage(mShotDamage);
		}
	}
	
	public void drawCircle(Graphics g, int xCenter, int yCenter, int r)
	{
		g.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}
	
	public ProjectileManager getProjectileManager()
	{
		return mProjectileManager;
	}
}
