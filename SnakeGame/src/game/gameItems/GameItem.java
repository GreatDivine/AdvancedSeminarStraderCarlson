package game.gameItems;

import java.awt.Graphics;

/*
 * GameItem.java
 * 
 * GameItem is a common interface for all of the objects in the SnakeGame
 * that can be drawn/updated. Anything that will appear on the game screen
 * will implement this interface.
 * 
 * Cody Carlson - Nov 13, 2014
 */

public interface GameItem {
	
	public void paint(Graphics g);
	public void update();

}
