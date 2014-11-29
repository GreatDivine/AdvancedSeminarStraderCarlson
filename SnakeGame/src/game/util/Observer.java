package game.util;

import game.util.messages.Message;

public interface Observer {
	
	public void processMessage(Message msg);

}
