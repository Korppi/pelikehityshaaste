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
import com.badlogic.gdx.utils.Array;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.Level;
import com.fatraven.AstroBlasterClone.game.objects.GameObject;

public class LevelOneEnemy extends GameObject implements IEnemy{
	private Level level;
	private Array<Vector2> points;
	private int currentPoint;
	private boolean alive;
	private int velocity;
	private Vector2 speedFactor;
	private float slowerFactor;
	private boolean xReady;
	private boolean yReady;
	private int pointsForKilling;
	private float shootingCooldown;
	private float currentShootingDc;
	private boolean useAlternativeGraph;
	
	public LevelOneEnemy(Level level, Vector2 pos){
		super();
		this.level = level;
		scale.set(0.1f, 0.1f);
		position = pos;
		init();
	}
	
	@Override
	public void addPoint(Vector2 point){
		points.add(point);
	}

	private void init() {
		useAlternativeGraph = false;
		points = new Array<Vector2>();
		currentPoint = 0;
		alive = true;
		velocity = 20;
		speedFactor = new Vector2(1, 1);
		slowerFactor = 1;
		//calculateMovement();
		xReady = false;
		yReady = false;
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.shipsAndShit.enemyOne.getRegionWidth() * scale.x,
				Assets.instance.shipsAndShit.enemyOne.getRegionHeight() * scale.y);
		pointsForKilling = 10;
		Random r = new Random();
		shootingCooldown = 1 + r.nextFloat() * ((1 + 10) - 1);
		currentShootingDc = shootingCooldown;
	}
	
	public void calculateMovement(){
		float distanceX = position.x - points.get(currentPoint).x;
		float distanceY = position.y - points.get(currentPoint).y;
		int xFactor = 1; //lol, se telkkarijuttu
		int yFactor = 1; //kertoimia siis onko positiivinen vai negatiivinen kerroin
		if (position.x > points.get(currentPoint).x){
			xFactor = -1;
		} 
		if (position.y > points.get(currentPoint).y){
			yFactor = -1;
		}
		
		if (Math.abs(distanceX) > Math.abs(distanceY)){
			speedFactor.x = 1;
			speedFactor.y = Math.abs(distanceY) / Math.abs(distanceX);
		} else if (Math.abs(distanceX) < Math.abs(distanceY)){
			speedFactor.x = Math.abs(distanceX) / Math.abs(distanceY);
			speedFactor.y = 1;
		} else {
			speedFactor.set(1, 1);
		}
		
		speedFactor.x *= xFactor;
		speedFactor.y *= yFactor;
	}

	@Override
	public void render(SpriteBatch batch) {
		/*batch.draw(Assets.instance.shipsAndShit.enemyOne, 
				position.x, position.y, origin.x, origin.y, 
				Assets.instance.shipsAndShit.enemyOne.getRegionWidth(), 
				Assets.instance.shipsAndShit.enemyOne.getRegionHeight(), scale.x, scale.y, rotation);*/
		if (alive){
			if (!useAlternativeGraph){
				batch.draw(Assets.instance.shipsAndShit.enemyOne, 
						position.x, position.y, origin.x, origin.y, 
						Assets.instance.shipsAndShit.enemyOne.getRegionWidth(), 
						Assets.instance.shipsAndShit.enemyOne.getRegionHeight(), scale.x, scale.y, rotation);
			
			} else {
				batch.draw(Assets.instance.shipsAndShit.enemyThree, 
						position.x, position.y, origin.x, origin.y, 
						Assets.instance.shipsAndShit.enemyOne.getRegionWidth(), 
						Assets.instance.shipsAndShit.enemyOne.getRegionHeight(), scale.x, scale.y, rotation);
			}
			
		}
	}
	
	public void update(float deltaTime){
		position.x += velocity * deltaTime * speedFactor.x * slowerFactor;
		position.y += velocity * deltaTime * speedFactor.y * slowerFactor;
		if (position.x >= points.get(currentPoint).x && speedFactor.x >= 0){
			speedFactor.x = 0;
			xReady = true;
		} else if (position.x <= points.get(currentPoint).x && speedFactor.x <= 0){
			speedFactor.x = 0;
			xReady = true;
		}
		if (position.y >= points.get(currentPoint).y && speedFactor.y >= 0){
			speedFactor.y = 0;
			yReady = true;
		} else if (position.y <= points.get(currentPoint).y && speedFactor.y <= 0){
			speedFactor.y = 0;
			yReady = true;
		}
		
		if (yReady && xReady){
			currentPoint++;
			currentPoint = currentPoint % points.size;
			yReady = false;
			xReady = false;
			calculateMovement();
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
	public boolean isAlive() {
		return alive;
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
		useAlternativeGraph = b;
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
