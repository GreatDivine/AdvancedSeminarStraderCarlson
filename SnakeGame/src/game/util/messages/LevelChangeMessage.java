package game.util.messages;

public class LevelChangeMessage extends Message {
	
	int mNewLevel;

	public LevelChangeMessage(int level) 
	{
		super(MessageType.LEVEL_CHANGE_MESSAGE);
		
		mNewLevel = level;
	}
	
	public int getNewLevel()
	{
		return mNewLevel;
	}

}
