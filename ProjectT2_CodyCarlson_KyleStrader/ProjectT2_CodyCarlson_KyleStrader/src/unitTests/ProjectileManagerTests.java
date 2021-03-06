package unitTests;

import static org.junit.Assert.assertEquals;
import gameItems.tower.Projectile;
import gameItems.tower.ProjectileManager;
import gameItems.zombie.Walker;
import gameItems.zombie.Zombie;

import java.awt.Color;
import java.awt.geom.Point2D;

import org.junit.Test;

import player.Player;
import tiles.Level;
import util.GameSettings;
import waves.Wave.mZombieType;
import waves.WaveManager;

public class ProjectileManagerTests 
{
	
	@Test
	public void testAddProjectile()
	{
		Zombie z = new Zombie(0,0,0,0,0,0);
		
		ProjectileManager pm = new ProjectileManager();
		
		assertEquals(pm.getNumProjectiles(), 0);
		
		pm.addProjectile(new Projectile(0, 0, 0, z, Color.BLUE, 3, new Point2D.Float(0.0f, 0.0f)));
		
		assertEquals(pm.getNumProjectiles(), 1);
	}
	
	@Test
	public void testProjectileMovement()
	{
		Zombie z = new Zombie(1,0,0,0,1,0);
		
		Projectile p = new Projectile(0,0,0,z,Color.BLUE,3, new Point2D.Float(0.0f, 0.0f)); // create a projectile that is moving directly right (velocity of (1,0))
		
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
		
		pm.addProjectile(new Projectile(0, 0, 0, z, Color.BLUE, 3, new Point2D.Float(0.0f, 0.0f)));
		
		assertEquals(pm.getNumProjectiles(), 1);
		
		pm.removeProjectile(pm.getProjectile(0));
		
		assertEquals(pm.getNumProjectiles(), 0);
	}
	
	// Test that a projectile is removed when it moves offscreen
	@Test
	public void testOffScreen()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level,p);
		Zombie z = new Zombie(GameSettings.FRAME_WIDTH,0,0,0,0,0); // temporary zombie since pm.update wants a zombie passed to it
		
		ProjectileManager pm = new ProjectileManager();
		
		pm.addProjectile(new Projectile(GameSettings.FRAME_WIDTH - 1, 0, 1, z, Color.BLUE, 3, new Point2D.Float(0.0f, 0.0f))); // add a projectile that is right next to the edge of the screen with a rightward velocity
		
		assertEquals(pm.getNumProjectiles(), 1);
		
		pm.update(1, z, wm); // update the manager to update every projectile it holds, in our case just the one
		
		// the update should have moved the proj 7 units right, putting it offscreen. Then the manager should notice this and delete.
		
		assertEquals(pm.getNumProjectiles(), 0); // as such, we should now have 0 projectiles
	}
	
	@Test
	public void testProjectileCollision()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level,p);
		wm.addTemplateWave();
		wm.getWave(0).addZombie(10, 10, mZombieType.WALKER, p, level);
		wm.getWave(0).forceSpawnZombies();
		ProjectileManager pm = new ProjectileManager();
		
		Zombie w = (Walker)wm.getWave(0).getZombie(0);
		
		pm.addProjectile(new Projectile(10, 10, 50, w, Color.BLUE, 3, new Point2D.Float(0.0f, 0.0f)));
		
		assertEquals(w.getHp(), 100.0f, 0); // before the collision check zombie has full 100 hp
		
		pm.checkBulletCollision(wm, pm.getProjectile(0)); // check for collision and deal damage if it happens
		
		assertEquals(w.getHp(), 50.0f, 0); // shot does 50 damage, so zombie should be at 50 hp
	}

}
