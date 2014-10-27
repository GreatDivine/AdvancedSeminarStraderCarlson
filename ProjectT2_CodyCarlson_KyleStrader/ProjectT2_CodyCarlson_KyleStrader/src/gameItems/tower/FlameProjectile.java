package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Color;

import util.GameSettings;

public class FlameProjectile extends Projectile 
{
	public static final Color COLOR = Color.YELLOW;
	public static final int SIZE = 10;
	public static final int DIST_TO_FINAL_STATE = 200;

	public FlameProjectile(int xPos, int yPos, int dmg, Zombie target) 
	{
		super(xPos, yPos, dmg, target, COLOR, SIZE);
	}
	
	@Override
	public void update(long timeNS)
	{
		float distance = (float)mStartPosition.distance(mPosition);
		if (distance > DIST_TO_FINAL_STATE)
		{
			distance = DIST_TO_FINAL_STATE;
		}
		mColor = new Color(255, (int)(255 * (distance / DIST_TO_FINAL_STATE)), 0, (int)(255 - (255 * (distance / DIST_TO_FINAL_STATE))));
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
		else if (distance >= DIST_TO_FINAL_STATE)
		{
			isOffscreen = true;
		}
		else isOffscreen = false;
	}

}
