package game.util.messages;

public class ScoreMessage extends Message {
	
	private int mNewScore;
	
	public ScoreMessage(int newScore)
	{
		super(MessageType.SCORE_MESSAGE);
		
		mNewScore = newScore;
	}
	
	public int getScore()
	{
		return mNewScore;
	}

}
