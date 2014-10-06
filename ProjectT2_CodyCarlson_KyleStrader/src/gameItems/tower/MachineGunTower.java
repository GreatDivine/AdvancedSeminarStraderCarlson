package gameItems.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;

public class MachineGunTower extends Tower 
{	
	private static final float MACHINE_GUN_DELAY = .07f;
	
	public MachineGunTower(int x, int y, int w, int h, int fireRad)
	{
		super(x, y, w, h, fireRad, MACHINE_GUN_DELAY);
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
		
		mProjectileManager.update(timeNS, mCurrentTarget);
	}
	
	@Override
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
			mProjectileManager.addProjectile((int)mPosX, (int)mPosY, aimDirX, aimDirY, GameSettings.MG_TOWER_SHOT_DAMAGE);
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
}
