package com.fatraven.astroBlasterClonev2.game.gameObjects;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public abstract class GameObject {
	protected Vector2 position;
	protected Animation animation;
	protected TextureRegion frame;
	protected Level level;
	protected float slowerFactor;
	protected int damage;
	public boolean alive;
	public boolean spawnPowerup;
	public static final Random random = new Random();

	protected Rectangle hitbox;
	
	public GameObject(Animation animation, Level level){
		spawnPowerup = false;
		this.animation = animation;
		this.level = level;
		slowerFactor = 1;
		damage = 1;
		hitbox = new Rectangle(0,0,0,0);
	}
	
	public GameObject(TextureRegion pic, Level level){
		position = new Vector2();
		spawnPowerup = false;
		frame = pic;
		this.level = level;
		slowerFactor = 1;

		hitbox = new Rectangle(0,0,0,0);
	}
	
	public void setDamage(int value){
		damage = value;
	}
	
	public void setSlowerFactor(float value){
		slowerFactor = value;
	}
	
	public float getSlowerFactor(){
		return slowerFactor;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public void addToCurrentPosition(float x, float y){
		this.position.x += x;
		this.position.y += y;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void update(float deltaTime){
		if (position.y <= -50) alive = false;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(frame, position.x, position.y, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0);
	}
	
	public boolean testCollision(GameObject object){
		getHitbox();
		if (Intersector.overlaps(object.getHitboxRectangle(), hitbox)){
			//Gdx.app.debug("gameobject", "hitbox osuma");
			if (Intersector.overlapConvexPolygons(object.getHitboxPol(), getHitboxPol())){
				//Gdx.app.debug("gameobject", "polygon osuma");
				return true;
			}
		}
		return false;
	}
	
	private Rectangle getHitboxRectangle() {
		getHitbox();
		return hitbox;
	}

	public boolean isAlive(){
		return alive;
	}
	
	public abstract Polygon getHitboxPol();
	
	public void getHitbox(){
		hitbox.x = position.x;
		hitbox.y = position.y;
		hitbox.width = frame.getRegionWidth();
		hitbox.height = frame.getRegionHeight();
	}
	
	public abstract int getPoints();
	public abstract void setPoints(int value);

	public abstract void kill();
}
