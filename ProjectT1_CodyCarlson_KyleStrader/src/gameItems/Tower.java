package gameItems;

import java.awt.Graphics;

public class Tower extends GameItem {
	
	private int mFireRadius;
	private GameItem currentTarget;
	private int mShotDelay;
	
	public Tower(int x, int y, int w, int h, int fireRad, int shotDelay)
	{
		super(x, y, w, h);
		
		mFireRadius = fireRad;
		mShotDelay = shotDelay;
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.fillOval(mPosX-(mWidth/2), mPosY-(mHeight/2), mWidth, mHeight);
		drawCircle(g, mPosX, mPosY, mFireRadius);
	}
	
	public void drawCircle(Graphics g, int xCenter, int yCenter, int r)
	{
		g.drawOval(xCenter-r, yCenter-r, 2*r, 2*r);
	}
}
