package menus;

import gameItems.tower.FlameTower;
import gameItems.tower.MachineGunTower;
import gameItems.tower.RocketTower;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Room;
import tiles.Tile;
import util.GameSettings;

@SuppressWarnings("serial")
public class BuildMenu extends Menu{
	
	Tile mParentTile;
	Room mRoom;
	
	public BuildMenu(Tile parentTile)
	{
		super();
		
		mParentTile = parentTile;
		mRoom = (Room)mParentTile.getParent().getParent();
		addButtons();
	}
	
	private void addButtons()
	{
		JButton mgButton = new JButton("Build MG Tower - $" + MachineGunTower.MG_BUY_COST);
		mgButton.setActionCommand("buildMG");
		
		if (mRoom.getPlayer().getCash() < MachineGunTower.MG_BUY_COST)
		{
			mgButton.setEnabled(false);
		}
		
		mgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		
		JButton rocketButton = new JButton("Build Rocket Tower - $" + RocketTower.ROCKET_BUY_COST);
		rocketButton.setActionCommand("buildRocket");
		
		if (mRoom.getPlayer().getCash() < RocketTower.ROCKET_BUY_COST)
		{
			rocketButton.setEnabled(false);
		}
		
		rocketButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		
		JButton flameButton = new JButton("Build Flame Tower - $" + FlameTower.FLAME_BUY_COST);
		flameButton.setActionCommand("buildFlame");
		
		if (mRoom.getPlayer().getCash() < FlameTower.FLAME_BUY_COST)
		{
			flameButton.setEnabled(false);
		}
		
		flameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		
		mMenuPanel.add(mgButton);
		mMenuPanel.add(rocketButton);
		mMenuPanel.add(flameButton);
	}
	
	private void buttonClicked(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case("buildMG"):
			{
				buildMG();
				break;
			}
			case("buildRocket"):
			{
				buildRocket();
				break;
			}
			case("buildFlame"):
			{
				buildFlame();
				break;
			}
		}
	}
	
	private void buildFlame()
	{
		int xIndex = mParentTile.getXPos() / GameSettings.TILE_SIZE;
		int yIndex = mParentTile.getYPos() / GameSettings.TILE_SIZE;
		
		mRoom.addFlameTowerOnTile(xIndex, yIndex, 20, 20, 200);
		
		mRoom.getPlayer().modCash(FlameTower.FLAME_BUY_COST * -1);
		
		mParentTile.setHasTower(true);
		mParentTile.setMenuOpen(false);
		this.dispose();
	}
	
	private void buildMG()
	{		
		int xIndex = mParentTile.getXPos() / GameSettings.TILE_SIZE;
		int yIndex = mParentTile.getYPos() / GameSettings.TILE_SIZE;
		
		mRoom.addMGTowerOnTile(xIndex, yIndex, 20, 20, 200);
		
		mRoom.getPlayer().modCash(MachineGunTower.MG_BUY_COST * -1);
		
		mParentTile.setHasTower(true);
		mParentTile.setMenuOpen(false);
		this.dispose();
	}
	
	private void buildRocket()
	{
		int xIndex = mParentTile.getXPos() / GameSettings.TILE_SIZE;
		int yIndex = mParentTile.getYPos() / GameSettings.TILE_SIZE;
		
		mRoom.addRocketTowerOnTile(xIndex, yIndex, 20, 20, 200);
		
		mRoom.getPlayer().modCash(RocketTower.ROCKET_BUY_COST * -1);
		
		mParentTile.setHasTower(true);
		mParentTile.setMenuOpen(false);
		this.dispose();
	}

}
