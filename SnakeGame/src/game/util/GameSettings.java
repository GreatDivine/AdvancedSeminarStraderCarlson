package game.util;

public class GameSettings {
	
	public final static int FRAME_WIDTH = 512;
	public final static int FRAME_HEIGHT = 512;
	
	public static final double NANOSECONDS_TO_SECONDS = 1000000000.0; //1.0E9 nanoseconds per 1 second
	public static final double FRAME_RATE = 60; //frames per second
	public static final double UPDATE_TIME = 1.0 / FRAME_RATE; //milliseconds
	
	public static final int TILE_SIZE = 32;
	public static final int GRID_SIZE = 16;
	
	public static final int FOOD_SIZE = 16;
	
	public static enum SnakeDirection
	{
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
}
