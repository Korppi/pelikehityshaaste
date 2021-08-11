package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class Bullet extends GameObject implements Poolable {
	private float speed;
	private int damage;
	private float direction;
	private boolean enabled;
	private Polygon polHitbox;

	public Bullet(TextureRegion pic, Level level) {
		super(pic, level);
		speed = 400;
		enabled = false;
		direction = 0;
		damage = 1;
		polHitbox = new Polygon();
	}
	
	public void setDirection(int dir){
		direction = dir;
	}
	
	public void setDamage(int value){
		damage = value;
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setEnabled(boolean value){
		enabled = value;
	}

	public boolean isEnabled(){
		return enabled;
	}

	@Override
	public void update(float deltaTime) {
		if (enabled){
			position.y += speed * direction * deltaTime * slowerFactor;
			if (position.y > 800 || position.y < -10) enabled = false;
		}
	}

	@Override
	public void reset() {
		enabled = false;
		direction = 0;
		damage = 1;
		speed = 500;
		position.x = 0;
		position.y = 0;
		slowerFactor = 1;
	}

	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	@Override
	public Polygon getHitboxPol() {
		getHitbox();
		polHitbox.setVertices(new float[]{
				position.x,position.y,
				hitbox.width + position.x, position.y,
				hitbox.width + position.x, hitbox.height + position.y,
				position.x,hitbox.height + position.y});
		return polHitbox;
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPoints(int value) {
		// TODO Auto-generated method stub
		
	}

}
