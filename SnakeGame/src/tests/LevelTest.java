package tests;

import static org.junit.Assert.*;
import game.gameItems.food.Food.FoodType;
import game.gameItems.level.Level;
import game.gameItems.player.Player;

import org.junit.Test;

public class LevelTest {
	
	@Test
	public void addFoodTest()
	{
		Level l = new Level();
		
		assertFalse(l.hasFood(0, 0));
		
		l.spawnFoodOnTile(0, 0, FoodType.REGULAR);
		
		assertTrue(l.hasFood(0, 0));
	}
	
	@Test
	public void removeFoodTest()
	{
		Level l = new Level();
		
		assertFalse(l.hasFood(0,0));
		
		l.spawnFoodOnTile(0, 0, FoodType.REGULAR);
		
		assertTrue(l.hasFood(0,0));
		
		l.removeFoodOnTile(0, 0);
		
		assertFalse(l.hasFood(0,0));
	}
	
	@Test
	public void testLevelUp()
	{
		Level l = new Level();
		Player p = new Player(l, 0);
		
		assertEquals(1, p.getLevel());
		
		p.modifyScore(500);
		p.update();
		
		assertEquals(2, p.getLevel());
	}

}
