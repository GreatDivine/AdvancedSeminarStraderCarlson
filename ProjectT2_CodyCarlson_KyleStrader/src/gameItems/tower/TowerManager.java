package gameItems.tower;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class TowerManager 
{
	
	private List<Tower> mTowers;
	
	public TowerManager()
	{
		mTowers = new ArrayList<Tower>();
	}
	
	public void addTower(int x, int y, int w, int h, int fireRad, float shotDelay)
	{
		mTowers.add(new Tower(x, y, w, h, fireRad, shotDelay));
	}
	
	public void update(long timeNS)
	{
		for(Tower t:mTowers)
		{
			t.update(timeNS);
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
