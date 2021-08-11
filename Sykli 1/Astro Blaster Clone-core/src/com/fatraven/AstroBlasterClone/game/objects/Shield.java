package com.fatraven.AstroBlasterClone.game.objects;

public class Shield{
	private int endurance;
	private int maxEndurance;
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
		if (currentTime >= 0) return true;
		return false;
	}
	
	public void hit(){
		endurance--;
		if (endurance < 0){
			System.out.println("shieldit hajos");
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
