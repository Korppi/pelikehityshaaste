package com.fatraven.proroglili.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.proroglili.game.Assets;

public class Tile {
	private TextureRegion background;
	private final Vector2 position;
	private boolean doorCanBePlaced;
	
	public Tile(Vector2 pos){
		position = pos;
		doorCanBePlaced = true;
	}
	
	public boolean isFloor(){
		if (background.equals(Assets.instance.graphics.floorWoodLight)) return true;
		return false;
	}
	
	public void setDoorCanBePlaced(boolean value){
		doorCanBePlaced = value;
	}
	
	public boolean isDoorable(){
		return doorCanBePlaced;
	}
	
	public void addBackground(TextureRegion background){
		this.background = background;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(background, position.x, position.y, 4, 4, 8, 8, 1, 1, 0);
	}
	
	public void execute(){
		//TODO: objektien suoritukset tänne
	}

	public Vector2 TestiGetPosition() {
		return position;
	}

	public boolean isWall() {
		return (background.equals(Assets.instance.graphics.wallStone) || background.equals(Assets.instance.graphics.wallStoneGrass));
	}

	public boolean isDoor() {
		return (background.equals(Assets.instance.graphics.door));
	}
}
