package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.levels.Level;
import com.fatraven.astroBlasterClonev2.game.levels.SquadronTwo;

public class EnemyTwo extends Enemy{

	private static float movement;
	private int y;
	private int speed;
	
	public EnemyTwo(TextureRegion pic, Level level, int select) {
		super(pic, level, select);
		// TODO Auto-generated constructor stub
		y = 0;
		speed = 500;
		if (SquadronTwo.levely >= 2) speed = 650;
		spawnPowerup = true;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
		y = (int)position.y - 75;
	}
	
	public void update(float deltaTime){
		super.update(deltaTime);
		if (movement == 0) movement = speed * slowerFactor * deltaTime;
		position.x += movement;
		if (position.x >= 100 && position.y >= y){
			position.y += -movement;
		}
		if (position.x > 900){
			position.x = -50;
			y -= 75;
		}
		movement = 0;
	}

}
