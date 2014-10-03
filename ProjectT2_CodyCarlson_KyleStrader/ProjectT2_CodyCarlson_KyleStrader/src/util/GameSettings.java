package util;

import java.awt.Color;
import java.awt.Font;

public class GameSettings 
{

	public static final double FRAME_RATE = 60; //frames per second
	public static final double UPDATE_TIME = 1.0 / FRAME_RATE; //milliseconds
	public static final double NANOSECONDS_TO_SECONDS = 1000000000.0; //1.0E9 nanoseconds per 1 second
	public static final int TIME_BETWEEN_WAVE = 1; //seconds
	public static final int FRAME_WIDTH = 512; //pixels
	public static final int FRAME_HEIGHT = 512; //pixels
	public static final int TOWER_SHOT_DAMAGE = 50; // tower damage per shot
	public static final int WAVES_BEFORE_INCREASE = 4; //number of waves that will pass before the waves become larger
	
	public static final Font FONT = new Font("Courrier", Font.BOLD, 18);
	public static final Color LIFE_COLOR = Color.yellow;
	public static final String LIFE_PREFIX = "\u2764";
	public static final Color CASH_COLOR = Color.green;
	public static final String CASH_PREFIX = " $  ";
	
	public static final int TILE_SIZE = 32;
	public static final int NUM_TILES = (FRAME_WIDTH * FRAME_HEIGHT)  / TILE_SIZE;
}