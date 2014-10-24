package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class ProjectileManager 
{
	
	private List<Projectile> mProjectiles;
	
	public ProjectileManager()
	{
		mProjectiles = new ArrayList<Projectile>();
	}
	
	public void addProjectile(int xPos, int yPos,int dmg, Zombie target)
	{
		mProjectiles.add(new Projectile(xPos, yPos, dmg, target));
	}
	
	public void update(long timeNS, Zombie currentTarget)
	{
		for(Projectile p:mProjectiles)
		{
			p.update(timeNS);
			
			if (currentTarget != null)
			{
				if (checkBulletCollision(currentTarget, p)) break;
			}
			
			if (p.isOffscreen)
			{
				removeProjectile(p);
				break;
			}
			
			if (p.getVelocity().getX() == 0 && p.getVelocity().getY() == 0 )
			{
				removeProjectile(p);
				break;
			}
			
			//if (p.getOrigin().distance(p.getStartPosition()) > GameSettings.)
		}
	}
	
	public boolean checkBulletCollision(Zombie z, Projectile p)
	{
		float zRad = (float) z.getDimensions().getX() / 2;
		float pRad = (float) p.getDimensions().getX() / 2;
		
		float oppositeLength = (float) (z.getOrigin().getY() - p.getOrigin().getY());
		float hypotenuseLength = (float) (z.getOrigin().getX() - p.getOrigin().getX());
		float dist = (float)Math.sqrt(Math.pow(hypotenuseLength, 2) + Math.pow(oppositeLength, 2)); 
		
		if (dist < zRad + pRad)
		{
			z.takeDamage(p.getDamage());
			removeProjectile(p);
			return true;
		}
		
		return false;
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
