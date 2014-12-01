package game.gameItems.player;

import java.util.ArrayList;

import game.util.Observable;
import game.util.Observer;
import game.util.messages.Message.MessageType;
import game.util.messages.ScoreMessage;

public class Player implements Observable {
	
	int mScore;
	
	ArrayList<Observer> mObservers;
	
	public Player()
	{
		mObservers = new ArrayList<Observer>();
	}

	@Override
	public void sendMessage(MessageType type) 
	{
		if (type == MessageType.SCORE_MESSAGE)
		{
			for (Observer o:mObservers)
			{
				ScoreMessage msg = new ScoreMessage(mScore);
				o.processMessage(msg);
			}
		}
	}

}
