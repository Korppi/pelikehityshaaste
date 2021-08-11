package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Polygon;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class Asteroid extends GameObject{
	private int points;
	public int speed;
	protected Polygon polHitbox;
	private float stateTime;
	
	public Asteroid(Animation animation, Level level) {
		super(animation, level);
		// TODO Auto-generated constructor stub
		polHitbox = new Polygon();
		points = 20;
		stateTime = 0;
		alive = true;
	}

	@Override
	public void update(float deltaTime) {
		super.update(deltaTime);
		position.y -= speed * slowerFactor * deltaTime;
		if (animation != null){
			stateTime += deltaTime * slowerFactor;
			frame = animation.getKeyFrame(stateTime);
		}
	}

	@Override
	public Polygon getHitboxPol() {
		getHitbox();
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y + hitbox.height * 0.25f,
				hitbox.width * 0.75f + position.x, position.y + hitbox.height * 0.75f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x + hitbox.width * 0.25f,hitbox.height * 0.75f + position.y});
		return polHitbox;
	}

	@Override
	public void kill() {
		alive = false;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int value) {
		points = value;
	}

}
