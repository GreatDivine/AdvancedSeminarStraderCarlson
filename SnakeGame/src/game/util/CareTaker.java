package game.util;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.IOException;

public class CareTaker {

	private GameSaveMomento mMomento; 
	
	private String mFileSysUrl;
	private boolean mSaveAppend;
	
	public CareTaker(String fileURL, boolean shouldAppend)
	{
		mFileSysUrl = fileURL;
		mSaveAppend = shouldAppend;
	}
	
	public void add(GameSaveMomento state)
	{
		mMomento = state;
	}
	
	public GameSaveMomento getCurMomento(int index)
	{
		return mMomento;
	}
	
	public void load()
	{
		try {
			FileReader read = new FileReader(mFileSysUrl);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void save() throws IOException
	{
		FileWriter write = new FileWriter(mFileSysUrl, mSaveAppend);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", mMomento.getState());
		
		print_line.close();
	}
}
