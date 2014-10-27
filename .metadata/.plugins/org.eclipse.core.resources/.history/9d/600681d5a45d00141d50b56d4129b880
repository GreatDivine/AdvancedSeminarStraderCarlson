package tiles;

import gameItems.tower.Tower;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import menus.BuildMenu;
import menus.Menu;
import menus.TowerMenu;
import util.GameSettings;

@SuppressWarnings("serial")
public class Tile extends JPanel {
	
	private int mPosX;
	private int mPosY;
	private int mWidth;
	private int mHeight;
	
	private boolean mIsPath;
	
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
		if (!mHasTower && !mIsPath)
		{
			BuildMenu bm = new BuildMenu(this);
			return "Build Menu";
		}
		else if (!mIsPath && mHasTower)
		{
			TowerMenu tm = new TowerMenu(this);
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
