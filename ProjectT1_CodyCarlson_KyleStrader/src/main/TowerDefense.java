package main;

import graphics.GraphicsWindow;

public class TowerDefense {
	
	private static GraphicsWindow mWindow;
	
	TowerDefense()
	{
	}
	
	public static void main(String[] args)
	{
		mWindow = new GraphicsWindow(512, 512);
		mWindow.init();
		mWindow.drawToFrame();
	}

}
