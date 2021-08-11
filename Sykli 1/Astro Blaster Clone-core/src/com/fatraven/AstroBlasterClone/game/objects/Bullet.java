package com.fatraven.AstroBlasterClone.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.util.Constants;

public class Bullet extends GameObject implements Poolable{
	private int damage;
	private int velocity;
	private boolean alive;
	private int time;
	private float slowerFactor;
	
	public Bullet(){
		super();
		alive = false;
		scale.set(0.1f, 0.1f);
	}
	
	public void init(float x, float y){
		alive = true;
		damage = 1;
		velocity = 30;
		position.x = x;
		position.y = y;
		time = 1;
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.shipsAndShit.bullet.getRegionWidth() * scale.x, 
				Assets.instance.shipsAndShit.bullet.getRegionHeight() * scale.y);
		slowerFactor = 1;
	}
	
	public Polygon getHitbox(){
		polHitbox.setVertices(new float[]{
				position.x,position.y,
				hitbox.width + position.x, position.y,
				hitbox.width + position.x, hitbox.height + position.y,
				position.x,hitbox.height + position.y});
		return polHitbox;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public void setVelocity(int vel){
		velocity = vel;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	
	public void update(float deltaTime){
		if (alive){
			position.y += velocity * time * deltaTime * slowerFactor;
			if (isOutOfScreen()) alive = false;
			hitbox.x = position.x;
			hitbox.y = position.y;
		}
	}

	private boolean isOutOfScreen() {
		if (position.y >= 0 && position.y <= Constants.VIEWPORT_HEIGHT) return false;
		return true;
	}

	@Override
	public void reset() {
		position.set(0, 0);
		alive = false;
		slowerFactor = 1;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(Assets.instance.shipsAndShit.bullet, 
				position.x, position.y, origin.x, origin.y, 
				Assets.instance.shipsAndShit.bullet.getRegionWidth(), 
				Assets.instance.shipsAndShit.bullet.getRegionHeight(), scale.x, scale.y, rotation);
	}

	public boolean isAlive() {
		return alive;
	}

	public void kill() {
		alive = false;
	}

	public void setSlowerTimer(float f) {
		slowerFactor = f;
	}

}
