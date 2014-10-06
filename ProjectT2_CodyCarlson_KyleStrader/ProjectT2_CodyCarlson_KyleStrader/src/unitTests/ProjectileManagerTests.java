package unitTests;

import static org.junit.Assert.assertEquals;
import gameItems.tower.Projectile;
import gameItems.tower.ProjectileManager;
import gameItems.zombie.Walker;
import gameItems.zombie.Zombie;

import org.junit.Test;

import util.GameSettings;

public class ProjectileManagerTests 
{
	
	@Test
	public void testAddProjectile()
	{
		ProjectileManager pm = new ProjectileManager();
		
		assertEquals(pm.getNumProjectiles(), 0);
		
		pm.addProjectile(0, 0, 1, 1);
		
		assertEquals(pm.getNumProjectiles(), 1);
	}
	
	@Test
	public void testProjectileMovement()
	{
		Projectile p = new Projectile(0,0,1,0); // create a projectile that is moving directly right (velocity of (1,0))
		
		// since p's starting pos is 0, the default proj speed is 7, and p is moving right, one movement tick through
		// update should move p to the pos (7,0)
		
		p.update(1);
		
		assertEquals((int)p.getX(), Projectile.PROJECTILE_SPEED);
	}
	
	@Test
	public void testRemoveProjectile()
	{
		ProjectileManager pm = new ProjectileManager();
		
		pm.addProjectile(0, 0, 0, 0);
		
		assertEquals(pm.getNumProjectiles(), 1);
		
		pm.removeProjectile(pm.getProjectile(0));
		
		assertEquals(pm.getNumProjectiles(), 0);
	}
	
	// Test that a projectile is removed when it moves offscreen
	//@Test
	//public void testOffScreen()
	//{
		//ProjectileManager pm = new ProjectileManager();
		
		//pm.addProjectile(GameSettings.FRAME_WIDTH - 1, 0, 1, 0); // add a projectile that is right next to the edge of the screen with a rightward velocity
		
		//assertEquals(pm.getNumProjectiles(), 1);
		
		//Zombie z = new Zombie(0,0,0,0,0,0); // temporary zombie since pm.update wants a zombie passed to it
		
		//pm.update(1); // update the manager to update every projectile it holds, in our case just the one
		
		// the update should have moved the proj 7 units right, putting it offscreen. Then the manager should notice this and delete.
		
		//assertEquals(pm.getNumProjectiles(), 0); // as such, we should now have 0 projectiles
	//}
	
	@Test
	public void testProjectileCollision()
	{
		ProjectileManager pm = new ProjectileManager();
		
		pm.addProjectile(10, 10, 0, 0);
		
		Walker w = new Walker(10,10);
		
		assertEquals(w.getHp(), 100); // before the collision check zombie has full 100 hp
		
		pm.checkBulletCollision(w, pm.getProjectile(0)); // check for collision and deal damage if it happens
		
		assertEquals(w.getHp(), 50); // shot does 50 damage, so zombie should be at 50 hp
	}

}
