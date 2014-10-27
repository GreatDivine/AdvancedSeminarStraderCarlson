package gameItems.tower;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import waves.WaveManager;

public class TowerManager 
{
	
	private List<Tower> mTowers;
	
	public TowerManager()
	{
		mTowers = new ArrayList<Tower>();
	}
	
	public void addMGTower(int x, int y, int w, int h, int fireRad, WaveManager waves)
	{
		mTowers.add(new MachineGunTower(x, y, w, h, fireRad, waves));
	}
	
	public void addMGTower(MachineGunTower tower)
	{
		mTowers.add(tower);
	}
	
	public void addRocketTower(int x, int y, int w, int h, int fireRad, WaveManager waves)
	{
		mTowers.add(new RocketTower(x, y, w, h, fireRad, waves));
	}
	
	public void addRocketTower(RocketTower tower)
	{
		mTowers.add(tower);
	}
	
	public void addFlameTower(int x, int y, int w, int h, int fireRad, WaveManager waves)
	{
		mTowers.add(new FlameTower(x, y, w, h, fireRad, waves));
	}

	public void addFlameTower(FlameTower tower)
	{
		mTowers.add(tower);
	}
	
	public void update(long timeNS)
	{
		for(Tower t:mTowers)
		{
			t.update(timeNS);
		}
	}
	
	public void removeTower(Tower tow)
	{
		for(Tower t:mTowers)
		{
			if (t == tow)
			{
				mTowers.remove(tow);
				break;
			}
		}
	}
	
	public void paint(Graphics g)
	{
		for(Tower t:mTowers)
		{
			t.paint(g);
		}
	}
	
	public Tower getTower(int index)
	{
		return mTowers.get(index);
	}
	
	public int getNumTowers()
	{
		return mTowers.size();
	}
}
