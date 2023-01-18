package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

//make abstract so we can store values
public abstract class Entity {
	
	//protected so only the class that extends entity can use variables
	protected float x,y;
	protected int width, height;
	protected Rectangle2D.Float hitbox; //for collision detection

	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		
	}
	//Methods (called by Player model)
	
	//will draw hitbox around character
	protected void drawHitbox(Graphics g) {
		
		g.setColor(Color.PINK);//for debugging the hitbox
		g.drawRect((int)hitbox.x, (int)hitbox.y,  (int)hitbox.width,  (int)hitbox.height);
	}
	
	protected void initHitbox(float x, float y, int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}
	
	//update hitbox
//	protected void updateHitbox() {
//		hitbox.x = (int)x;
//		hitbox.y = (int)y;
//	}
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
}

