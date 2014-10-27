package menus;

import gameItems.tower.FlameTower;
import gameItems.tower.MachineGunTower;
import gameItems.tower.RocketTower;
import gameItems.tower.Tower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Room;
import tiles.Tile;

public class TowerMenu extends Menu {
	Tile mParentTile;
	Room mRoom;
	
	public TowerMenu(Tile parentTile)
	{
		super();
		
		mParentTile = parentTile;
		mRoom = (Room)mParentTile.getParent().getParent();
		addButtons();
	}
	
	private void addButtons()
	{
		JButton ugButton = new JButton("Upgrade Tower - $" + mParentTile.getTower().getUpgradeCost());
		ugButton.setActionCommand("upgrade");
		
		if (mRoom.getPlayer().getCash() < mParentTile.getTower().getUpgradeCost())
		{
			ugButton.setEnabled(false);
		}
		
		ugButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		
		JButton sellButton = new JButton("Sell Tower - $" + (mParentTile.getTower().getCost() / 2));
		sellButton.setActionCommand("sell");
				
		sellButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		mMenuPanel.add(ugButton);
		mMenuPanel.add(sellButton);
	}
	
	private void buttonClicked(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case("upgrade"):
			{
				upgradeTower();
				System.out.println("Clicked Upgrade Button");
				break;
			}
			case("sell"):
			{
				sellTower();
				System.out.println("Clicked Sell Button");
				break;
			}
		}
	}
	
	private void upgradeTower()
	{
		Tower tmp = mParentTile.getTower();
		
		mRoom.getPlayer().modCash(tmp.getUpgradeCost() * -1);
		
		mRoom.getTowerManager().removeTower(tmp);
		
		if (tmp instanceof MachineGunTower)
		{
			tmp = new MachineGunTower(tmp);
			mRoom.getTowerManager().addMGTower((MachineGunTower)tmp);
		}
		else if (tmp instanceof RocketTower)
		{
			tmp = new RocketTower(tmp);
			mRoom.getTowerManager().addRocketTower((RocketTower)tmp);
		}
		else if (tmp instanceof FlameTower)
		{
			tmp = new FlameTower(tmp);
			mRoom.getTowerManager().addFlameTower((FlameTower)tmp);
		}
		
		mParentTile.setTower(tmp);
		this.dispose();
	}
	
	private void sellTower()
	{
		mRoom.getPlayer().modCash(mParentTile.getTower().getCost() / 2);
		mRoom.getTowerManager().removeTower(mParentTile.getTower());
		mParentTile.setHasTower(false);
		this.dispose();
	}

}
