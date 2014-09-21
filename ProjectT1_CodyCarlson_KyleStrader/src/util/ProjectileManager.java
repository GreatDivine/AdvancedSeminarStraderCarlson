package util;

import gameItems.Projectile;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ProjectileManager {
	
	private List<Projectile> mProjectiles;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	
	public ProjectileManager(){
		mProjectiles = new ArrayList<Projectile>();
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
	}
	
	public void addProjectile(int xPos, int yPos, float xVel, float yVel){
		mProjectiles.add(new Projectile(xPos, yPos, xVel, yVel));
	}
	
	
	public void update(long timeNS){
		for(Projectile p:mProjectiles){
			p.update(timeNS);
			
			if (p.isOffscreen)
			{
				removeProjectile(p);
				break;
			}
		}
	}
	
	public void paint(Graphics g){
		for(Projectile p:mProjectiles){
			p.paint(g);
		}
	}
	
	public Projectile getProjectile(int index){
		return mProjectiles.get(index);
	}
	
	public void removeProjectile(Projectile pr)
	{
		mProjectiles.remove(pr);
		//System.out.println("removed projectile");
	}
	
	public int getNumProjectiles(){
		return mProjectiles.size();
	}
}
