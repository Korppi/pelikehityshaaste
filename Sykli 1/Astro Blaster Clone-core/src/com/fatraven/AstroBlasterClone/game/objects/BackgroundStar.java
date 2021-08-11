package com.fatraven.AstroBlasterClone.game.objects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.Level;
import com.fatraven.AstroBlasterClone.util.Constants;

public class BackgroundStar extends GameObject{
	private float velocity;
	private float slowerFactor;
	
	public BackgroundStar(Level level, Vector2 pos) {
		super();
		
		position = pos;
		
		
		init();
	}
	
	public void setVelocity(float vel){
		velocity = vel;
	}
	
	public void setSlower(float s){
		slowerFactor = s;
	}
	
	private void init(){
		slowerFactor = 1;
		Random r = new Random();
		velocity = r.nextInt(20) + 20;
		rotation = r.nextInt(360) + 1;
		if (r.nextInt(2) == 1){
			scale.set(0.12f, 0.12f);
		} else {
			scale.set(0.17f, 0.17f);
		}
		
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.decorations.star.getRegionWidth() * scale.x, 
				Assets.instance.decorations.star.getRegionHeight() * scale.y);
		origin.x = hitbox.width * 0.5f * scale.x;
		origin.x = hitbox.height * 0.5f * scale.y;

	}
	
	@Override
	public void update(float deltaTime){
		position.y -= deltaTime * velocity * slowerFactor;
		if (isOutOfLowerScreen()){
			position.y = Constants.VIEWPORT_HEIGHT + 10;
		}
	}

	private boolean isOutOfLowerScreen() {
		if (position.y < -5){
			return true;
		}
		return false;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(
				Assets.instance.decorations.star, 
				position.x, position.y, 
				origin.x, origin.y, 
				hitbox.width, hitbox.height, scale.x, scale.y, rotation);
	}

}
