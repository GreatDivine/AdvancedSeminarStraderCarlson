package game.util.momento;

public class GameSaveMomento {

	private String mState;
	
	public GameSaveMomento(String state)
	{
		mState = state;
	}
	
	public String getStateStr()
	{
		return mState;
	}
	
	public int getStateInt()
	{
		return Integer.parseInt(mState);
	}
}
