package gameItems.tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;

import util.GameSettings;
import waves.WaveManager;

public class FlameTower extends Tower{
	private static final float FIRE_DELAY = .01f;
	private static final float CONE = 30.0f;
	private static final int FLAME_DAMAGE = 4;
	private static final int FLAME_UPGRADE_COST_START = 100;
	public static final int FLAME_BUY_COST = 500;
	private static final int FLAME_RADIUS = 100;
	
	private Random mRand;
	
	public FlameTower(int x, int y, int w, int h, int fireRad, WaveManager waves)
	{
		super(x, y, w, h, FLAME_RADIUS, FIRE_DELAY, waves, FLAME_DAMAGE, FLAME_UPGRADE_COST_START, FLAME_BUY_COST);
		mRand = new Random();
	}
	
	public FlameTower(Tower tmp) 
	{
		super(tmp);
		mRand = new Random();
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
			
			float angle = mRand.nextInt() % CONE / 2;
			if(mRand.nextInt() % 1 == 0)
			{
				angle *= -1.0f;
			}
			angle *= (Math.PI / 180); // convert degrees to radians
			
			float cs = (float)Math.cos(angle);
			float sn = (float)Math.sin(angle);
			
			float x = (float)((aimDir.getX() * cs) - (aimDir.getY() * sn));
			float y = (float)((aimDir.getX() * sn) + (aimDir.getY() * cs));
			
			aimDir.setLocation(x, y);
			
			if (aimDir.getX() != 0.0 && aimDir.getY() != 0.0)
			{
				// normalize direction vector
				float length = (float) mPosition.distance(mCurrentTarget.getPosition());
				aimDir.setLocation(aimDir.getX() / length, aimDir.getY() / length);
				
				// fire projectile in direction
				mProjectileManager.addProjectile(new FlameProjectile((int) mPosition.getX() - FlameProjectile.SIZE/2, 
					(int) mPosition.getY() - FlameProjectile.SIZE/2, 
					mDamage,
					mCurrentTarget,
					aimDir,
					mFireRadius));
			}
		}
	}
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN);
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
