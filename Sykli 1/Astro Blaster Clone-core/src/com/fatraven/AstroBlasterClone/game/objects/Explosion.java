package com.fatraven.AstroBlasterClone.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.fatraven.AstroBlasterClone.game.Assets;

public class Explosion implements Poolable{
	private Vector2 position;
	private Animation animation;
	private float delta;
	private float scale;
	private boolean isPlayer;
	
	public Explosion(){
		
	}
	
	public void init(Array<AtlasRegion> frames){
		animation = new Animation(0.03f, frames);
		delta = 0;
		scale = 0.1f;
		isPlayer = false;
	}
	
	public void setPosition(float x, float y){
		position = new Vector2(x, y);
	}
	
	
	public void update(float deltaTime){
		delta += deltaTime;
	}
	
	public boolean isEnded(){
		return animation.isAnimationFinished(delta);
	}
	
	public void render(SpriteBatch batch){
		if (isPlayer){
			batch.draw(
					animation.getKeyFrame(delta), position.x, position.y, 0, 0, 
					Assets.instance.explosions.explosions.get(0).getRegionWidth(), 
					Assets.instance.explosions.explosions.get(0).getRegionHeight(), scale * 2, scale * 2, 0);
		} else {
			batch.draw(
					animation.getKeyFrame(delta), position.x, position.y, 0, 0, 
					Assets.instance.explosions.explosions.get(0).getRegionWidth(), 
					Assets.instance.explosions.explosions.get(0).getRegionHeight(), scale, scale, 0);
		}
	}
	
	@Override
	public void reset() {
		delta = 0;
		position.setZero();
		scale = 0.1f;
		isPlayer = false;
	}

	public void setScale(boolean f) {
		isPlayer = f;
	}
}
