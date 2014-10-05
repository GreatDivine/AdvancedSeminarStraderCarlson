package player;

import java.util.ArrayList;
import java.util.List;

public class Player implements Observable {
	
	int mHP;
	int mCash;
	
	private List<Observer> mObservers;
	
	public Player()
	{
		mObservers = new ArrayList<Observer>();
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

}
