package tiles;

import gameItems.tower.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import menus.BuildMenu;
import menus.TowerMenu;
import util.GameSettings;

@SuppressWarnings("serial")
public class Tile extends JPanel {
	
	private int mPosX;
	private int mPosY;
	private int mWidth;
	private int mHeight;
	
	private boolean mIsPath;
	private boolean mMenuOpen;
	
	private Rectangle mTileRect;
	
	private boolean mHasTower;
	private Tower mTower;
	
	public Tile(int x, int y, boolean path)
	{
		mPosX = x;
		mPosY = y;
		mIsPath = path;
		
		this.setLocation(mPosX, mPosY);
		
		mWidth = GameSettings.TILE_SIZE;
		mHeight = GameSettings.TILE_SIZE;
		
		mHasTower = false;
		mTower = null;
		mMenuOpen = false;

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
	
	public String buttonClicked()
	{
		if (!mHasTower && !mIsPath && !mMenuOpen)
		{
			@SuppressWarnings("unused")
			BuildMenu bm = new BuildMenu(this);
			mMenuOpen = true;
			return "Build Menu";
		}
		else if (!mIsPath && mHasTower && !mMenuOpen)
		{
			@SuppressWarnings("unused")
			TowerMenu tm = new TowerMenu(this);
			mMenuOpen = true;
			return "Tower Menu";
		}
		
		return "No Menu";
	}
	
	public void paint(Graphics g)
	{
		if(mIsPath)
		{
			g.setColor(Color.GRAY);
			g.fillRect(mPosX, mPosY, mWidth, mHeight);
		}
		
		g.setColor(Color.black);
		g.drawRect(mPosX, mPosY, mWidth, mHeight);
	}
	
	public void setMenuOpen(boolean val)
	{
		mMenuOpen = val;
	}
	
	public boolean getMenuOpen()
	{
		return mMenuOpen;
	}
	
	public int getXPos()
	{
		return mPosX;
	}
	
	public Tower getTower()
	{
		return mTower;
	}
	
	public void setTower(Tower tower)
	{
		mTower = tower;
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
