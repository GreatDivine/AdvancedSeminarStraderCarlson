package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import game.gameItems.food.Food.FoodType;
import game.gameItems.level.Level;
import game.gameItems.player.Player;
import game.gameItems.snake.Snake;
import game.gameItems.snake.SnakeBodyPart;
import game.gameItems.snake.SnakePartFactory;
import game.util.GameSettings;
import game.util.GameSettings.SnakeDirection;

import org.junit.Test;

public class SnakeTests {
	
	@Test
	public void snakeMoveTest()
	{
		Level l = new Level();
		Snake s = new Snake(0,0,500,l);
		
		assertEquals(0, s.getHead().getTileX());
		
		s.getHead().move();
		
		assertEquals(1, s.getHead().getTileX());
	}
	
	@Test
	public void snakeEatTest()
	{
		Level l = new Level();
		Player p = new Player(l, 0);
		Snake s = new Snake(0,0,500, l, p);
		
		l.spawnFoodOnTile(1, 0, FoodType.REGULAR);
		
		assertTrue(l.getTile(1, 0).hasFood());
		
		s.getHead().move();
		s.processFood(1, 0);
		
		assertFalse(l.getTile(1,0).hasFood());
		
		assertEquals(2, s.getSnakeSize());
	}
	
	@Test
	public void snakeTestSelfDeath()
	{
		Level l = new Level();
		
		Snake s = new Snake(0, 0, 0, l); // at UL corner with update time of 0 milliseconds
		
		assertTrue(s.isAlive());
		
		s.setSnakeDirection(SnakeDirection.NONE);
		
		s.addSnakePart();
		
		s.update();
		
		assertFalse(s.isAlive());
	}
	
	@Test
	public void snakeTestWallDeath()
	{
		Level l = new Level();
		
		Snake s = new Snake(15, 0, 500, l); // spawn snake at the last tile in the first row
		
		assertTrue(s.isAlive());
		
		s.getHead().move();
		s.update();
		
		assertFalse(s.isAlive());
	}
	
	@Test
	public void snakeTestChangeDirection()
	{
		Level l = new Level();
		Snake s = new Snake(0,0,500,l);
		
		assertEquals(GameSettings.SnakeDirection.RIGHT, s.getSnakeDirection());
		
		s.setSnakeDirection(GameSettings.SnakeDirection.UP);
		
		assertEquals(GameSettings.SnakeDirection.UP, s.getSnakeDirection());
	}
	
	@Test
	public void testSnakeBodyPartFactory()
	{
		ArrayList<SnakeBodyPart> mSnakeBody;
		mSnakeBody = new ArrayList<SnakeBodyPart>();
		SnakeBodyPart p = SnakePartFactory.createBodyPart(0, 1, mSnakeBody);
		
		assertEquals(0, p.getTileX());
		assertEquals(1, p.getTileY());
	}
	
	@Test
	public void testSnakeUpdate()
	{
		Level l = new Level();
		
		Snake s = new Snake(0, 0, 0, l); // at UL corner with update time of 0 milliseconds
		
		assertTrue(s.isAlive());

		s.update();
		
		assertEquals(1, s.getHead().getTileX());
	}
	
	@Test
	public void testSnakeCannotMoveBackwards()
	{

		Level l = new Level();
		Snake s = new Snake(0,0,500,l);
		
		assertEquals(GameSettings.SnakeDirection.RIGHT, s.getSnakeDirection());
		s.update();
		
		s.setSnakeDirection(GameSettings.SnakeDirection.LEFT);
		
		assertEquals(GameSettings.SnakeDirection.RIGHT, s.getSnakeDirection());
	}

}
