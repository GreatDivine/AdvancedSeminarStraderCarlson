package main;

import gameItems.tower.FlameTower;
import gameItems.tower.MachineGunTower;
import gameItems.tower.RocketTower;
import gameItems.tower.TowerManager;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import player.Player;
import player.PlayerUI;
import tiles.Level;
import tiles.Tile;
import util.GameSettings;
import waves.WaveManager;

@SuppressWarnings("serial")
public class Room extends JPanel implements MouseListener
{
	private WaveManager mWaveManager;
	private TowerManager mTowerManager;
	
	private Player mPlayer;
	private PlayerUI mUI;
	
	private Level testLevel;
	
	public Room()
	{
		testLevel = new Level();
		mTowerManager = new TowerManager();
		
		
		mPlayer = new Player();
		mUI = new PlayerUI();
		
		mWaveManager = new WaveManager(testLevel, mPlayer);
		
		mPlayer.addObserver(mUI);
		
		addMouseListener(this);
		this.add(testLevel);
	}
	
	public void addMGTower(int x, int y, int w, int h, int r)
	{
		mTowerManager.addMGTower(x, y, w, h, r, mWaveManager);
	}
	
	public void addRocketTower(int x, int y, int w, int h, int r)
	{
		mTowerManager.addRocketTower(x, y, w, h, r, mWaveManager);
	}	
	
	public void addMGTowerOnTile(int x, int y, int w, int h, int r)
	{
		mTowerManager.addMGTower(
				((x * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				((y * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				w, 
				h, 
				r, 
				mWaveManager);
		
		MachineGunTower mgt = (MachineGunTower)mTowerManager.getTower(mTowerManager.getNumTowers() - 1);

		
		for(Tile t: testLevel.getTiles())
		{
			if(t.containsPoint(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE))
			{
				t.setHasTower(true);
				t.setTower(mgt);
			}
		}
	}
	
	public void addRocketTowerOnTile(int x, int y, int w, int h, int r)
	{
		mTowerManager.addRocketTower(
				((x * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				((y * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				w, 
				h, 
				r, 
				mWaveManager);
		
		RocketTower rt = (RocketTower)mTowerManager.getTower(mTowerManager.getNumTowers() - 1);
		
		for(Tile t: testLevel.getTiles())
		{
			if(t.containsPoint(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE))
			{
				t.setHasTower(true);
				t.setTower(rt);
			}
		}
	}
	
	public void addFlameTowerOnTile(int x, int y, int w, int h, int r)
	{
		mTowerManager.addFlameTower(
				((x * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				((y * GameSettings.TILE_SIZE) + GameSettings.TILE_SIZE/2), 
				w, 
				h, 
				r, 
				mWaveManager);
		
		FlameTower ft = (FlameTower)mTowerManager.getTower(mTowerManager.getNumTowers() - 1);
		
		for(Tile t: testLevel.getTiles())
		{
			if(t.containsPoint(x * GameSettings.TILE_SIZE, y * GameSettings.TILE_SIZE))
			{
				t.setHasTower(true);
				t.setTower(ft);
			}
		}
	}
	
	public Player getPlayer()
	{
		return mPlayer;
	}
	
	@Override
	public void paint(Graphics g)
	{
		testLevel.paint(g);
		mWaveManager.paint(g);
		mTowerManager.paint(g);
		
		mUI.paint(g);
	}
	
	public void updateItems(long timeNS)
	{
		mWaveManager.update(timeNS, mTowerManager);
		mTowerManager.update(timeNS);
	}
	
	public TowerManager getTowerManager()
	{
		return mTowerManager;
	}
	
	public int getNumTowers()
	{
		return mTowerManager.getNumTowers();
	}
	
	public Level getLevel()
	{
		return testLevel;
	}
	
	private void mouseClickedEvent(int x, int y)
	{
		testLevel.checkTileClick(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{

	}

	@Override
	public void mouseExited(MouseEvent e) 
	{

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int mouseX = e.getX();
		int mouseY = e.getY();
		mouseClickedEvent(mouseX, mouseY);
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{

	}

}
