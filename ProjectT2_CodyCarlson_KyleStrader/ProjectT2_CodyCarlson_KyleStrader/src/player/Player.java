package player;

import java.util.ArrayList;
import java.util.List;

import util.GameSettings;

public class Player implements Observable, Observer {
	
	int mHP;
	int mCash;
	
	private List<Observer> mObservers;
	
	public Player()
	{
		mObservers = new ArrayList<Observer>();
		mHP = GameSettings.START_HP;
		mCash = GameSettings.START_CASH;
	}
	
	public int getHP()
	{
		return mHP;
	}
	
	public int getCash()
	{
		return mCash;
	}
	
	public void modCash(int val)
	{
		mCash += val;
		sendMessage();
	}
	
	public void addObserver(Observer o)
	{
		mObservers.add(o);
	}

	@Override
	public void sendMessage() 
	{
		for(Observer o: mObservers)
		{
			o.process(mHP, mCash);
		}
	}
	
	@Override
	public void process(int hp, int cash) 
	{
		mHP += hp;
		mCash += cash;
		
		sendMessage();
	}

}
