package com.fatraven.astroBlasterClonev2.screens;

public enum ScreenType {
	MENUSCREEN(0),CREDITSCREEN(1), GAMESCREEN(2);
	private int value;
	private ScreenType(int value){
		this.value = value;
	}
	public int getValue() {
		return value;
	}
}
