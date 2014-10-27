package unitTests;

import static org.junit.Assert.*;
import gameItems.tower.MachineGunTower;

import org.junit.Test;

public class UpgradeTests {

	
	@Test
	public void testUpgradeDecorator()
	{
		MachineGunTower mgTest = new MachineGunTower(0, 0, 0, 0, 0, null);
		
		assertEquals(mgTest.getDamage(), MachineGunTower.MG_DAMAGE);
		
		mgTest = new MachineGunTower(mgTest);
		
		assertEquals(mgTest.getDamage(), MachineGunTower.MG_DAMAGE + (int)(MachineGunTower.MG_DAMAGE * .25));
	}

}
