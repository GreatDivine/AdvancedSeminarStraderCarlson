package game.util.messages;

public class Message {
	
	public enum MessageType
	{
		SCORE_MESSAGE,
		LEVEL_CHANGE_MESSAGE
	}
	
	public MessageType mType;
	
	public Message(MessageType type)
	{
		mType = type;
	}
	
	public MessageType getType()
	{
		return mType;
	}

}
