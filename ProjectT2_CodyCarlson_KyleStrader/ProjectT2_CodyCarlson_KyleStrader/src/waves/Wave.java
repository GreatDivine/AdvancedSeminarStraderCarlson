package waves;

import gameItems.tower.TowerManager;
import gameItems.zombie.Fatty;
import gameItems.zombie.Imp;
import gameItems.zombie.Runner;
import gameItems.zombie.Walker;
import gameItems.zombie.Zombie;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import tiles.Level;

public class Wave 
{
	
	private List<Zombie> mZombies;
	
	public Wave()
	{
		mZombies = new ArrayList<Zombie>();
	}
	
	public enum mZombieType
	{
		WALKER,
		RUNNER,
		FATTY,
		IMP
	}
	
	public void addZombie(int x, int y, int w, int h, int hp, int spd)
	{
		mZombies.add(new Zombie(x, y, w, h, hp, spd));
	}
	
	public Zombie addZombie(int x, int y, mZombieType zType)
	{
		switch(zType)
		{
			case WALKER:
				Walker tmpW = new Walker(x,y);
				mZombies.add(tmpW);
				return tmpW;
			case RUNNER:
				Runner tmpR = new Runner(x,y);
				mZombies.add(tmpR);
				return tmpR;
			case FATTY:
				Fatty tmpF = new Fatty(x,y);
				mZombies.add(tmpF);
				return tmpF;
			case IMP:
				Imp tmpI = new Imp(x,y);
				mZombies.add(tmpI);
				return tmpI;
		}
		
		return null; // if we get here we must have somehow gotten an incorrect zType
	}
	
	public void update(long timeNS)
	{
		for (int i = 0; i < mZombies.size(); i++)
		{
			Zombie z = mZombies.get(i);
			
			z.update(timeNS);
			
			if (z.isOffscreen() || z.isDead())
			{
				removeZombie(z);
			    i--;
			}
		}
	}
	
	public void update(long timeNS, TowerManager towerManager, Level level)
	{
		ListIterator<Zombie> iter = mZombies.listIterator();
		
		while (iter.hasNext())
		{
			Zombie z = iter.next();

			z.update(timeNS);
			
			if (z.getX() == z.getXTarget() && z.getY() == z.getYTarget())
			{
				z.incrementPathIndex();
				
				if (z.getPathIndex() < level.getPathLength())
					z.setTarget(level.getPathIndexed(z.getPathIndex()).getXOrig(), level.getPathIndexed(z.getPathIndex()).getYOrig());
			}
			
			int numTowers = towerManager.getNumTowers();
			for(int i = 0; i < numTowers; i++)
			{
				towerManager.getTower(i).checkIfTargettable(z);
			}
			
			if (z.isOffscreen())
			{
				iter.remove();
			}
			
			if (z.isDead())
			{
				iter.remove();
			}
		}
	}
	
	public void paint(Graphics g)
	{
		for(Zombie z:mZombies)
		{
			z.paint(g);
		}
	}
	
	public int getNumZombies()
	{
		return mZombies.size();
	}
	
	public Zombie getZombie(int index)
	{
		return mZombies.get(index);
	}
	
	public void removeZombie(Zombie z)
	{
		mZombies.remove(z);
	}
}
