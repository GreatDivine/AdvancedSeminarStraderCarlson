package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Color;
import java.awt.geom.Point2D;

import util.GameSettings;

public class FlameProjectile extends Projectile 
{
	public static final Color COLOR = Color.YELLOW;
	public static final int SIZE = 7;
	//public static final int DIST_TO_FINAL_STATE = 200;
	
	private int distToFinalState;

	public FlameProjectile(int xPos, int yPos, int dmg, Zombie target, Point2D.Float vel, int toFinalState) 
	{
		super(xPos, yPos, dmg, target, COLOR, SIZE, vel);
		distToFinalState = toFinalState;
	}
	
	@Override
	public void update(long timeNS)
	{
		float distance = (float)mStartPosition.distance(mPosition);
		if (distance > distToFinalState)
		{
			distance = distToFinalState;
		}
		mColor = new Color(255, (int)(255 * (distance / distToFinalState)), 0, (int)(255 - (255 * (distance / distToFinalState))));
		mPosition.setLocation(mPosition.getX() + (mVelocity.getX() * PROJECTILE_SPEED), 
				mPosition.getY() + (mVelocity.getY() * PROJECTILE_SPEED));
		
		checkIsOutOfBounds();
	}
	
	public void checkIsOutOfBounds()
	{
		float distance = (float)mStartPosition.distance(mPosition);
		if (mPosition.getX() > GameSettings.FRAME_WIDTH || 
				mPosition.getY() > GameSettings.FRAME_HEIGHT || 
				mPosition.getX() < 0 || 
				mPosition.getY() < 0)
		{
			isOffscreen = true;
		}
		else if (distance >= distToFinalState)
		{
			isOffscreen = true;
		}
		else isOffscreen = false;
	}

}
