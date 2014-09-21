package unitTests;

import static org.junit.Assert.assertEquals;
import gameItems.Tower;
import gameItems.Walker;
import graphics.Room;

import org.junit.Test;

public class TowerTests {
	
	// Test that we can add a tower to a room
	@Test
	public void testAddTower()
	{
		Room r = new Room();
		
		assertEquals(r.getNumTowers(), 0);
		
		r.addTower(0, 0, 20, 20, 50, 20);
		
		assertEquals(r.getNumTowers(), 1);
	}
	
	// Test to see that the tower will target a walker within its radius
	@Test
	public void testTargetAcquired()
	{
		int towerRadius = 100;
		Tower t = new Tower(0,0,20,20,towerRadius,100);
		
		Walker w = new Walker(10,10); // spawn a new walker within towers target radius
		
		t.checkForTarget(w); // this should set the currentTarget of the tower to the walker
		
		assertEquals(t.getCurrentTarget(), w); // check that the currentTarget is the walker
	}
	
	// Test to see if the target properly untargets a zombie that exits its range
	@Test
	public void testTargetLost()
	{
		int towerRadius = 100;
		Tower t = new Tower(0,0,20,20,towerRadius,100);
		
		Walker w = new Walker(99,0); // spawn a walker at x = 99. Since the walker y is equal to the towers, moving right 2 units will place the walker out of the target radius
		
		t.checkForTarget(w); // tower sees the walker and targets it
		
		w.move("right"); // walker moves 2 units right and out of tower radius
		
		t.checkForTarget(w); // check for target, now not within distance, so set currentTarget to null
		
		assertEquals(t.getCurrentTarget(), null); // check that the target is null
	}
	
	// Test to see if the tower will properly target a new zombie when it loses its current target
	@Test
	public void testSwitchTarget()
	{
		int towerRadius = 100;
		Tower t = new Tower(0,0,20,20,towerRadius,100);
		
		Walker w = new Walker(99,0); //spawn 2 walkers, both in range of the tower
		Walker w2 = new Walker(80, 0);
		
		t.checkForTarget(w); // tower sees the walker and targets it
		t.checkForTarget(w2); // should see that w2 is within range, but another target already exists
		
		assertEquals(t.getCurrentTarget(), w); // double check that w is still the target
		
		t.damageCurrentTarget(); // do 100 damage to w, killing it
		
		t.checkForTarget(w); // check w, see that is in range, but is dead. currentTarget should be null now
		
		assertEquals(t.getCurrentTarget(), null);
		
		t.checkForTarget(w2); // check w2, see that it is in range and alive, and currentTarget is null. target it.
		
		assertEquals(t.getCurrentTarget(), w2);
	}
	
	// Test that we can deal damage to the currently targeted zombie
	@Test
	public void testDamageTarget()
	{
		int towerRadius = 100;
		Tower t = new Tower(0,0,20,20,towerRadius,100);
		
		Walker w = new Walker(10,10);
		
		t.checkForTarget(w);
		
		t.damageCurrentTarget(); // shoot at the current target, damaging it. In this case I am using 100 as the damage, so it should kill the walker.
		
		assertEquals(w.checkIsDead(), true);
	}

}
