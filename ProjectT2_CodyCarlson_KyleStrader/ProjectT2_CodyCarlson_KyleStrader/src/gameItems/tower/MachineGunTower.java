package gameItems.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;
import waves.WaveManager;

public class MachineGunTower extends Tower 
{	
	private static final float MACHINE_GUN_DELAY = .3f;
	public static final int MG_DAMAGE = 50;
	private static final int MG_UPGRADE_COST_START = 100;
	public static final int MG_BUY_COST = 500;
	private static final int MG_RADIUS = 150;
	
	public MachineGunTower(int x, int y, int w, int h, int fireRad, WaveManager waves)
	{
		super(x, y, w, h, MG_RADIUS, MACHINE_GUN_DELAY, waves, MG_DAMAGE, MG_UPGRADE_COST_START, MG_BUY_COST);
	}
	
	public MachineGunTower(Tower tmp) 
	{
		super(tmp);
	}

	@Override
	public void update(long timeNS)
	{
		mPrevTime = mCurTime;
		mCurTime = (double) System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > mShotDelay)
		{	
			shootAtCurrentTarget();
			mTimePassed -= mShotDelay;
		}
		
		mProjectileManager.update(timeNS, mCurrentTarget, mWaves);
	}
	
	@Override
	public void shootAtCurrentTarget()
	{
		if(mCurrentTarget != null)
		{	
			// get direction vector b/w target and tower
			Point2D.Float aimDir = new Point2D.Float(
					(float) (mCurrentTarget.getPosition().getX() - mPosition.getX()), 
					(float) (mCurrentTarget.getPosition().getY() - mPosition.getY()));
			
			// normalize direction vector
			float length = (float) mPosition.distance(mCurrentTarget.getPosition());
			aimDir.setLocation(aimDir.getX() / length, aimDir.getY() / length);
			
			// fire projectile in direction
			mProjectileManager.addProjectile(new MachineGunProjectile((int)mPosition.getX(), 
				(int) mPosition.getY(), 
				mDamage,
				mCurrentTarget,
				aimDir));
		}
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		g.fillOval((int) (mPosition.getX()-(mDimensions.getX()/2)), 
				(int) (mPosition.getY()-(mDimensions.getY()/2)), 
				(int) mDimensions.getX(), 
				(int) mDimensions.getY());
		
		if (mCurrentTarget != null && !mCurrentTarget.isDead())
		{
			g.drawLine((int) mPosition.getX(), 
					(int) mPosition.getY(), 
					(int) mCurrentTarget.getOrigin().getX(), 
					(int) mCurrentTarget.getOrigin().getY());
		}
		
		drawTargettingRadius(g, (int)mPosition.getX(), (int)mPosition.getY(), mFireRadius);
		
		g.setColor(Color.white);
		
		g.drawString(Integer.toString(mLevel), (int)mPosition.getX(), (int)mPosition.getY() + (int)(mDimensions.getY() / 4));
		
		mProjectileManager.paint(g);
	}
}
