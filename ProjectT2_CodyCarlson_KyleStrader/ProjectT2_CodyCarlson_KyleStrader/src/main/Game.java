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
		mRoom.addMGTowerOnTile(1, 6, 20, 20, 200);
		mRoom.addMGTowerOnTile(0, 6, 20, 20, 200);
		mRoom.addMGTowerOnTile(1, 5, 20, 20, 200);
		mRoom.addMGTowerOnTile(1, 4, 20, 20, 200);
		mRoom.addMGTowerOnTile(1, 3, 20, 20, 200);
		mRoom.addMGTowerOnTile(1, 2, 20, 20, 200);
		mRoom.addRocketTowerOnTile(5, 4, 20, 20, 200);
		
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
