package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import util.GameSettings;

@SuppressWarnings("serial")
public class Game extends JFrame 
{
	
	public static Room mRoom; //Used to make global calls to this object (needs getter)
	
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
	}
	
	public Room getRoom()
	{
		return mRoom;
	}
}
