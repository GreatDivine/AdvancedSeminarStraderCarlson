package graphics;

import gameItems.GameItem;
import gameItems.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import util.TowerManager;
import util.WaveManager;

public class Room extends JPanel {
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
		//g.fillRect(0,0,100,100);
		
		mWaveManager.paint(g);
		mTowerManager.paint(g);
	}
	
	public void updateItems(long timeNS)
	{
		mWaveManager.update(timeNS);
		mTowerManager.update(timeNS);
	}
	
	public int getNumTowers()
	{
		return mTowerManager.getNumTowers();
	}

}
