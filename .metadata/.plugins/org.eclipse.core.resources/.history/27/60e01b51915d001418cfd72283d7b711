package gameItems.tower;

import gameItems.GameItem;
import gameItems.zombie.Zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import util.GameSettings;

public class Projectile extends GameItem 
{
	public final static int PROJECTILE_SPEED = 5;
	
	private int projDamage;
	public boolean isOffscreen;
	private Zombie mTarget;
	protected Color mColor;
	protected int mSize;
	
	protected Point2D.Float mVelocity; //normalized velocity vector
	protected Point2D.Float mStartPosition;
	
	public Projectile(int xPos, int yPos, int dmg, Zombie target, Color color, int size)
	{
		super(xPos, yPos, size, size);
		projDamage = dmg;
		mVelocity = new Point2D.Float(0, 0);
		mStartPosition = new Point2D.Float(xPos, yPos);
		mTarget = target;
		mColor = color;
		mSize = size;
		initialize();
	}
	
	public void initialize()
	{
		updateVelocity();
	}
	
	public Point2D.Float getStartPosition()
	{
		return mStartPosition;
	}
	
	public int getDamage()
	{
		return projDamage;
	}
	
	public Point2D.Float getVelocity()
	{
		return mVelocity;
	}
	
	@Override
	public void update(long timeNS)
	{
		updateVelocity();
		mPosition.setLocation(mPosition.getX() + (mVelocity.getX() * PROJECTILE_SPEED), 
				mPosition.getY() + (mVelocity.getY() * PROJECTILE_SPEED));
		
		checkIsOffscreen();
	}
	
	public void updateVelocity()
	{
		if (!mTarget.isDead())
		{
			Point2D.Float toTarget = new Point2D.Float((float) (mTarget.getOrigin().getX() - mPosition.getX()), 
					(float) (mTarget.getOrigin().getY() - mPosition.getY()));
			
			float oppositeLength = (float) (mPosition.getY() - mTarget.getPosition().getY());
			float hypotenuseLength = (float) (mPosition.getX() - mTarget.getPosition().getX());
			float length = (float)Math.sqrt(Math.pow(hypotenuseLength, 2) + Math.pow(oppositeLength, 2)); 
			
			mVelocity.setLocation(toTarget.getX() / length, toTarget.getY() / length);
		}
	}
	
	public void checkIsOffscreen()
	{
		if (mPosition.getX() > GameSettings.FRAME_WIDTH || 
				mPosition.getY() > GameSettings.FRAME_HEIGHT || 
				mPosition.getX() < 0 || 
				mPosition.getY() < 0)
		{
			isOffscreen = true;
		}
		else isOffscreen = false;
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(mColor);
		g.fillOval((int)mPosition.getX(), (int)mPosition.getY(), mSize, mSize);
	}
}
