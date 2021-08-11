package com.fatraven.astroBlasterClonev2.game.levels;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;

public abstract class Squadron {
	public Array<GameObject> objects; //laiskottaa ja on kiire, siksi public
	protected boolean squadronCleared;
	protected Level level;
	protected Vector2 squadronPosition;
	protected Array<Vector2> points;
	protected int currentPoint;
	
	public Squadron(Level level){
		squadronCleared = false;
		this.level = level;
		objects = new Array<GameObject>();
		points = new Array<Vector2>();
		currentPoint = 0;
	}
	
	public void addPoint(Vector2 point){
		points.add(point);
	}
	
	public boolean isSquadronCleared(){
		return squadronCleared;
	}

	public abstract void update(float deltaTime);
	
	public void draw(SpriteBatch batch){
		if (!squadronCleared){
			for (GameObject object : objects){
				if (object.isAlive()){
					object.draw(batch);
				}
			}
		}
	}

	public void slowDown() {
		for (GameObject o : objects){
			o.setSlowerFactor(0.25f);
			
		}
	}

	public void cancelSLower() {
		for (GameObject o : objects){
			o.setSlowerFactor(1);
		}
	}

	

}
