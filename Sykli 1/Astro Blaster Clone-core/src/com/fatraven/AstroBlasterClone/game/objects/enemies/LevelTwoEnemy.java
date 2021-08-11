package com.fatraven.AstroBlasterClone.game.objects.enemies;

import java.util.Random;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.Level;
import com.fatraven.AstroBlasterClone.game.objects.GameObject;
import com.fatraven.AstroBlasterClone.util.Constants;

public class LevelTwoEnemy extends GameObject implements IEnemy{
	private Level level;
	private boolean alive;
	private int velocity;
	private Vector2 speedFactor;
	private float slowerFactor;
	private int pointsForKilling;
	private float shootingCooldown;
	private float currentShootingDc;
	private float oldY;
	private boolean changedAlready;
	
	public LevelTwoEnemy(Level level, Vector2 pos) {
		super();
		this.level = level;
		scale.set(0.1f, 0.1f);
		position = pos;
		init();
	}
	
	private void init() {
		alive = true;
		velocity = 50;
		speedFactor = new Vector2(1, 1);
		slowerFactor = 1;
		//calculateMovement();
		//xReady = false;
		//yReady = false;
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.shipsAndShit.enemyOne.getRegionWidth() * scale.x,
				Assets.instance.shipsAndShit.enemyOne.getRegionHeight() * scale.y);
		pointsForKilling = 15;
		Random r = new Random();
		shootingCooldown = 1 + r.nextFloat() * ((1 + 9) - 1);
		currentShootingDc = shootingCooldown;
		oldY = 0;
		changedAlready = false;
	}

	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void addPoint(Vector2 point) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Polygon getHitbox(){
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y,
				hitbox.width + position.x, position.y + hitbox.height * 0.75f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x,hitbox.height * 0.75f + position.y});
		return polHitbox;
	}

	@Override
	public void calculateMovement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shapeRender(Camera cam) {
		ShapeRenderer r = new ShapeRenderer();
		r.setProjectionMatrix(cam.combined);
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitbox().getVertices());
		r.end();
	}

	@Override
	public void kill() {
		alive = false;
		Assets.instance.sounds.hurt.play();
		Assets.instance.sounds.explosion.play();
	}

	@Override
	public int getPoints() {
		return pointsForKilling;
	}
	
	public void update(float deltaTime){
		position.x += velocity * deltaTime * speedFactor.x * slowerFactor;
		if (position.x > Constants.VIEWPORT_WIDTH + 5){
			position.x = -15;
			changedAlready = false;
		} 
		if (position.x >= 10 && !changedAlready){
			changedAlready = true;
			oldY = position.y;
		}
		if (changedAlready){
			if (position.y >= oldY - 6){
				position.y -= velocity * deltaTime * 0.25f * speedFactor.x * slowerFactor;
			}
		}
		
		hitbox.x = position.x;
		hitbox.y = position.y;
		
		currentShootingDc -= deltaTime * slowerFactor;
		if (currentShootingDc <= 0){
			level.fireBullet(false, position.x + hitbox.width * 0.5f, position.y, 1);
			currentShootingDc = shootingCooldown;
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		if (alive){
			batch.draw(Assets.instance.shipsAndShit.enemyTwo, 
					position.x, position.y, origin.x, origin.y, 
					Assets.instance.shipsAndShit.enemyOne.getRegionWidth(), 
					Assets.instance.shipsAndShit.enemyOne.getRegionHeight(), scale.x, scale.y, rotation);
		
		}
	}

	@Override
	public void setSlowTimer(float f) {
		slowerFactor = f;
	}

	@Override
	public float getSlowerTimer() {
		return slowerFactor;
	}

	@Override
	public void setVelocity(int i) {
		velocity = i;
	}

	@Override
	public void setPoints(int i) {
		pointsForKilling = i;
	}

	@Override
	public void useAlternativeGraph(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addAnimation(Animation a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
