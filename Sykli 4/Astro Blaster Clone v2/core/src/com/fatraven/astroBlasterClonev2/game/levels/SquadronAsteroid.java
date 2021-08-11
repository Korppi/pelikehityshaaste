package com.fatraven.astroBlasterClonev2.game.levels;

import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Asteroid;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;

public class SquadronAsteroid extends Squadron{
	int luku;
	public static int levely = 0;
	public SquadronAsteroid(Level level) {
		super(level);
		levely++;
		Random r = new Random();
		Asteroid a;
		int asteroids = 75;
		if (levely >= 2) asteroids = 110;
		int maxx = 800;
		int miny = 610;
		luku = 0;
		int epoints = 5;
		if (levely >= 2) epoints = 10;
		
		for (int i = 0; i < asteroids; i++){
			Animation ani;
			if (i % 2 == 0){
				ani = new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f , Assets.instance.misc.brownAsteroids);
			} else if (i % 5 == 0){
				ani = new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f , Assets.instance.misc.greyAsteroids);
			} else {
				ani = new Animation(r.nextFloat() * (0.2f - 0.03f) + 0.03f , Assets.instance.misc.redAsteroids);
			}
			ani.setPlayMode(PlayMode.LOOP);
			a = new Asteroid(ani, level);
			a.setPosition(new Vector2(r.nextInt(maxx), r.nextInt(miny * 2) + miny));
			a.setPoints(epoints); 
			if (i % 5 == 0) a.setPoints(50);
			a.speed = r.nextInt(200) + 100;
			objects.add(a);
		}
		squadronCleared = false;
	}

	@Override
	public void update(float deltaTime) {
		squadronCleared = true;
		luku = 0;
		for (GameObject o : objects){
			if (o.isAlive()){
				squadronCleared = false;
				o.update(deltaTime);
				luku++;
			}
		}
		
	}

}
