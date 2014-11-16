package game.gameItems.food;

public class FoodFactory {
	
	public static Food createFood(int tileX, int tileY)
	{
		Food f = new Food(tileX, tileY);
		
		return f;
	}

}
