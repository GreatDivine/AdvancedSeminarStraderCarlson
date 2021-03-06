package waves;

import gameItems.tower.TowerManager;
import gameItems.zombie.Fatty;
import gameItems.zombie.Imp;
import gameItems.zombie.Runner;
import gameItems.zombie.Walker;
import gameItems.zombie.Zombie;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import player.Player;
import tiles.Level;
import util.GameSettings;

public class Wave 
{
	public final int ZOMBIE_TARGET_EPSILON = 3;
	private CopyOnWriteArrayList<Zombie> mZombies;
	private CopyOnWriteArrayList<Zombie> mSpawnQueue;
	private boolean mFinishedSpawning;
	private double mTimePassed;
	private double mCurTime;
	private double mPrevTime;
	private double mStartTime;
	private Random mRand;
	
	public Wave()
	{
		mZombies = new CopyOnWriteArrayList<Zombie>();
		mSpawnQueue = new CopyOnWriteArrayList<Zombie>();
		mFinishedSpawning = false;
		mRand = new Random();
		
		//timing
		mTimePassed = 0;
		mStartTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mCurTime = mStartTime;
		mPrevTime = 0;
	}
	
	public enum mZombieType
	{
		WALKER,
		RUNNER,
		FATTY,
		IMP
	}
	
	boolean getFinishedSpawning()
	{
		return mFinishedSpawning; 
	}
	
	public void addZombie(int x, int y, int w, int h, int hp, int spd)
	{
		mSpawnQueue.add(new Zombie(x, y, w, h, hp, spd));
	}
	
	public Zombie addZombie(int x, int y, mZombieType zType, Player p, Level level)
	{
		switch(zType)
		{
			case WALKER:
				Walker tmpW = new Walker(x,y);
				tmpW.addObserver(p);
				mSpawnQueue.add(tmpW);
				return tmpW;
			case RUNNER:
				Runner tmpR = new Runner(x,y);
				tmpR.addObserver(p);
				mSpawnQueue.add(tmpR);
				return tmpR;
			case FATTY:
				Fatty tmpF = new Fatty(x,y);
				tmpF.addObserver(p);
				mSpawnQueue.add(tmpF);
				return tmpF;
			case IMP:
				Imp tmpI = new Imp(x,y);
				tmpI.addObserver(p);
				tmpI.setpathIndex(level.getPathLength() - 1, level);
				mSpawnQueue.add(tmpI);
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
		mPrevTime = mCurTime;
		mCurTime = (double)System.nanoTime() / GameSettings.NANOSECONDS_TO_SECONDS;
		mTimePassed += mCurTime - mPrevTime;
		
		if (mSpawnQueue.size() == 0)
		{
			mFinishedSpawning = true;
		}
		else 
			mFinishedSpawning = false;
		
		if(mTimePassed > GameSettings.TIME_BETWEEN_SPAWN && !mFinishedSpawning)
		{
			mZombies.add(mSpawnQueue.remove(mRand.nextInt(mSpawnQueue.size())));
			mTimePassed -= GameSettings.TIME_BETWEEN_SPAWN;
		}
		
		for (Zombie z:mZombies)
		{
			z.update(timeNS);
			
			Point2D zPos = new Point2D.Float((float) z.getPosition().getX(), (float) z.getPosition().getY());
			
			Point2D zTargetPos = new Point2D.Float((float)z.getTarget().getX(), (float)z.getTarget().getY());
			
			float distanceFromCenter = (float) zPos.distance(zTargetPos);
			
			if (distanceFromCenter <= ZOMBIE_TARGET_EPSILON)
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
				z.sendMessage();
				mZombies.remove(z);
			}
			
			else if (z.isDead())
			{
				z.sendMessage();
				mZombies.remove(z);
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
	
	public void forceSpawnZombies()
	{
		for (Zombie z:mSpawnQueue)
		{
			mZombies.add(z);
		}
		
		mSpawnQueue.clear();
	}
	
	public int getNumZombies()
	{
		return mZombies.size();
	}
	
	public int getNumQueuedZombies()
	{
		return mSpawnQueue.size();
	}
	
	public Zombie getZombie(int index)
	{
		return mZombies.get(index);
	}
	
	public Zombie getQueuedZombie(int index)
	{
		return mSpawnQueue.get(index);
	}
	
	public void removeZombie(Zombie z)
	{
		mZombies.remove(z);
	}
	
	public void removeQueuedZombie(int index)
	{
		mSpawnQueue.remove(index);
	}
}
