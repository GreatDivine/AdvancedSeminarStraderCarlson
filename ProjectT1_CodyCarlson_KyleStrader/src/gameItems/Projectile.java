package gameItems;

import java.awt.geom.Point2D;

public class Projectile extends GameItem {
	private Point2D.Float mVelocity;
	private Point2D.Float mPosition;
	
	public Projectile(Point2D.Float pos){
		mVelocity = new Point2D.Float();
		mPosition = pos;
	}
	
	public void update(){
		mPosition.setLocation(mVelocity.getX(), mVelocity.getY());
	}
}
