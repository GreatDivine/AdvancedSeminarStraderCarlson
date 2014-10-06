package player;

import java.awt.Graphics;

import util.GameSettings;

public class PlayerUI implements Observer {
	
	private String mDispHP;
	private String mDispCash;
	
	public PlayerUI()
	{
		mDispHP = GameSettings.LIFE_PREFIX + GameSettings.START_HP;
		mDispCash = GameSettings.CASH_PREFIX + GameSettings.START_CASH;
	}

	@Override
	public void process(int hp, int cash) 
	{
		mDispHP = GameSettings.LIFE_PREFIX + String.valueOf(hp);
		mDispCash = GameSettings.CASH_PREFIX + String.valueOf(cash);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(GameSettings.LIFE_COLOR);
		g.drawString(mDispHP, 25, 25);
		g.setColor(GameSettings.CASH_COLOR);
		g.drawString(mDispCash, 25, 50);
	}

}
