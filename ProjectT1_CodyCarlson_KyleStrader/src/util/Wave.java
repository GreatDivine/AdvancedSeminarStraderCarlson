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
	
	public Zombie addZombie(int x, int y, mZombieType zType){
		
		switch(zType){
			case WALKER:
				Walker tmpW = new Walker(x,y);
				mZombies.add(tmpW);
				return tmpW;
			case RUNNER:
				Runner tmpR = new Runner(x,y);
				mZombies.add(tmpR);
				return tmpR;
			case FATTY:
				Fatty tmpF = new Fatty(x,y);
				mZombies.add(tmpF);
				return tmpF;
		}
		
		return null; // if we get here we must have somehow gotten an incorrect zType
	}
	
	public void update(long timeNS){
		for(Zombie z:mZombies){
			z.update(timeNS);
			
			if (z.isOffscreen || z.checkIsDead())
			{
				removeZombie(z);
				break;
			}
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
	
	public Zombie getZombie(int index){
		return mZombies.get(index);
	}
	
	public void removeZombie(Zombie z)
	{
		mZombies.remove(z);
		//System.out.println("zombie removed");
	}
}
