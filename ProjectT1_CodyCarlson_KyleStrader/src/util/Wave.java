package util;

import gameItems.Fatty;
import gameItems.Runner;
import gameItems.Walker;
import gameItems.Zombie;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Wave {
	
	private List<Zombie> mZombies;
	
	public Wave(){
		mZombies = new ArrayList<Zombie>();
	}
	
	public enum mZombieType{
		WALKER,
		RUNNER,
		FATTY
	}
	
	public void addZombie(int x, int y, int w, int h, int hp, int spd){
		mZombies.add(new Zombie(x, y, w, h, hp, spd));
	}
	
	public void addZombie(int x, int y, mZombieType zType){
		
		switch(zType){
			case WALKER:
				mZombies.add(new Walker(x, y));
				break;
			case RUNNER:
				mZombies.add(new Runner(x, y));
				break;
			case FATTY:
				mZombies.add(new Fatty(x, y));
		}
	}
	
	public void update(long timeNS){
		for(Zombie z:mZombies){
			z.update(timeNS);
		}
	}
	
	public void paint(Graphics g){
		for(Zombie z:mZombies){
			z.paint(g);
		}
	}
	
	public int getNumZombies(){
		return mZombies.size();
	}
}
