package main;

import java.awt.Dimension;

import javax.swing.JFrame;

import util.GameSettings;

@SuppressWarnings("serial")
public class Game extends JFrame 
{
	
	public static Room mRoom;
	
	public boolean keepGoing;
	
	public Game()
	{
		mRoom = new Room();
		keepGoing = true;
	}
	
	public void init()
	{
		add(mRoom);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(GameSettings.FRAME_WIDTH + 16, GameSettings.FRAME_HEIGHT + 6));
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
}
