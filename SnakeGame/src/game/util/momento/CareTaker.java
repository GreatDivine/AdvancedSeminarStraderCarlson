package game.util.momento;

import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CareTaker {

	private List<GameSaveMomento> mMomentos; 
	
	private String mFileSysUrl;
	private boolean mSaveAppend;
	
	public CareTaker(String fileURL, boolean shouldAppend)
	{
		mFileSysUrl = fileURL;
		mSaveAppend = shouldAppend;
		mMomentos = new ArrayList<GameSaveMomento>();
	}
	
	public void add(GameSaveMomento state)
	{
		mMomentos.add(state);
	}
	
	public GameSaveMomento getCurMomento()
	{
		return mMomentos.get(mMomentos.size() - 1);
	}
	
	public void load(String fileName) throws IOException
	{
		if (new File(fileName).isFile())
		{
			try {
				File file = new File(fileName);
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				StringBuffer stringBuffer = new StringBuffer();
				String line;
				line = bufferedReader.readLine();
				stringBuffer.append(line);
				fileReader.close();
				System.out.println("Contents of file:");
				System.out.println(stringBuffer.toString());
				GameSaveMomento state = new GameSaveMomento(line);
				mMomentos.add(state);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			GameSaveMomento state = new GameSaveMomento("0");
			mMomentos.add(state);
		}
	}
	
	public void save() throws IOException
	{
		FileWriter write = new FileWriter(mFileSysUrl, mSaveAppend);
		PrintWriter print_line = new PrintWriter(write);
		
		print_line.printf("%s" + "%n", getCurMomento().getStateStr());
		
		print_line.close();
	}
}
