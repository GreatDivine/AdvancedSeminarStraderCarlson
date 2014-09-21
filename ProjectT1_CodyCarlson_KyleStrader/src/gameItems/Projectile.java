package gameItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;

public class Projectile extends GameItem {
	
	protected final static int PROJECTILE_SIZE = 3;
	protected final static int PROJECTILE_SPEED = 7;
	protected final static Color PROJECTILE_COLOR = Color.black;
	
	public boolean isOffscreen;
	
	private Point2D.Float mVelocity; //normalized velocity vector
	
	public Projectile(int xPos, int yPos, float xVel, float yVel){
		super(xPos, yPos, PROJECTILE_SIZE, PROJECTILE_SIZE);
		mVelocity = new Point2D.Float(xVel, yVel);
	}
	
	@Override
	public void update(long timeNS){
		mPosX += mVelocity.getX() * PROJECTILE_SPEED;
		mPosY += mVelocity.getY() * PROJECTILE_SPEED;
		
		checkOffscreen();
	}
	
	public void checkOffscreen()
	{
		if (mPosX > GameSettings.FRAME_WIDTH || mPosY > GameSettings.FRAME_HEIGHT || mPosX < 0 || mPosY < 0)
		{
			isOffscreen = true;
		}
		else isOffscreen = false;
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(PROJECTILE_COLOR);
		g.fillOval((int)mPosX, (int)mPosY, mWidth, mHeight);
	}
}
