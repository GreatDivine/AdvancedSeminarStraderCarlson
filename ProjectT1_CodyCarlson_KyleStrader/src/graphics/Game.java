package graphics;

import java.awt.Dimension;

import javax.swing.JFrame;

import util.GameSettings;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	private Room mRoom;
	
	public boolean keepGoing;
	
	public Game()
	{
		mRoom = new Room();
		keepGoing = true;
	}
	
	public void init()
	{
		add(mRoom);
		mRoom.addTower(300, 100, 20, 20, 200, 0.07f);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(GameSettings.FRAME_WIDTH, GameSettings.FRAME_HEIGHT));
		setVisible(true);
	}
	
	public void exitGame()
	{
		keepGoing = false;
	}
	
	public void update(long timeNS)
	{
		mRoom.updateItems(timeNS);
		//System.out.println("update tick");
	}
	
	public void drawToFrame()
	{
	}

}
