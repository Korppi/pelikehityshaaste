package com.fatraven.astroBlasterClonev2.game.gameObjects;

public class Shield{
	public int endurance;
	public int maxEndurance;
	private float drawCooldown;
	private float currentTime;
	
	public Shield(){
		init();
	}
	
	private void init(){
		maxEndurance = 1;
		endurance = maxEndurance;
		drawCooldown = 1.5f;
		currentTime = 0f;
	}
	
	public boolean isShown(){
		if (currentTime > 0) return true;
		return false;
	}
	
	public void hit(){
		endurance--;
		if (endurance < 0){
			currentTime = 0;
		}else {
			currentTime = drawCooldown;
		}
		
	}
	
	public boolean isDown(){
		if (endurance <= 0) return true;
		return false;
	}
	
	public void update(float deltaTime){
		currentTime -= deltaTime;
	}

	public void restore() {
		endurance = maxEndurance;
	}
}
