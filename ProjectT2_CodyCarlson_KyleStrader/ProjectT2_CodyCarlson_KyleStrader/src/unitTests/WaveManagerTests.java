package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import player.Player;
import tiles.Level;
import waves.Wave;
import waves.WaveManager;

public class WaveManagerTests 
{
	
	@Test
	public void testAddWaves()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level,p);
		
		assertEquals(wm.getNumWavesSpawned(), 0); // we have a brand new wave manager, which automatically spawns a wave when it is first created, so our wave count should be 1
		
		wm.addWave();
		
		assertEquals(wm.getNumWavesSpawned(), 1); // as we spawn more waves the wavesSpawned count should increase
		
		wm.addWave();
		
		assertEquals(wm.getNumWavesSpawned(), 2);
	}
	
	
	@Test
	public void testPopulateWave()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level, p);
		
		Wave w = new Wave();
		
		assertEquals(w.getNumZombies(), 0);
		
		wm.populateWave(w); //populating the wave should add one zombie of each type
		
		assertEquals(w.getNumZombies(), 4); // check to make sure that the wave now has 4 total zombies in it
	}
	
	@Test
	public void testGetWave()
	{
		Level level = new Level();
		Player p = new Player();
		WaveManager wm = new WaveManager(level, p);
		
		Wave w = wm.addWave();
		wm.addWave();
		
		boolean wavesAreEqual = (w == wm.getWave(0)); // since the wavemanager adds a wave automatically, our new wave should be in the 2nd index, 1.
		boolean wavesAreNotEqual = (w == wm.getWave(1)); // as such, the first index should not equal w
		
		assertEquals(wavesAreEqual, true);
		assertEquals(wavesAreNotEqual, false);
	}

}
