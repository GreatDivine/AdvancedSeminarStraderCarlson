package gameItems.tower;

import gameItems.zombie.Zombie;

import java.awt.Color;
import java.awt.geom.Point2D;

public class RocketProjectile extends Projectile
{
	public static final Color COLOR = Color.BLACK;
	public static final int SIZE = 10;

	public RocketProjectile(int xPos, int yPos, int dmg, Zombie target, Point2D.Float vel) 
	{
		super(xPos, yPos, dmg, target, COLOR, SIZE, vel);
	}
}
