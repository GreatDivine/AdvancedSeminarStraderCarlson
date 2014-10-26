package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import waves.WaveManager;

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
	
	public void update(long timeNS, Zombie currentTarget, WaveManager waves)
	{
		int numProjectiles = mProjectiles.size();
		for (int i = 0; i < numProjectiles;)
		{
			Projectile p = mProjectiles.get(i);
			p.update(timeNS);
			
			if (p != null)
			{
				if (checkBulletCollision(waves, p))
				{
					 removeProjectile(p);
					 numProjectiles--;
					 continue;
				}
			}
			
			if (p.isOffscreen && p != null)
			{
				removeProjectile(p);
				numProjectiles--;
				continue;
			}
			
			if (p.getVelocity().getX() == 0 && p.getVelocity().getY() == 0  && p != null)
			{
				removeProjectile(p);
				numProjectiles--;
				continue;
			}
			
			i++;
		}
	}
	
	public boolean checkBulletCollision(WaveManager waves, Projectile p)
	{
		boolean didCollide = false;
		int numWaves = waves.getNumWavesSpawned();
		for (int i = 0; i < numWaves; i++)
		{
			int numZombies = waves.getWave(i).getNumZombies();
			for (int j = 0; j < numZombies; j++)
			{
				Zombie z = waves.getWave(i).getZombie(j);
				float zRad = (float) z.getDimensions().getX() / 2;
				float pRad = (float) p.getDimensions().getX() / 2;
				
				float oppositeLength = (float) (z.getOrigin().getY() - p.getOrigin().getY());
				float hypotenuseLength = (float) (z.getOrigin().getX() - p.getOrigin().getX());
				float dist = (float)Math.sqrt(Math.pow(hypotenuseLength, 2) + Math.pow(oppositeLength, 2)); 
				
				if (dist < zRad + pRad)
				{
					z.takeDamage(p.getDamage());
					didCollide =  true;
				}
			}
		}
		return didCollide;
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
