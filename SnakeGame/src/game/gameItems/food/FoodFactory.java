package game.gameItems.food;

public class FoodFactory {
	
	public static Food createRegularFood(int tileX, int tileY)
	{
		Food f = new Food(tileX, tileY);
		
		return f;
	}
	
	public static Food createPoisonFood(int tileX, int tileY)
	{
		Food f = new Food(tileX, tileY);
		
		return f;
	}

}
