package com.fatraven.vaalipeli.game.objects;

public class Player {
	private int rightAnswers;
	
	public Player(){
		rightAnswers = 0;
	}
	
	public void addPoint(){
		rightAnswers++;
	}
	
	public int getPoints(){
		return rightAnswers;
	}
}
