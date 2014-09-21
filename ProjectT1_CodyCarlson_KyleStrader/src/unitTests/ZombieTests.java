package unitTests;

import static org.junit.Assert.assertEquals;
import gameItems.Fatty;
import gameItems.Tower;
import gameItems.Walker;
import gameItems.Zombie;

import org.junit.Test;

import util.Wave;

public class ZombieTests {
	
	@Test
	public void testZombieMove()
	{
		Walker w = new Walker(0,0);
		
		int wX = (int)w.getX();
		
		assertEquals(wX, 0);
		
		w.move("right"); // move the walker to the right WALKER_SPEED pixels
		
		int expectedX = w.WALKER_SPEED;
		wX = (int)w.getX();
		
		assertEquals(wX, expectedX);
	}
	
	@Test
	public void testAddZombie()
	{
		Wave w = new Wave();
		
		assertEquals(w.getNumZombies(), 0);
		
		w.addZombie(0, 0, Wave.mZombieType.WALKER);
		
		assertEquals(w.getNumZombies(), 1);
	}
	
	@Test
	public void testGetHeight()
	{
		int testHeight = 20;
		Zombie z = new Zombie(0, 0, 20, testHeight, 10, 10);
		
		assertEquals(z.getHeight(), testHeight);
	}
	
	@Test
	public void testGetZombie()
	{
		Wave w = new Wave();
		
		Fatty f = (Fatty)w.addZombie(0,0, Wave.mZombieType.FATTY);
		Fatty differentFatty = new Fatty(100,100);
		
		boolean checkZombSame = (f == w.getZombie(0));
		boolean checkZombDiff = (differentFatty == w.getZombie(0));
		
		assertEquals(checkZombSame, true);
		assertEquals(checkZombDiff, false);
	}
	
	@Test
	public void testDamageZombie()
	{
		Walker w = new Walker(0,0);
		int startHP = Walker.WALKER_HP;
		
		assertEquals(w.getHp(), startHP);
		
		int dmg = 10;
		int newHP = startHP - dmg;
		
		w.takeDamage(dmg);
		
		assertEquals(w.getHp(), newHP);
	}
	
	@Test
	public void testZombieDeath()
	{
		Walker w = new Walker(0,0);
		
		assertEquals(w.checkIsDead(), false); // our brand new zombie should not be dead already
		
		w.takeDamage(100); // walkers have 100 hp, so dealing 100 damage should kill it
		
		assertEquals(w.checkIsDead(), true);
	}
	
	@Test
	public void testIsTargeted()
	{
		int towerRadius = 100;
		Tower t = new Tower(0,0,20,20,towerRadius,100);
		
		Walker w = new Walker(10,10);
		
		t.checkForTarget(w);
		
		assertEquals(w.getIsTargeted(), true);
	}
}
