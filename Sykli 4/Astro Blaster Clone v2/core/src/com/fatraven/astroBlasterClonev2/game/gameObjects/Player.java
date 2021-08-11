package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.astroBlasterClonev2.game.Assets;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class Player extends GameObject{
	public boolean skipGasChecker;
	private float speed;
	public boolean slowerUnused;
	public float shootingTimer;
	public float shootingTimeElapsed;
	public float gas;
	public float gasMax;
	private int lives;
	public float temperature;
	public float temperatureMax;
	private boolean overheated;
	public Shield shield;
	private float liveTimer;
	private Polygon polHitbox;
	//private boolean stunned;
	private int points;
	public float tempCooler;
	public boolean doubleGunsEnabled;
	
	public Player(TextureRegion pic, Level level) {
		super(pic, level);
		skipGasChecker = false;
		slowerFactor = 1;
		doubleGunsEnabled = false;
		position = new Vector2(600, 10);
		speed = 200;
		tempCooler = 5;
		slowerUnused = true;
		shootingTimeElapsed = 0;
		shootingTimer = 0.5f;
		temperature = 0;
		temperatureMax = 100;
		gasMax = 100;
		gas = gasMax;
		lives = 3;
		alive = true;
		overheated = false;
		shield = new Shield();
		liveTimer = 1;
		polHitbox = new Polygon();
		//stunned = false;
		points = 0;
	}
	
	public void addPoints(int value){
		points += value;
	}
	
	public void hit(int damage){
		if (!alive && lives <=0) return; 
		Assets.instance.sounds.hit.play();
		if (shield.isDown()){
			kill();
			alive = false;
			//stunned = true;
			
		}
		shield.hit();
	}

	@Override
	public void update(float deltaTime) {
		if (!skipGasChecker){
			shield.update(deltaTime);
			gas -= 0.5f * deltaTime;
			if (gas <= 0) {
				lives = 0;
				alive = false;
			}
		}
		if (temperature > 0) {
			temperature -= tempCooler * deltaTime;
			if (temperature <= 80) {
				if (overheated) {
					level.upText = "Weapons ready!";
					level.upTextTimer = 3;
				}
				overheated = false;
			}
			if (temperature < 0) temperature = 0;
		}
		if (shootingTimeElapsed > 0) shootingTimeElapsed -= deltaTime;
		if (alive && lives > 0){
			//System.out.println(speed * deltaTime * slowerFactor);
			//System.out.println("shield: " + shield.endurance + ":" + shield.maxEndurance);
			if (Gdx.input.isKeyPressed(Keys.RIGHT)){
				position.x += speed * deltaTime * slowerFactor;
			}
			if (Gdx.input.isKeyPressed(Keys.LEFT)){
				position.x -= speed * deltaTime * slowerFactor;
			}
			position.x = MathUtils.clamp(position.x, 0, 800 - 110);
			if (Gdx.input.isKeyPressed(Keys.SPACE) && shootingTimeElapsed <= 0 && !overheated){
				if (!doubleGunsEnabled){
					level.addBullet(true, position.x + frame.getRegionWidth() * 0.5f - Assets.instance.shipsAndShit.bullet.getRegionWidth() * 0.5f, position.y + frame.getRegionHeight() - 10, damage);
				} else {
					level.addBullet(true, position.x + 15 - Assets.instance.shipsAndShit.bullet.getRegionWidth() * 0.5f, position.y + frame.getRegionHeight() - 40, damage);
					level.addBullet(true, position.x - 15 + frame.getRegionWidth() - Assets.instance.shipsAndShit.bullet.getRegionWidth() * 0.5f, position.y + frame.getRegionHeight() - 40, damage);
					level.addBullet(true, position.x + frame.getRegionWidth() * 0.5f - Assets.instance.shipsAndShit.bullet.getRegionWidth() * 0.5f, position.y + frame.getRegionHeight() - 10, damage);
				}
				shootingTimeElapsed = shootingTimer;
				temperature += 8;
				if (temperature >= temperatureMax){
					overheated = true;
					level.upText = "Weapons overheated!";
					level.upTextTimer = 3;
				}
			}
			if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && slowerUnused){
				level.activateSlower();
				slowerUnused = false;
			}
		} else if (lives > 0 && !alive){
			liveTimer -= deltaTime;
			if (liveTimer < 0) {
				alive = true;
				shield.restore();
				liveTimer = 1;
				slowerFactor = 1;
				tempCooler = 5;
				gasMax = 100;
				shield.maxEndurance = 1;
				shield.endurance = shield.maxEndurance;
				shootingTimer = 0.5f;
				doubleGunsEnabled = false;
			}
		}
	}
	
	public Polygon getHitboxPol(){
		getHitbox();
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y,
				hitbox.width + position.x, position.y + hitbox.height * 0.3f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x,hitbox.height * 0.3f});
		return polHitbox;
	}
	
	public void draw(SpriteBatch batch){
		if (alive && lives > 0){
			batch.draw(frame, position.x, position.y, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0);
		}
		/*batch.end();
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitboxPol().getVertices());
		r.end();
		batch.begin();*/
		if (shield.isShown()){
			batch.draw(Assets.instance.shipsAndShit.shield, 
					position.x - 5, position.y, 0, 0, 
					Assets.instance.shipsAndShit.shield.getRegionWidth(), 
					Assets.instance.shipsAndShit.shield.getRegionHeight(), 1, 1, 0);
		}
	}

	public boolean isNotCompletelyDead() {
		if (alive || lives > 0) return true;
		return false;
	}

	@Override
	public void kill() {
		lives--;
	}

	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int value) {
		// TODO Auto-generated method stub
		
	}

	public float getHeatPros() {
		return temperature / temperatureMax;
	}

	public float getFuelPros() {
		return gas / gasMax;
	}

	public void killByColl() {
		// TODO Auto-generated method stub
		kill();
		alive = false;
		//stunned = true;
	}
}
