package com.fatraven.astroBlasterClonev2.game.levels;

import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Enemy;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;

public class SquadronOne extends Squadron{
	private Vector2 speedFactor;
	private float speed;
	private float slowerFactor;
	private boolean xReady;
	private boolean yReady;
	public static int levely = 0;
	
	public SquadronOne(Level level){
		super(level);
		levely++;
		slowerFactor = 1;
		yReady = false;
		xReady = false;
		speedFactor = new Vector2();
		speed = 220;
		squadronPosition = new Vector2(5, 900);
		int x = 5;
		int y = 900;
		int enemies = 17;
		int epoints = 20;
		if (levely >= 2) epoints = 40;
		//int thingi = levely;
		int juttu = 0;
		if (levely >= 2) juttu = 20;
		int modifier = 0;
		for (int i = 0; i < enemies; i++){
			objects.add(new Enemy(Assets.instance.shipsAndShit.beam, level, 1));
			objects.get(i).setDamage(1);
			objects.get(i).setPoints(epoints);
			objects.get(i).spawnPowerup = true;
			if (i == 6){
				x += 32;
				if (levely >= 2) x += 10;
				y += 70;
				modifier = 0;
			}
			else if (i == 11){
				x -= 32;
				if (levely >= 2) x -= 10;
				y += 70;
				modifier = 0;
			}
			objects.get(i).setPosition(new Vector2(x + (66 - juttu) * levely * modifier, y));
			modifier++;
		}
	}
	
	public void slowDown() {
		for (GameObject o : objects){
			o.setSlowerFactor(0.25f);
			slowerFactor = 0.25f;
		}
	}
	
	public void cancelSLower() {
		for (GameObject o : objects){
			o.setSlowerFactor(1);
			slowerFactor = 1;
		}
	}

	@Override
	public void update(float deltaTime) {
		if (!squadronCleared){
			squadronPosition.x += speed * deltaTime * speedFactor.x * slowerFactor;
			squadronPosition.y += speed * deltaTime * speedFactor.y * slowerFactor;
			if (squadronPosition.x >= points.get(currentPoint).x && speedFactor.x >= 0){
				speedFactor.x = 0;
				xReady = true;
			} else if (squadronPosition.x <= points.get(currentPoint).x && speedFactor.x <= 0){
				speedFactor.x = 0;
				xReady = true;
			}
			if (squadronPosition.y >= points.get(currentPoint).y && speedFactor.y >= 0){
				speedFactor.y = 0;
				yReady = true;
			} else if (squadronPosition.y <= points.get(currentPoint).y && speedFactor.y <= 0){
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
			squadronCleared = true;
			for (GameObject object : objects){
				if (object.isAlive()){
					object.update(deltaTime);
					object.addToCurrentPosition(
							speed * deltaTime * speedFactor.x * slowerFactor, 
							speed * deltaTime * speedFactor.y * slowerFactor);
					squadronCleared = false;
				}
			}
		}
	}

	private void calculateMovement() {
		float distanceX = squadronPosition.x - points.get(currentPoint).x;
		float distanceY = squadronPosition.y - points.get(currentPoint).y;
		int xFactor = 1; //lol, se telkkarijuttu
		int yFactor = 1; //kertoimia siis onko positiivinen vai negatiivinen kerroin
		if (squadronPosition.x > points.get(currentPoint).x){
			xFactor = -1;
		} 
		if (squadronPosition.y > points.get(currentPoint).y){
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
