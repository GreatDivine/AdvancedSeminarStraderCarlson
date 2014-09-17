package graphics;

import gameItems.GameItem;
import gameItems.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import util.WaveManager;

public class Room extends JPanel {
	
	private List<GameItem> gameItems;
	private WaveManager waveManager;
	
	public Room()
	{
		gameItems = new ArrayList<GameItem>();
		waveManager = new WaveManager();
	}
	
	public void addTower(int x, int y, int w, int h, int r, int delay)
	{
		gameItems.add(new Tower(x, y, w, h, r, delay));
	}
	
	@Override
	public void paint(Graphics g)
	{
		//g.fillRect(0,0,100,100);
		
		waveManager.paint(g);

		g.setColor(Color.red);
		
		for(GameItem i: gameItems)
		{
			i.paint(g);
		}
	}
	
	public void updateItems(long timeNS)
	{
		waveManager.update(timeNS);
		
		for(GameItem i: gameItems)
		{
			i.update(timeNS);
		}
	}

}
