package game.main;

public class SnakeGame {
	
	private static Game game;
	
	public static void main(String[] args)
	{
		game = new Game();
		game.init();
		game.gameLoop();
	}

}
