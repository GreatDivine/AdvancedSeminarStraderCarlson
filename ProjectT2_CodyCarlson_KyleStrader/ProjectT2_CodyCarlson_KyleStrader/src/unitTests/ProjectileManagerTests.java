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
		Zombie z = new Zombie(0,0,0,0,0,0);
		
		ProjectileManager pm = new ProjectileManager();
		
		assertEquals(pm.getNumProjectiles(), 0);
		
		pm.addProjectile(0, 0, 0, z);
		
		assertEquals(pm.getNumProjectiles(), 1);
	}
	
	@Test
	public void testProjectileMovement()
	{
		Zombie z = new Zombie(1,0,0,0,0,0);
		
		Projectile p = new Projectile(0,0,0,z); // create a projectile that is moving directly right (velocity of (1,0))
		
		// since p's starting pos is 0, the default proj speed is 7, and p is moving right, one movement tick through
		// update should move p to the pos (7,0)
		
		p.update(1);
		
		assertEquals((int)p.getPosition().getX(), Projectile.PROJECTILE_SPEED);
	}
	
	@Test
	public void testRemoveProjectile()
	{
		Zombie z = new Zombie(0,0,0,0,0,0);
		
		ProjectileManager pm = new ProjectileManager();
		
		pm.addProjectile(0, 0, 0, z);
		
		assertEquals(pm.getNumProjectiles(), 1);
		
		pm.removeProjectile(pm.getProjectile(0));
		
		assertEquals(pm.getNumProjectiles(), 0);
	}
	
	// Test that a projectile is removed when it moves offscreen
	@Test
	public void testOffScreen()
	{
		Zombie z = new Zombie(GameSettings.FRAME_WIDTH,0,0,0,0,0); // temporary zombie since pm.update wants a zombie passed to it
		
		ProjectileManager pm = new ProjectileManager();
		
		pm.addProjectile(GameSettings.FRAME_WIDTH - 1, 0, 1, z); // add a projectile that is right next to the edge of the screen with a rightward velocity
		
		assertEquals(pm.getNumProjectiles(), 1);
		
		pm.update(1, z); // update the manager to update every projectile it holds, in our case just the one
		
		// the update should have moved the proj 7 units right, putting it offscreen. Then the manager should notice this and delete.
		
		assertEquals(pm.getNumProjectiles(), 0); // as such, we should now have 0 projectiles
	}
	
	@Test
	public void testProjectileCollision()
	{
		ProjectileManager pm = new ProjectileManager();
		
		Walker w = new Walker(10,10);
		
		pm.addProjectile(10, 10, 50, w);
		
		assertEquals(w.getHp(), 100); // before the collision check zombie has full 100 hp
		
		pm.checkBulletCollision(w, pm.getProjectile(0)); // check for collision and deal damage if it happens
		
		assertEquals(w.getHp(), 50); // shot does 50 damage, so zombie should be at 50 hp
	}

}
