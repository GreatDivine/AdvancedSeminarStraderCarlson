package gameItems.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;

public class RocketTower extends Tower {
	private static final float ROCKET_SHOT_DELAY = 1.0f;
	
	public RocketTower(int x, int y, int w, int h, int fireRad) 
	{
		super(x, y, w, h, fireRad, ROCKET_SHOT_DELAY);
	}

	@Override
	public void update(long timens) 
	{
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > mShotDelay)
		{	
			shootAtCurrentTarget();
			mTimePassed -= mShotDelay;
		}
		
		mProjectileManager.update(timens, mCurrentTarget);
	}

	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillOval((int)mPosX-(mWidth/2), (int)mPosY-(mHeight/2), mWidth, mHeight);
		drawTargettingRadius(g, (int)mPosX, (int)mPosY, mFireRadius);
		
		mProjectileManager.paint(g);
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
			mProjectileManager.addProjectile((int)mPosX, (int)mPosY, aimDirX, aimDirY, GameSettings.ROCKET_TOWER_SHOT_DAMAGE);
		}
	}
	
	
}
