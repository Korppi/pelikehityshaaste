package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class EnemyThree extends Enemy{

	private float velocity;
	private Vector2 speedFactor;
	private int currentPoint;
	private Array<Vector2> points;
	private boolean xReady;
	private boolean yReady;

	public EnemyThree(TextureRegion pic, Level level, int select) {
		super(pic, level, select);
		// TODO Auto-generated constructor stub
		points = new Array<Vector2>();
		xReady = false;
		yReady = false;
		speedFactor = new Vector2();
		velocity = 500;
		spawnPowerup = true;
	}
	
	public void addPoint(Vector2 p){
		points.add(p);
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		
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

}
