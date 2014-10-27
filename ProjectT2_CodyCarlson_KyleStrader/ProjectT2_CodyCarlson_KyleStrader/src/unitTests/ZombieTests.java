package unitTests;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;

import gameItems.tower.MachineGunTower;
import gameItems.zombie.Fatty;
import gameItems.zombie.Walker;

import org.junit.Test;

import player.Player;
import tiles.Level;
import waves.Wave;
import waves.WaveManager;

public class ZombieTests 
{
	
	@Test
	public void testZombieMove()
	{
		Walker w = new Walker(0,0);
		
		int wX = (int)w.getPosition().getX();
		
		assertEquals(wX, 0);
		
		w.move(new Point2D.Float(1, 0)); // move the walker to the right WALKER_SPEED pixels
		
		wX = (int)w.getPosition().getX();
		
		assertEquals(wX, Walker.WALKER_SPEED);
	}
	
	@Test
	public void testAddZombie()
	{
		Wave w = new Wave();
		Player p = new Player();
		Level level = new Level();
		
		assertEquals(w.getNumZombies(), 0);
		
		w.addZombie(0, 0, Wave.mZombieType.WALKER, p, level);
		
		assertEquals(w.getNumZombies(), 1);
	}
	
	@Test
	public void testGetZombie()
	{
		Wave w = new Wave();
		Player p = new Player();
		Level level = new Level();
		
		Fatty f = (Fatty)w.addZombie(0,0, Wave.mZombieType.FATTY, p, level);
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
		
		assertEquals(w.getHp(), startHP, 0);
		
		float dmg = 10;
		float newHP = startHP - dmg;
		
		w.takeDamage((int)dmg);
		
		assertEquals(w.getHp(), newHP, 0);
	}
	
	@Test
	public void testZombieDeath()
	{
		Walker w = new Walker(0,0);
		
		assertEquals(w.isDead(), false); // our brand new zombie should not be dead already
		
		w.takeDamage(100); // walkers have 100 hp, so dealing 100 damage should kill it
		
		assertEquals(w.isDead(), true);
	}
	
	@Test
	public void testIsTargeted()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level,p);
		int towerRadius = 100;
		MachineGunTower t = new MachineGunTower(0,0,20,20,towerRadius, wm);
		
		Walker w = new Walker(10,10);
		
		t.checkIfTargettable(w);
		
		assertEquals(w.getIsTargeted(), true);
	}
	
	@Test
	public void testZombieRemoval()
	{
		Wave w = new Wave();
		Player p = new Player();
		Level level = new Level();
		
		w.addZombie(-1, 0, Wave.mZombieType.WALKER, p, level); // add a walker at the edge of the frame width, any movement should push us off
		
		assertEquals(w.getNumZombies(), 1);
		
		w.getZombie(0).move(new Point2D.Float(1, 0));
		
		w.update(1); // update the wave, causing the walker to step off screen and get deleted
		
		assertEquals(w.getNumZombies(), 0);
		
		Walker tmpWalker = (Walker)w.addZombie(0, 0, Wave.mZombieType.WALKER, p, level); // create another walker
		
		assertEquals (w.getNumZombies(), 1);
		
		tmpWalker.takeDamage(100); // kill the walker
		
		w.update(1); // update wave so it sees that the walker is dead
		
		assertEquals(w.getNumZombies(), 0); // now that the only zombie has been killed it should have been removed and we should have 0
	}
}
