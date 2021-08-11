package com.fatraven.proroglili.game.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.fatraven.proroglili.game.Assets;
import com.fatraven.proroglili.game.RandomGen;
import com.fatraven.proroglili.game.dungeon.Room.RoomType;

public class Level {
	public enum Side {LEFT, RIGHT, UP, DOWN};
	private Tile[][] tiles;
	private Array<Room> rooms;
	private int state;
	private boolean doo;
	
	public Level(){
		init();
	}
	
	public void init(){
		tiles = new Tile[100][100]; //[y][x]
		state = 0;
		doo = true;
		/*createRooms();
		createCorridors();
		addIntersectionTiles();
		addDoors();
		createObjects();*/
		/*
		 for (int y = 0; y < tiles.length; y++){
			for (int x = 0; x < tiles[0].length; x++){
				
			}
		}
		 */
	}
/*
	private void createRooms() {
		tiles[0][0] = new Tile(new Vector2(0, 0));
		tiles[0][0].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][1] = new Tile(new Vector2(8, 0));
		tiles[0][1].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][2] = new Tile(new Vector2(16, 0));
		tiles[0][2].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][3] = new Tile(new Vector2(24, 0));
		tiles[0][3].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][4] = new Tile(new Vector2(32, 0));
		tiles[0][4].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][5] = new Tile(new Vector2(40, 0));
		tiles[0][5].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][6] = new Tile(new Vector2(48, 0));
		tiles[0][6].addBackground(Assets.instance.graphics.wallStone);
		tiles[0][7] = new Tile(new Vector2(56, 0));
		tiles[0][7].addBackground(Assets.instance.graphics.wallStone);
		tiles[1][0] = new Tile(new Vector2(0, -8));
		tiles[1][0].addBackground(Assets.instance.graphics.wallStone);
		tiles[2][0] = new Tile(new Vector2(0, -16));
		tiles[2][0].addBackground(Assets.instance.graphics.wallStone);
	}*/

