package graphics;

import gameItems.Fatty;
import gameItems.GameItem;
import gameItems.Runner;
import gameItems.Tower;
import gameItems.Walker;
import gameItems.Zombie;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Room extends JPanel {
	
	private List<GameItem> gameItems;
	
	public Room()
	{
		gameItems = new ArrayList<GameItem>();
	}
	
	public void addTower(int x, int y, int w, int h, int r, int delay)
	{
		gameItems.add(new Tower(x, y, w, h, r, delay));
	}
	
	public void addZombie(int x, int y, int w, int h, int hp, int spd)
	{
		gameItems.add(new Zombie(x,y,w,h,hp,spd));
	}
	
	public void addWalker(int x, int y)
	{
		gameItems.add(new Walker(x,y));
	}
	
	public void addFatty(int x, int y)
	{
		gameItems.add(new Fatty(x,y));
	}
	
	public void addRunner(int x, int y)
	{
		gameItems.add(new Runner(x,y));
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		//g.fillRect(0,0,100,100);
		
		for(GameItem i: gameItems)
		{
			i.paint(g);
		}
	}
	
	public void updateItems(long timeNS)
	{
		for(GameItem i: gameItems)
		{
			i.update(timeNS);
		}
	}

}
