package tests;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import game.gameItems.food.Food.FoodType;
import game.gameItems.hud.HUD;
import game.gameItems.level.Level;
import game.gameItems.player.Player;
import game.main.Room;
import game.util.GameSettings.SnakeDirection;

import org.junit.Test;

public class RoomTests {
	
	@Test
	public void resetTest()
	{
		Room r = new Room();
		assertTrue(r.getPlayer().getSnake().isAlive());
		r.changeSnakeDirection(KeyEvent.VK_UP);
		r.getPlayer().getSnake().getHead().move();
		r.getPlayer().getSnake().getHead().move();
		r.update();
		assertFalse(r.getPlayer().getSnake().isAlive());
	}
	
	@Test
	public void highScoreTest()
	{
		Room r = new Room();
		
		//This test ensures that the player receives the high score from the CareTaker correctly during initialization.
		//by default the high score is "0"
		assertTrue(r.getPlayer().getHighScore() == r.getCareTaker().getCurMomento().getStateInt());
	}

}
