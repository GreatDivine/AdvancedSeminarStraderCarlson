package graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import util.GameSettings;

public class GraphicsWindow extends JFrame {
	
	public Graphics2D g;
	
	public GraphicsWindow()
	{
	}
	
	public void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(GameSettings.FRAME_WIDTH, GameSettings.FRAME_HEIGHT));
		setVisible(true);
	}
	
	
	public void drawToFrame()
	{
		System.out.println("drawing");
	}

}
