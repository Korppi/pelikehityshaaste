package com.fatraven.astroBlasterClonev2.game.levels;

import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.EnemyTwo;
import com.fatraven.astroBlasterClonev2.game.gameObjects.GameObject;

public class SquadronTwo extends Squadron{
	/*private float slowerFactor;
	private boolean createNewPoints;
	private int y;
	private int add;
	private float speed;
	private boolean isGoingDownAllowed;
	private int count;*/
	public static int levely = 0;
	
	public SquadronTwo(Level level) {
		super(level);
		levely++;
		/*slowerFactor = 1;
		isGoingDownAllowed = true;
		createNewPoints = false;
		speed = 100;
		add = 100;
		y = 450;*/
		int enemies = 12;
		int plus = 0;
		int plos = 0;
		//int minus = 6;
		int epoints = 25;
		if (levely >= 1) epoints = 55;
		Vector2 posi = new Vector2(-10, 520);
		for (int i = 0; i < enemies; i++){
			objects.add(new EnemyTwo(Assets.instance.shipsAndShit.turret, level, 2));
			objects.get(i).setPoints(epoints);
			if (i > 5){
				objects.get(i).setPosition(new Vector2(posi.x - (i - 6) * 104 + 140 - plos, posi.y + plus));
			} else {
				objects.get(i).setPosition(new Vector2(posi.x - i * 104 + 140 - plos, posi.y + plus));
			}
			
			if (i == 5) {
				plus += 100;
				plos = 50;
			}
		}
		//count  = objects.size;
	}
	
	@Override
	public void update(float deltaTime) {
		if (!squadronCleared){
			squadronCleared = true;
			for (GameObject o : objects) {
				if (o.isAlive()){
					squadronCleared = false;
					o.update(deltaTime);
				}
			}
			/*float movement = speed * slowerFactor * deltaTime * -1;
			squadronCleared = true;
			createNewPoints = true;
			int whichRow = 0;
			int add = 0;
			float x = -10000;
			boolean isAdd = false;
			for (GameObject o : objects) {
				whichRow ++;
				if (whichRow == 7) add = 100;
				if (o.isAlive()){
					if (x == -10000){
						x = o.getPosition().x + add;
						if (add != 0) isAdd = true;
					}
					squadronCleared = false;
					o.addToCurrentPosition(speed * slowerFactor * deltaTime, 0);
					if (o.getPosition().x >= 100 && o.getPosition().x <= 500 && o.getPosition().y >= y + add && isGoingDownAllowed){
						o.addToCurrentPosition(0, movement);
						createNewPoints = false;
					}
					if (o.getPosition().x >= 1100){
						o.addToCurrentPosition(-1200, 0);
						
					}
					o.update(deltaTime);
				}
			}
			if (createNewPoints){
				if (isAdd){
					if (x <= y + 100) return;
				} else {
					if (x <= y) return;
				}
				y -= 70;
				isGoingDownAllowed = true;
			}*/
		}
	}

}
