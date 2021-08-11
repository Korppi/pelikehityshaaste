package com.fatraven.astroBlasterClonev2.game.levels;

import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.EnemyThree;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;

public class SquadronThree extends Squadron {
	public static int levely = 0;
	public SquadronThree(Level level) {
		super(level);
		levely++;
		int enemies = 3;
		int epoints = 30;
		if (levely >= 2) epoints = 60;
		if (levely >= 2) enemies *= 2;
		for (int i = 0; i < enemies; i++){
			EnemyThree e = new EnemyThree(Assets.instance.shipsAndShit.beetle, level, 1);
			e.setPoints(epoints);
			e.setPosition(new Vector2(200, 1000 + i * 70));
			e.addPoint(new Vector2(500, 200));
			e.addPoint(new Vector2(600, 100));
			e.addPoint(new Vector2(200, 150));
			e.addPoint(new Vector2(250, 200));
			e.addPoint(new Vector2(500, 500));
			e.addPoint(new Vector2(400, 400));
			e.addPoint(new Vector2(300, 300));
			e.addPoint(new Vector2(400, 400));
			e.addPoint(new Vector2(500, 500));
			e.addPoint(new Vector2(50, 600));
			e.addPoint(new Vector2(600, 150));
			e.addPoint(new Vector2(520, 220));
			e.addPoint(new Vector2(200, 300));
			e.addPoint(new Vector2(600, 210));
			e.addPoint(new Vector2(50, 250));
			e.addPoint(new Vector2(510, 300));
			objects.add(e);
		}
	}

	@Override
	public void update(float deltaTime) {
		squadronCleared = true;
		for (GameObject o : objects){
			if (o.isAlive()){
				squadronCleared = false;
				o.update(deltaTime);
			}
		}
	}

}
