package game.util;

public class GameSettings {
	
	public final static int FRAME_WIDTH = 640;
	public final static int FRAME_HEIGHT = 480;
	
	public static final double NANOSECONDS_TO_SECONDS = 1000000000.0; //1.0E9 nanoseconds per 1 second
	public static final double FRAME_RATE = 60; //frames per second
	public static final double UPDATE_TIME = 1.0 / FRAME_RATE; //milliseconds
	
	public static enum SnakeDirection
	{
		UP,
		DOWN,
		RIGHT,
		LEFT
	}
}
