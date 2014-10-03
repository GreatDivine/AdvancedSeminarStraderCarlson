package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import util.GameSettings;

public class ProjectileManager 
{
	
	private List<Projectile> mProjectiles;
	
	public ProjectileManager()
	{
		mProjectiles = new ArrayList<Projectile>();
	}
	
	public void addProjectile(int xPos, int yPos, float xVel, float yVel)
	{
		mProjectiles.add(new Projectile(xPos, yPos, xVel, yVel));
	}
	
	public void update(long timeNS, Zombie currentTarget)
	{
		for(Projectile p:mProjectiles)
		{
			p.update(timeNS);
			
			if (currentTarget != null)
			{
				checkBulletCollision(currentTarget, p);
			}
			
			if (p.isOffscreen)
			{
				removeProjectile(p);
				break;
			}
		}
	}
	
	public void checkBulletCollision(Zombie z, Projectile p)
	{
		float zRad = z.getWidth() / 2;
		float pRad = p.getWidth() / 2;
		
		float dist = (float)Math.sqrt(Math.pow(z.getX() - p.getX(), 2) + Math.pow(z.getY() - p.getY(), 2)); 
		
		if (dist < zRad + pRad)
		{
			z.takeDamage(GameSettings.TOWER_SHOT_DAMAGE);
		}
	}
	
	public void paint(Graphics g)
	{
		for(Projectile p:mProjectiles)
		{
			p.paint(g);
		}
	}
	
	public Projectile getProjectile(int index)
	{
		return mProjectiles.get(index);
	}
	
	public void removeProjectile(Projectile pr)
	{
		mProjectiles.remove(pr);
	}
	
	public int getNumProjectiles()
	{
		return mProjectiles.size();
	}
}