	private void addDoors() {
		Rectangle tempRectangle;
		for (Room room : rooms){
			tempRectangle = new Rectangle();
			tempRectangle.x = room.getRectangle().x - 1;
			tempRectangle.y = room.getRectangle().y - 1;
			tempRectangle.width = room.getRectangle().width + 1;
			tempRectangle.height = room.getRectangle().height + 1;
			int doorCount = 0;
			//montako ovea tulee... switch huoneisiin 1-2, muihin 1-4
			if (room.getType().equals(RoomType.SWITCH)){
				doorCount = RandomGen.instance.getInt(1, 2);
			} else {
				doorCount = RandomGen.instance.getInt(1, 4);
			}
			
			//tutkitaan huoneen sivustat mistä löytyy, jokainen sivu omaksi arrayksi ja sitte joka 
			//sivulta randomilla juttuja ulos
			Array<Integer> leftSideY = new Array<Integer>();
			Array<Integer> rightSideY = new Array<Integer>();
			Array<Integer> upSideX = new Array<Integer>();
			Array<Integer> downSideX = new Array<Integer>();
			
			//ongelma: huoneella alle 4 (0-3) sisäänkäyntiä ja doorCountti 4?
			
			//lasketaan ovipaikat, kun yksi otetaan käyttöön, poistetaan viereiset jos on
			
			int ty = (int)tempRectangle.y;
			int tx = (int)tempRectangle.x;
			int widht = (int)tempRectangle.width;
			int height = (int)tempRectangle.height;
			//tarkista miten nää rectangle arvot meni jos ei toimi
			//eli onko ty + height sama kuin 50 + 5 vai onko 50 + 55
			
			//NOPEA SILMÄILY: TOIMII!!!!
			for (int y = ty; y <= ty + height; y++){
				if (tiles[y][tx] != null){
					if (tiles[y][tx].isDoor()){
						leftSideY.add(y);
						//System.out.println("left y | y:" + y + " x:" + tx);
					}
				}
				if (tiles[y][tx + widht] != null){
					if (tiles[y][tx + widht].isDoor()){
						rightSideY.add(y);
						//System.out.println("right y | y:" + y + " x:" + tx);
					}
				}
			}
			
			for (int x = tx; x <= tx + height; x++){
				if (tiles[ty][x] != null){
					if (tiles[ty][x].isDoor()){
						downSideX.add(x);
						//System.out.println("down x | y:" + ty + " x:" + x);
					}
				}
				if (tiles[ty + height][x] != null){
					if (tiles[ty + height][x].isDoor()){						
						upSideX.add(x);
						//System.out.println("up x | y:" + ty + " x:" + x);
					}
				}
			}
			
			//katsotaan, montako ovea tehdään huoneeseen ja montako on mahdollista
			
			while (doorCount > 0){
				if (leftSideY.size == 0 && rightSideY.size == 0 && downSideX.size == 0 && upSideX.size == 0){
					doorCount = 0;
				}
				switch (RandomGen.instance.getInt(1, 4)) {
				case 1:
					if (leftSideY.size > 0){
						int value;
						if (leftSideY.size == 1){
							createDoor(Side.LEFT, room, leftSideY.get(0));
							leftSideY.clear();
						}else {
							int index = RandomGen.instance.getInt(0, leftSideY.size - 1);
							value = leftSideY.get(index); //arvo 
							createDoor(Side.LEFT, room, value); //tarkista, onko value indeksi vai arvo! arvo?
							leftSideY.removeValue(value - 1, true);
							leftSideY.removeValue(value + 1, true);
							leftSideY.removeValue(value, true);
						}
						doorCount--;
					}
					break;
				case 2:
					if (rightSideY.size > 0){
						int value;
						if (rightSideY.size == 1){
							createDoor(Side.RIGHT, room, rightSideY.get(0));
							rightSideY.clear();
						}else {
							int index = RandomGen.instance.getInt(0, rightSideY.size - 1);
							value = rightSideY.get(index); //arvo 
							createDoor(Side.RIGHT, room, value); //tarkista, onko value indeksi vai arvo! arvo?
							rightSideY.removeValue(value - 1, true);
							rightSideY.removeValue(value + 1, true);
							rightSideY.removeValue(value, true);
						}
						doorCount--;
					}
					break;
				case 3:
					if (upSideX.size > 0){
						int value;
						if (upSideX.size == 1){
							createDoor(Side.UP, room, upSideX.get(0));
						}else {
							int index = RandomGen.instance.getInt(0, upSideX.size - 1);
							value = upSideX.get(index);
							createDoor(Side.UP, room, value);
							upSideX.removeValue(value, true);
							upSideX.removeValue(value + 1, true);
							upSideX.removeValue(value - 1, true);
						}
						doorCount--;
					}
					break;
				case 4:
					if (downSideX.size > 0){
						int value;
						if (downSideX.size == 1){
							createDoor(Side.DOWN, room, downSideX.get(0));
						}else {
							int index = RandomGen.instance.getInt(0, downSideX.size - 1);
							value = downSideX.get(index);
							createDoor(Side.DOWN, room, value);
							downSideX.removeValue(value, true);
							downSideX.removeValue(value + 1, true);
							downSideX.removeValue(value - 1, true);
						}
						doorCount--;
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private void createDoor(Side side, Room room, int place) {
		int y = 0;
		int x = 0;
		//place = arvo, johon tulee se ovi, siitä sitten yksi suunta kohti käytävää johon lattia
		switch (side) {
		case LEFT:
			x = (int)room.getRectangle().x;
			y = place;
			break;
		case RIGHT:
			x = (int)room.getRectangle().x + (int)room.getRectangle().width;
			y = place;
			break;
		case UP:
			x = place;
			y = (int)room.getRectangle().y + (int)room.getRectangle().height;
			break;
		case DOWN:
			x = place;
			y = (int)room.getRectangle().y;
			break;

		default:
			break;
		}
		
		
		//luodaan myös liekit
		switch (side) {
		case LEFT:
			//Tile newTile;
			if (tiles[y][x-2] != null){
				if (tiles[y][x-2].isWall()){
					tiles[y][x-2].addBackground(Assets.instance.graphics.door);
					/*newTile = new Tile(new Vector2(x * 8 - 16, y * 8));
					newTile.addBackground(Assets.instance.graphics.door);
					room.modifyTile((int)y - (int)room.getRectangle().y, (int)x - (int)room.getRectangle().x +2, newTile);*/
				}
			}
			tiles[y][x].addBackground(Assets.instance.graphics.door);
			tiles[y][x-1].addBackground(Assets.instance.graphics.floorWoodLight);
			tiles[y-1][x-1] = null;
			tiles[y+1][x-1] = null;
			/*newTile = new Tile(new Vector2(x * 8, y * 8));
			newTile.addBackground(Assets.instance.graphics.door);
			room.modifyTile((int)y - (int)room.getRectangle().y, (int)x - (int)room.getRectangle().x, newTile);*/
			/*System.out.println("ovi tuli x:y " + x + ":" + y + " ja muutos " + 
					((int)x - (int)room.getRectangle().x) + ":" + ((int)y - (int)room.getRectangle().y));*/
			break;
		case RIGHT:
			if (tiles[y][x+1] != null){
				if (tiles[y][x+1].isWall()){
					tiles[y][x+1].addBackground(Assets.instance.graphics.door);
				}
			}
			tiles[y][x-1].addBackground(Assets.instance.graphics.door);
			tiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
			tiles[y+1][x] = null;
			tiles[y-1][x] = null;
			//Tile newTile2 = new Tile(new Vector2(x *8 - 8, y *8));
			//newTile2.addBackground(Assets.instance.graphics.door);
			/*room.modifyTile((int)y - (int)room.getRectangle().y, 
					(int)x - (int)room.getRectangle().x - 1, newTile2);*/
			break;
		case UP:
			if (tiles[y+1][x] != null){
				if (tiles[y+1][x].isWall()){
					tiles[y+1][x].addBackground(Assets.instance.graphics.door);
				}
			}
			tiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
			tiles[y-1][x].addBackground(Assets.instance.graphics.door);
			tiles[y][x+1] = null;
			tiles[y][x-1] = null;
			break;
		case DOWN:
			if (tiles[y-2][x] != null){
				if (y-2 >= 0){
					if (tiles[y-2][x].isWall()){
						tiles[y-2][x].addBackground(Assets.instance.graphics.door);
					}
				}
				
			}
			tiles[y][x].addBackground(Assets.instance.graphics.door);
			tiles[y-1][x].addBackground(Assets.instance.graphics.floorWoodLight);
			tiles[y-1][x-1] = null;
			tiles[y-1][x+1] = null;
			break;
		default:
			break;
		}
		
	}

	private void addIntersectionTiles() {
		for (int y = 1; y < tiles.length - 1; y++){
			for (int x = 1; x < tiles[0].length - 1; x++){
				if (tiles[y][x] == null) 
					if (isConnectable(y, x)){
						tiles[y][x] = new Tile(new Vector2(x*8, y*8));
						tiles[y][x].addBackground(Assets.instance.graphics.door);
					}
			}
		}
	}
	
	private boolean isConnectable(int y, int x) {
		boolean door = false;
		boolean room = false;
		boolean secondRoom = false;
		if (tiles[y-1][x] != null){
			if (tiles[y-1][x].isFloor()) door = true;
			else if (tiles[y-1][x].isWall()){
				if (tiles[y-2][x].isFloor() && tiles[y-1][x].isDoorable()){
					if (room) secondRoom = true;
					else room = true;
				}
			}
		}
		if (tiles[y+1][x] != null){
			if (tiles[y+1][x].isFloor()) door = true;
			else if (tiles[y+1][x].isWall()){
				if (tiles[y+2][x].isFloor() && tiles[y+1][x].isDoorable()){
					if (room) secondRoom = true;
					else room = true;
				}
			}
		}
		if (tiles[y][x+1] != null){
			if (tiles[y][x+1].isFloor()) door = true;
			else if (tiles[y][x+1].isWall()){
				if (tiles[y][x+2].isFloor() && tiles[y][x+1].isDoorable()){
					if (room) secondRoom = true;
					else room = true;
				}
			}
		}
		if (tiles[y][x-1] != null){
			if (tiles[y][x-1].isFloor()) door = true;
			else if (tiles[y][x-1].isWall()){
				if (tiles[y][x-2].isFloor() && tiles[y][x-1].isDoorable()){
					if (room) secondRoom = true;
					else room = true;
				}
			}
		}
		if (room && (door || secondRoom)) return true;
		return false;
	}

	private void checkTile(int y, int x){
		if (tiles[y][x] == null){
			if (areSurroundingTilesAreFree(y,x)){
				tiles[y][x] = new Tile(new Vector2(x * 8, y * 8));
				tiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
				if (x < tiles[0].length - 2){
					checkTile(y, x + 1);
				}
				if (y < tiles.length - 2){
					checkTile(y + 1, x);
				}
				if (x > 3){
					checkTile(y, x - 1);
				}
				if (y > 3){
					checkTile(y - 1, x);
				}
			}
		}
	}

	private boolean areSurroundingTilesAreFree(int y, int x) {
		int floors = 0;
		if (tiles[y + 1][x] != null){
			if (tiles[y + 1][x].isFloor()) floors++;
			else return false;
		}
		if (tiles[y - 1][x] != null){
			if (tiles[y - 1][x].isFloor()) floors++;
			else return false;
		}
		if (tiles[y][x + 1] != null){
			if (tiles[y][x + 1].isFloor()) floors++;
			else return false;
		}
		if (tiles[y][x - 1] != null){
			if (tiles[y][x - 1].isFloor()) floors++;
			else return false;
		}
		
		if (floors > 1) return false;
		return true;
	}

	private void createCorridors() {
		for (int y = 1; y < tiles.length - 1; y++){
			for (int x = 1; x < tiles[0].length - 1; x++){
				checkTile(y, x);
			}
		}
		for (int y = 1; y < tiles.length - 1; y++){
			for (int x = 1; x < tiles[0].length - 1; x++){
				if (tiles[y][x] == null){
					if (canBeBiggerCorridorThing(y,x)){
						tiles[y][x] = new Tile(new Vector2(x*8, y*8));
						tiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
					}
				}
			}
		}
	}
	
	private boolean canBeBiggerCorridorThing(int y, int x) {
		boolean tuitui = true;
		if (tiles[y+1][x] != null){
			if (!tiles[y+1][x].isFloor()) tuitui = false; 
		} else tuitui = false;
		if (tiles[y-1][x] != null){
			if (!tiles[y-1][x].isFloor()) tuitui = false; 
		} else tuitui = false;
		if (tiles[y][x-1] != null){
			if (!tiles[y][x-1].isFloor()) tuitui = false; 
		} else tuitui = false;
		if (tiles[y][x+1] != null){
			if (!tiles[y][x+1].isFloor()) tuitui = false; 
		} else tuitui = false;
		return tuitui;
	}

	private Room createRoom(Rectangle area, RoomType type){
		Room tempRoom;
		Tile[][] tempTiles;
		tempRoom = new Room((int)area.x,(int) area.y, (int)area.width, (int)area.height, type);
		tempTiles = new Tile[(int)area.height][(int)area.width];
		for (int y = 0; y < tempTiles.length; y++){
			for (int x = 0; x < tempTiles[0].length; x++){
				if (y == 0 || x == 0 || y == area.height - 1 || x == area.width - 1){
					tempTiles[y][x] = new Tile(new Vector2(area.x * 8 +  x * 8, area.y * 8 +  y * 8));
					if (RandomGen.instance.getIntOdd(1, 2) == 1){
						tempTiles[y][x].addBackground(Assets.instance.graphics.wallStoneGrass);
					} else {
						tempTiles[y][x].addBackground(Assets.instance.graphics.wallStone);
					}
				} else {
					if (tempRoom.getType().equals(RoomType.SWITCH) && (x == 2 || x == 6)){
						tempTiles[y][x] = new Tile(new Vector2(area.x * 8 +  x * 8, area.y * 8 +  y * 8));
						tempTiles[y][x].addBackground(Assets.instance.graphics.wallStone);
					}else {
						tempTiles[y][x] = new Tile(new Vector2(area.x * 8 +  x * 8, area.y * 8 +  y * 8));
						tempTiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
					}
				}
				if (tempRoom.getType().equals(RoomType.SWITCH)){
					if (x < 3 || x > 5){
						tempTiles[y][x].setDoorCanBePlaced(false);
					}
				}
			}
		}
		tempRoom.addTiles(tempTiles);
		return tempRoom;
	}

	private void createRooms() {
		rooms = new Array<Room>();
		//Room tempRoom;
		Rectangle tempRectangle;
		int count = 0;
		//Tile[][] tempTiles;
		boolean overlapped;
		while (true){
			overlapped = false;
			//0-20
			switch (RandomGen.instance.getIntOdd(1,20)) {
			case 1:
				//System.out.println("aarre huone");
				tempRectangle = new Rectangle(
						RandomGen.instance.getIntOdd(1, 90) - 1, 
						RandomGen.instance.getIntOdd(1, 90) - 1, 
						5 + 2, 5 + 2); //+1 on varmistin, ettei ole toisessa huoneessa kiinni
				//törmääkö
				for (Room room : rooms){
					if (room.getRectangle().overlaps(tempRectangle)) overlapped = true;
				}
				tempRectangle.height -= 2;
				tempRectangle.width -= 2;
				tempRectangle.x += 1;
				tempRectangle.y += 1;
				//jos ei törmännyt
				if (!overlapped) {
					rooms.add(createRoom(tempRectangle, RoomType.TREASURE));
					/*TOIMIIIIIIIIiiIIIiIiIIiiiII
					tempRoom = new Room(tempRectangle, RoomType.TREASURE);
					tempTiles = new Tile[5][5];//huoneen koko
					for (int y = 0; y < tempTiles.length; y++){
						for (int x = 0; x < tempTiles[0].length; x++){
							if (y == 0 || x == 0 || y == 4 || x == 4){
								int juttux = (int)tempRectangle.x * 8 +  x * 8;
								int juttuy = (int)tempRectangle.y * 8 +  y * -8;
								if (y == 0 && x == 0) System.out.println("x: " + juttux + " , y: " + juttuy);
								tempTiles[y][x] = new Tile(new Vector2(tempRectangle.x * 8 +  x * 8, tempRectangle.y * 8 +  y * -8));
								//reunatilet
								if (RandomGen.instance.getInt(1, 2) == 1){
									tempTiles[y][x].addBackground(Assets.instance.graphics.wallStoneGrass);
								} else {
									tempTiles[y][x].addBackground(Assets.instance.graphics.wallStone);
								}
							} else {
								//muut tilet
								//aloituspiste + monesko tile eli 0 + 0 * 8 = 0, 0 + 1 * 8 = 8
								tempTiles[y][x] = new Tile(new Vector2(tempRectangle.x * 8 +  x * 8, tempRectangle.y * 8 +  y * -8));
								tempTiles[y][x].addBackground(Assets.instance.graphics.floorWoodLight);
							}
						}
					}
					tempRoom.addTiles(tempTiles);
					rooms.add(tempRoom);*/
				}
				break;
			case 2:
			case 3:
				//System.out.println("witchi huone");
				tempRectangle = new Rectangle(
						RandomGen.instance.getIntOdd(1, 87) - 1, 
						RandomGen.instance.getIntOdd(1, 87) - 1, 
						9 + 2, 9 + 2); //+1 on varmistin, ettei ole toisessa huoneessa kiinni
				//törmääkö
				for (Room room : rooms){
					if (room.getRectangle().overlaps(tempRectangle)) overlapped = true;
				}
				tempRectangle.height -= 2;
				tempRectangle.width -= 2;
				tempRectangle.x += 1;
				tempRectangle.y += 1;
				//jos ei törmännyt
				if (!overlapped) {
					rooms.add(createRoom(tempRectangle, RoomType.SWITCH));
				}
				break;
			default:
				//System.out.println("normaali huone");
				tempRectangle = new Rectangle(
						RandomGen.instance.getIntOdd(1, 65) - 1, 
						RandomGen.instance.getIntOdd(1, 65) - 1, 
						RandomGen.instance.getIntOdd(5, 30) + 2, 
						RandomGen.instance.getIntOdd(5, 30) + 2); //+1 on varmistin, ettei ole toisessa huoneessa kiinni
				//törmääkö
				for (Room room : rooms){
					if (room.getRectangle().overlaps(tempRectangle)) overlapped = true;
				}
				tempRectangle.height -= 2;
				tempRectangle.width -= 2;
				tempRectangle.x += 1;
				tempRectangle.y += 1;
				//jos ei törmännyt
				if (!overlapped) {
					rooms.add(createRoom(tempRectangle, RoomType.NORMAL));
				}
				break;
			}
			if (overlapped){
				count++;
				overlapped = false;
			}
			if (count == 1000) break;
		}
		int y;//alkava piste huoneen
		int x;//alkava piste
		int ySize;//koko
		int xSize;//koko
		int roomX = 0;
		int roomY = 0;
		for (Room room : rooms){
			y = (int)room.getRectangle().y;
			x = (int)room.getRectangle().x;
			ySize = y + (int)room.getRectangle().height;
			xSize = x + (int)room.getRectangle().width;
			roomY = 0;
			while (y < ySize){
				roomX = 0;
				while (x < xSize){
					
					tiles[y][x] = room.getTiles()[roomY][roomX];
					roomX++;
					x++;
				}				
				roomY++;
				y++;
				x = (int)room.getRectangle().x;
			}
		}
	}

	public void draw(SpriteBatch batch){
		for (int y = tiles.length -1; y >= 0; y--){
			for (int x = tiles[0].length -1; x >= 0; x--){
				if (tiles[y][x] != null) tiles[y][x].draw(batch);
			}
		}
		
		/*
		for (int y = 0; y < tiles.length; y++){
			for (int x = 0; x < tiles[0].length; x++){
				if (tiles[y][x] != null) tiles[y][x].draw(batch);
			}
		}*/
	}
	
	public Vector2 getPositionFromTile(int x, int y){
		return new Vector2(x * 8, y *8);
	}
	
	public Vector2 getTileFromPosition(Vector2 pos){
		return new Vector2(pos.x / 8, pos.y / 8);
	}

	public void update(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			doo = true;
			state++;
		}
		switch (state) {
		case 1:
			if (doo) createRooms();
			doo = false;
			break;
		case 2:
			if (doo) createCorridors();
			doo = false;
			break;
		case 3:
			if (doo) addIntersectionTiles();
			doo = false;
			break;
		case 4:
			if (doo) addDoors();
			doo = false;
			break;
		case 5:
			if (doo) clearDoors();
			doo = false;
			break;
		case 6:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 7:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 8:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 9:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 10:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 11:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 12:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 13:
			if (doo) clearCorridors();
			doo = false;
			break;
		case 14:
			init();
			break;
		default:
			break;
		}
	}

	private void clearCorridors() {
		for (int y = 1; y < tiles.length - 1; y++){
			for (int x = 1; x < tiles[0].length - 1; x++){
				if (tiles[y][x] != null){
					if (tiles[y][x].isFloor()){
						int emptySpaces = 0;
						if (tiles[y+1][x] == null) emptySpaces++;
						if (tiles[y-1][x] == null) emptySpaces++;
						if (tiles[y][x+1] == null) emptySpaces++;
						if (tiles[y][x-1] == null) emptySpaces++;
						if (emptySpaces >= 3) tiles[y][x] = null;
					}
				}
			}
		}
		for (int y = tiles.length - 2; y >= 1; y--){
			for (int x = tiles[0].length - 2; x >= 1; x--){
				if (tiles[y][x] != null){
					if (tiles[y][x].isFloor()){
						int emptySpaces = 0;
						if (tiles[y+1][x] == null) emptySpaces++;
						if (tiles[y-1][x] == null) emptySpaces++;
						if (tiles[y][x+1] == null) emptySpaces++;
						if (tiles[y][x-1] == null) emptySpaces++;
						if (emptySpaces >= 3) tiles[y][x] = null;
					}
				}
			}
		}
	}

	private void clearDoors() {
		for (int y = 1; y < tiles.length - 1; y++){
			for (int x = 1; x < tiles[0].length - 1; x++){
				if (tiles[y][x] != null){
					if (tiles[y][x].isDoor()){
						if (
								tiles[y-1][x] == null ||
								tiles[y+1][x] == null ||
								tiles[y][x-1] == null ||
								tiles[y][x+1] == null){
							tiles[y][x] = null;
						}
					}
				}
			}
		}
	}
}
