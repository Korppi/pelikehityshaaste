package com.fatraven.astroBlasterClonev2.game.levels;

import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Player;

public class SquadronEmptyTank extends Squadron{
	Player player;
	float timer;
	
	public SquadronEmptyTank(Level level, Player player) {
		super(level);
		this.player = player;
		timer = 0.05f;
	}

	@Override
	public void update(float deltaTime) {
		squadronCleared = true;
		if (player.gas > 0){
			timer -= deltaTime;
			if (timer <= 0){
				player.gas--;
				player.addPoints(2);
				Assets.instance.sounds.select.play();
				timer = 0.05f;
			}
			squadronCleared = false;
			
		}
	}

}
