package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import main.Room;
import tiles.Tile;
import util.GameSettings;

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
		JButton mgButton = new JButton("Build MG Tower");
		mgButton.setActionCommand("buildMG");
		
		if (mRoom.getPlayer().getCash() < GameSettings.MG_TOWER_COST)
		{
			mgButton.setEnabled(false);
		}
		
		mgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		
		JButton rocketButton = new JButton("Build Rocket Tower");
		rocketButton.setActionCommand("buildRocket");
		
		if (mRoom.getPlayer().getCash() < GameSettings.ROCKET_TOWER_COST)
		{
			rocketButton.setEnabled(false);
		}
		
		rocketButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				buttonClicked(e);
			}
		});
		mMenuPanel.add(mgButton);
		mMenuPanel.add(rocketButton);
	}
	
	private void buttonClicked(ActionEvent e)
	{
		switch(e.getActionCommand())
		{
			case("buildMG"):
			{
				buildMG();
				System.out.println("Clicked Build MG Button");
				break;
			}
			case("buildRocket"):
			{
				buildRocket();
				System.out.println("Clicked Build Rocket Button");
				break;
			}
		}
	}
	
	private void buildMG()
	{		
		int xIndex = mParentTile.getXPos() / GameSettings.TILE_SIZE;
		int yIndex = mParentTile.getYPos() / GameSettings.TILE_SIZE;
		
		mRoom.addMGTowerOnTile(xIndex, yIndex, 20, 20, 200);
		
		mRoom.getPlayer().modCash(GameSettings.MG_TOWER_COST * -1);
		
		mParentTile.setHasTower(true);
		this.dispose();
	}
	
	private void buildRocket()
	{
		int xIndex = mParentTile.getXPos() / GameSettings.TILE_SIZE;
		int yIndex = mParentTile.getYPos() / GameSettings.TILE_SIZE;
		
		mRoom.addRocketTowerOnTile(xIndex, yIndex, 20, 20, 200);
		
		mRoom.getPlayer().modCash(GameSettings.ROCKET_TOWER_COST * -1);
		
		mParentTile.setHasTower(true);
		this.dispose();
	}

}
