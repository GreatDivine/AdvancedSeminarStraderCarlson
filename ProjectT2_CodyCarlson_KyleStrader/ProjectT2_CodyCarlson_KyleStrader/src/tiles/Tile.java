package tiles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import menus.BuildMenu;
import util.GameSettings;

@SuppressWarnings("serial")
public class Tile extends JPanel {
	
	protected int mPosX;
	protected int mPosY;
	protected int mWidth;
	protected int mHeight;
	
	private Rectangle mTileRect;
	
	private boolean mHasTower;
	
	public Tile(int x, int y)
	{
		mPosX = x;
		mPosY = y;
		
		this.setLocation(mPosX, mPosY);
		
		mWidth = GameSettings.TILE_SIZE;
		mHeight = GameSettings.TILE_SIZE;
		
		mHasTower = false;

		mTileRect = new Rectangle(mPosX, mPosY, mWidth, mHeight);
	}
	
	public Rectangle getRect()
	{
		return mTileRect;
	}
	
	public boolean containsPoint(int x, int y)
	{
		return mTileRect.contains(x, y);
	}
	
	public void setHasTower(boolean val)
	{
		mHasTower = val;
	}
	
	public void buttonClicked()
	{
		if (!mHasTower)
		{
			BuildMenu bm = new BuildMenu(this);
		}
		
		System.out.println("Tile Clicked: " + mPosX + ", " + mPosY);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.drawRect(mPosX, mPosY, mWidth, mHeight);
	}
	
	public int getXPos()
	{
		return mPosX;
	}
	
	public int getYPos()
	{
		return mPosY;
	}
	
	public int getXOrig()
	{
		return mPosX + GameSettings.TILE_SIZE/2;
	}
	
	public int getYOrig()
	{
		return mPosY + GameSettings.TILE_SIZE/2;
	}
}
