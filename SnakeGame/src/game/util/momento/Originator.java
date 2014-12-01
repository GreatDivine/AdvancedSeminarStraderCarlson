package game.util.momento;

public class Originator {
	
	private String mState;
	
	public void setState(String state)
	{
		mState = state;
	}
	
	public String getState()
	{
		return mState;
	}
	
	public GameSaveMomento saveStateToMomento()
	{
		return new GameSaveMomento(mState);
	}
	
	public void getStateFromMomento(GameSaveMomento momento)
	{
		mState = momento.getStateStr();
	}
}
