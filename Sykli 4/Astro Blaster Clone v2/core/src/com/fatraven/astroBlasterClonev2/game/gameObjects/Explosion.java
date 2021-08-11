package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class Explosion extends GameObject implements Poolable{
	private boolean enabled;
	private float stateTime;
	private boolean isPlayer;

	public Explosion(Animation animation, Level level) {
		super(animation, level);
		// TODO Auto-generated constructor stub
		enabled = true;
		stateTime = 0;
		isPlayer = false;
		position = new Vector2();
		
	}

	@Override
	public void update(float deltaTime) {
		stateTime += deltaTime;
		if (animation.isAnimationFinished(stateTime)) enabled = false;
		frame = animation.getKeyFrame(stateTime);
	}

	@Override
	public Polygon getHitboxPol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isEnabled(){
		return enabled;
	}

	@Override
	public void reset() {
		stateTime = 0;
		position.x = 0;
		position.y = 0;
		isPlayer = false;
		enabled = false;
	}
	
	public void setIsPlayer(boolean value){
		isPlayer = value;
	}
	
	public void draw(SpriteBatch batch){
		if (isPlayer){
			batch.draw(frame, position.x, position.y, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), 2, 2, 0);
		} else {
			batch.draw(frame, position.x, position.y, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0);
		}
	}

	public void setEnabled(boolean b) {
		enabled = b;
	}
	
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
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
