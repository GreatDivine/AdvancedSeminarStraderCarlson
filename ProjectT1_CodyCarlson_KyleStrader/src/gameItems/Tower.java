package gameItems;

import java.awt.Graphics;

import util.ProjectileManager;

public class Tower extends GameItem {
	
	private int mFireRadius;
	private GameItem currentTarget;
	private int mShotDelay;
	private ProjectileManager mProjectileManager;
	
	public Tower(int x, int y, int w, int h, int fireRad, int shotDelay)
	{
		super(x, y, w, h);
		
		mFireRadius = fireRad;
		mShotDelay = shotDelay;
		mProjectileManager = new ProjectileManager();
	}
	
	@Override
	public void update(long timeNS){
		mProjectileManager.addProjectile((int)mPosX, (int)mPosY, 0.23f, 0.75f);
		mProjectileManager.update(timeNS);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.fillOval((int)mPosX-(mWidth/2), (int)mPosY-(mHeight/2), mWidth, mHeight);
		drawCircle(g, (int)mPosX, (int)mPosY, mFireRadius);
		
		mProjectileManager.paint(g);
	}
	
	public void drawCircle(Graphics g, int xCenter, int yCenter, int r)
	{
		g.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}
}
