package com.fatraven.proroglili.game.dungeon;

import com.badlogic.gdx.math.Rectangle;

public class Room {
	public enum RoomType {
		NORMAL, TREASURE, SWITCH, CORRIDOR
	};
	private Rectangle space;
	private RoomType type;
	private Tile[][] tiles;
	
	public Room(int x, int y, int width, int height, RoomType type){
		space = new Rectangle(x, y, width, height);
		this.type = type;
	}
	
	public void addTiles(Tile[][] tiles){
		this.tiles = tiles;
	}
	
	public Tile[][] getTiles(){
		return tiles;
	}
	
	public Rectangle getRectangle(){
		return space;
	}
	
	public RoomType getType(){
		return type;
	}

	public void modifyTile(int y, int x, Tile newTile) {
		tiles[y][x] = newTile;
	}
}
