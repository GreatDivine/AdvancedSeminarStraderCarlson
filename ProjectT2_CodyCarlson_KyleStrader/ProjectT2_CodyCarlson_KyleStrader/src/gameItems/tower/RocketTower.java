package gameItems.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;
import waves.WaveManager;

public class RocketTower extends Tower {
	private static final float ROCKET_SHOT_DELAY = 2.0f;
	
	public RocketTower(int x, int y, int w, int h, int fireRad, WaveManager waves) 
	{
		super(x, y, w, h, fireRad, ROCKET_SHOT_DELAY, waves);
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
		
		mProjectileManager.update(timens, mCurrentTarget, mWaves);
	}

	@Override
	public void paint(Graphics g) 
	{
		g.setColor(Color.blue);
		g.fillOval(
				(int) (mPosition.getX()-(mDimensions.getX()/2)), 
				(int) (mPosition.getY()-(mDimensions.getY()/2)), 
				(int) mDimensions.getX(), 
				(int) mDimensions.getY());
		
		drawTargettingRadius(g, (int)mPosition.getX(), (int)mPosition.getY(), mFireRadius);
		
		mProjectileManager.paint(g);
	}

	@Override
	public void shootAtCurrentTarget() 
	{
		if(mCurrentTarget != null)
		{	
			// get direction vector b/w target and tower
			Point2D.Float aimDir = new Point2D.Float((float) (mCurrentTarget.getPosition().getX() - mPosition.getX()), 
					(float) (mCurrentTarget.getPosition().getY() - mPosition.getY()));
			
			// normalize direction vector
			float length = (float) mPosition.distance(mCurrentTarget.getPosition());
			aimDir.setLocation(aimDir.getX() / length, aimDir.getY() / length);
			
			// fire projectile in direction
			mProjectileManager.addProjectile((int)mPosition.getX(), 
					(int)mPosition.getY(),  
					GameSettings.ROCKET_TOWER_SHOT_DAMAGE,
					mCurrentTarget);
		}
	}
	
	
}
