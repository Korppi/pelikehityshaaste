package com.fatraven.proroglili.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public abstract class Entity {
	protected Vector2 position;
	protected Animation animation;
	private TextureRegion frame;
	private float stateTime;
	
	public Entity(Vector2 position, Array<TextureRegion> keyFrames){
		this.position = position;
		animation = new Animation(1f, keyFrames);
		animation.setPlayMode(PlayMode.LOOP);
		stateTime = 0;
	}
	
	public void update(float deltaTime){
		stateTime += deltaTime;
		frame = animation.getKeyFrame(stateTime);
	}
	public void draw(SpriteBatch batch){
		batch.draw(frame, 
				position.x, position.y, 
				frame.getRegionWidth() / 2, 
				frame.getRegionHeight() / 2, 
				frame.getRegionWidth(), 
				frame.getRegionHeight(), 1, 1, 0);
	}
}
