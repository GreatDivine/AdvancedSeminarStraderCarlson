package main;

import gameItems.tower.TowerManager;

import java.awt.Graphics;

import javax.swing.JPanel;

import player.Player;
import player.PlayerUI;
import tiles.Level;
import waves.WaveManager;

@SuppressWarnings("serial")
public class Room extends JPanel 
{
	private WaveManager mWaveManager;
	private TowerManager mTowerManager;
	
	private Player mPlayer;
	private PlayerUI mUI;
	
	private Level testLevel;
	
	public Room()
	{
		mTowerManager = new TowerManager();
		mWaveManager = new WaveManager();
		
		mPlayer = new Player();
		mUI = new PlayerUI();
		
		mPlayer.addObserver(mUI);
		
		testLevel = new Level();
	}
	
	public void addMGTower(int x, int y, int w, int h, int r)
	{
		mTowerManager.addMGTower(x, y, w, h, r);
	}
	
	public void addRocketTower(int x, int y, int w, int h, int r)
	{
		mTowerManager.addRocketTower(x, y, w, h, r);
	}
	
	@Override
	public void paint(Graphics g)
	{
		mWaveManager.paint(g);
		mTowerManager.paint(g);
		
		mUI.paint(g);
		
		testLevel.paint(g);
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
