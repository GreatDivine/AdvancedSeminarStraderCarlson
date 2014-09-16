package graphics;

import java.awt.Dimension;
import java.util.Timer;

import javax.swing.JFrame;

public class Game extends JFrame {
	private final int frameWidth;
	private final int frameHeight;
	
	private Room mRoom;
	
	public boolean keepGoing;
	
	public Game(int width, int height)
	{
		this.frameWidth = width;
		this.frameHeight = height;
		
		mRoom = new Room();
		
		keepGoing = true;
	}
	
	public void init()
	{
		add(mRoom);
		mRoom.addTower(300, 100, 20, 20, 200, 1);
		mRoom.addFatty(10, 200);
		mRoom.addWalker(10, 150);
		mRoom.addRunner(10, 250);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(frameWidth, frameHeight));
		setVisible(true);
	}
	
	public void exitGame()
	{
		keepGoing = false;
	}
	
	public void update(long timeNS)
	{
		mRoom.updateItems(timeNS);
		System.out.println("update tick");
	}
	
	public void drawToFrame()
	{
	}

}
