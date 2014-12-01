package tests;

import static org.junit.Assert.*;
import game.gameItems.food.Food;
import game.gameItems.food.FoodFactory;
import game.gameItems.food.PoisonFood;
import game.gameItems.food.RegularFood;

import org.junit.Test;

public class FoodTest {
	
	@Test /* Test our class inheritance with food types: Create a base class food object, then make it an instance of RegularFood. Test the return value. 
	 		 Then we change the same object to an instance of Poison food and check the return value to ensure that the processEaten() function is being called correctly.*/
	public void testFoodTypes()
	{
		Food tmpFood;
		
		tmpFood = new RegularFood(0, 0);
		
		assertEquals(100, tmpFood.processEaten());
		
		tmpFood = new PoisonFood(0,0);
		
		assertEquals(-100, tmpFood.processEaten());
	}
	
	@Test /* Test that our factories are creating the food correctly. Use the same test methods as testFoodTypes(), but with the factory instead of simply using new */
	public void testFoodFactory()
	{
		Food tmpFood;
		
		tmpFood = FoodFactory.createRegularFood(0, 0);
		
		assertEquals(100, tmpFood.processEaten());
		
		tmpFood = FoodFactory.createPoisonFood(0, 0);
		
		assertEquals(-100, tmpFood.processEaten());
	}

}
