package com.fatraven.astroBlasterClonev2.game.levels;

import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.gameObjects.Player;

public class SquadronFillTank extends Squadron{
	Player player;
	float timer;
	public SquadronFillTank(Level level, Player player) {
		super(level);
		// TODO Auto-generated constructor stub
		this.player = player;
		squadronCleared = false;
		timer = 0.05f;
	}

	@Override
	public void update(float deltaTime) {
		squadronCleared = true;
		if (player.gas < player.gasMax){
			timer -= deltaTime;
			squadronCleared = false;
			if (timer <= 0){
				Assets.instance.sounds.select.play();
				player.gas++;
				timer = 0.05f;
			}
		}
	}

}
