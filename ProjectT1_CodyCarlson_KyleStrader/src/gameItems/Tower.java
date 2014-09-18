package gameItems;

import java.awt.Color;
import java.awt.Graphics;

import util.GameSettings;
import util.ProjectileManager;

public class Tower extends GameItem {
	
	private int mFireRadius;
	private GameItem currentTarget;
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
	public void update(long timeNS){
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if(mTimePassed > mShotDelay){	
			mProjectileManager.addProjectile((int)mPosX, (int)mPosY, 0.23f, 0.75f);
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
	
	public void drawCircle(Graphics g, int xCenter, int yCenter, int r)
	{
		g.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}
	
	public ProjectileManager getProjectileManager(){
		return mProjectileManager;
	}
}
