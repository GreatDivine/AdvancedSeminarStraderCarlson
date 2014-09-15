package graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class GraphicsWindow extends JFrame {
	private final int frameWidth;
	private final int frameHeight;
	
	public Graphics2D g;
	
	public GraphicsWindow(int width, int height)
	{
		this.frameWidth = width;
		this.frameHeight = height;
	}
	
	public void init()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(frameWidth, frameHeight));
		setVisible(true);
	}
	
	
	public void drawToFrame()
	{
		System.out.println("drawing");
	}

}
