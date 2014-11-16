package tests;

import static org.junit.Assert.*;
import game.gameItems.snake.Snake;
import game.util.GameSettings;

import org.junit.Test;

public class SnakeTests {
	
	@Test
	public void snakeMoveTest()
	{
		// This will eventually test whether or not the snake is moving from tile to tile correctly, but movement needs to be completed first
	}
	
	@Test
	public void snakeEatTest()
	{
		// This will test the snake growth when it eats some food
	}
	
	@Test
	public void snakeTestSelfDeath()
	{
		// This will test snake death on hitting itself
	}
	
	@Test
	public void snakeTestWallDeath()
	{
		// this will test snake death on hitting edge of screen
	}
	
	@Test
	public void snakeTestChangeDirection()
	{
		Snake s = new Snake();
		
		assertEquals(GameSettings.SnakeDirection.RIGHT, s.getSnakeDirection());
		
		s.setSnakeDirection(GameSettings.SnakeDirection.UP);
		
		assertEquals(GameSettings.SnakeDirection.UP, s.getSnakeDirection());
	}

}
