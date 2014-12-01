package game.gameItems.food;

public class FoodFactory {
	
	public static RegularFood createRegularFood(int tileX, int tileY)
	{
		RegularFood f = new RegularFood(tileX, tileY);
		
		return f;
	}
	
	public static PoisonFood createPoisonFood(int tileX, int tileY)
	{
		PoisonFood f = new PoisonFood(tileX, tileY);
		
		return f;
	}

}
