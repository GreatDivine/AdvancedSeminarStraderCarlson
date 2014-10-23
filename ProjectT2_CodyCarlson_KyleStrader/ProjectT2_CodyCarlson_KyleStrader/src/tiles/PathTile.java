package tiles;

import java.awt.Color;
import java.awt.Graphics;

public class PathTile extends Tile
{
	PathTile(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(mPosX, mPosY, mWidth, mHeight);
	}
}
