package main;

import gameItems.tower.TowerManager;

import java.awt.Graphics;

import javax.swing.JPanel;

import waves.WaveManager;

@SuppressWarnings("serial")
public class Room extends JPanel 
{
	
	private WaveManager mWaveManager;
	private TowerManager mTowerManager;
	
	public Room()
	{
		mTowerManager = new TowerManager();
		mWaveManager = new WaveManager();
	}
	
	public void addTower(int x, int y, int w, int h, int r, float delay)
	{
		mTowerManager.addTower(x, y, w, h, r, delay);
	}
	
	@Override
	public void paint(Graphics g)
	{
		mWaveManager.paint(g);
		mTowerManager.paint(g);
	}
	
	public void updateItems(long timeNS)
	{
		mWaveManager.update(timeNS, mTowerManager.getTower(0));
		mTowerManager.update(timeNS);
	}
	
	public int getNumTowers()
	{
		return mTowerManager.getNumTowers();
	}

}
