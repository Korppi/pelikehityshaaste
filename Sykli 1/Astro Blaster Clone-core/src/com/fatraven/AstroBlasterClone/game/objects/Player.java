package com.fatraven.AstroBlasterClone.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.Level;
import com.fatraven.AstroBlasterClone.util.Constants;

public class Player extends GameObject{
	private static final String TAG = Player.class.getName();
	private int points;
	private int lives = Constants.STARTING_LIVES;
	private float velocity;
	private float laserCooldown;
	private float currentCooldown;
	private Level level;
	private Shield shield;
	private boolean alive;
	private int gunPower;
	private boolean stunned;
	private float fuelMax;
	private float currentFuel;
	private float laserOverheatLevel;
	private float laserOverheat;
	private boolean overheateded;
	
	public Player(Level level){
		super();
		this.level = level;
		init();
	}
	
	public boolean isAlive(){
		return alive;
	}
	
	public float getOverheatMax(){
		return laserOverheatLevel;
	}
	
	public float getOverHeat(){
		return laserOverheat;
	}
	
	private void init(){
		//position = new Vector2(Constants.VIEWPORT_WIDTH * 0.5f, Constants.VIEWPORT_HEIGHT * 0.5f);
		position.x = 0;
		position.y = 0;
		velocity = 20f;
		scale.set(0.1f, 0.1f);
		laserCooldown = 0.5f;
		currentCooldown = 0f;
		shield = new Shield();
		alive = true;
		gunPower = 1;
		laserOverheatLevel = 30;
		laserOverheat = 0;
		stunned = false;
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.shipsAndShit.redship.get(4).getRegionWidth() * scale.x, 
				Assets.instance.shipsAndShit.redship.get(4).getRegionHeight() * scale.y);
		overheateded = false;
		fuelMax = 200;
		currentFuel = fuelMax;
	}
	
	
	public void hitByLaser(){
		if (stunned) return;
		Assets.instance.sounds.hurt.play();
		if (!shield.isDown()){
			shield.hit();
			Assets.instance.sounds.shield.play();
		} else {
			Gdx.app.debug(TAG, "die");
			Assets.instance.sounds.explosion.play();
			die();
		}
	}
	
	private void die(){
		lives--;
		Gdx.app.debug(TAG, "lives: " + lives);
		if (lives == 0){
			alive = false;
			Gdx.app.debug(TAG, "alive: " + alive);
		} else {
			stunned = true;
			Gdx.app.debug(TAG, "stunned!");
			//resurrect();
		}
	}
	
	public void resurrect() {
		gunPower = 1;
		shield.restore();
		currentCooldown = 0;
		stunned = false;
		Gdx.app.debug(TAG, "resurrected!");
	}

	public void update(float deltaTime){
		currentFuel -= deltaTime * 2.2f;
		if (currentFuel <= 0){
			alive = false;
			lives = 0;
		}
		if (laserOverheat > 0) {
			laserOverheat -= deltaTime;
			if (laserOverheat <= laserOverheatLevel * 0.8f && overheateded){
				overheateded = false;
				Gdx.app.debug(TAG, "overheated no more!");
			}
		}
		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			position.x += -velocity * deltaTime;
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			position.x += velocity * deltaTime;
		} /*else {
			if (position.x > 0){
				Gdx.app.debug(TAG, "palautus vasemmalle");
				position.x = MathUtils.clamp(position.x - velocity * deltaTime, 0, position.x);
			}
			else if (position.x < 0){
				Gdx.app.debug(TAG, "palautus oikealle");
				position.x = MathUtils.clamp(position.x + velocity * deltaTime, position.x, 0);
			}			
		}*/
		if (Gdx.input.isKeyPressed(Keys.SPACE)){
			if (currentCooldown <= 0 && !overheateded){
				laserOverheat += 1.3f;
				if (laserOverheat >= laserOverheatLevel){
					overheateded = true;
					Gdx.app.debug(TAG, "overheated!!!!!!!!!!");
				} else {
					Gdx.app.debug(TAG, "nöt överheated");
					currentCooldown = laserCooldown;
					level.fireBullet(true, position.x + Assets.instance.shipsAndShit.redship.get(4).getRegionWidth() * scale.x * 0.5f,
							position.y + Assets.instance.shipsAndShit.redship.get(4).getRegionHeight() * scale.y, gunPower);
				}
				
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.F)) hitByLaser();
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) Gdx.app.debug(TAG, String.valueOf(position.x));
		/*position.x = MathUtils.clamp(position.x, 0,Constants.VIEWPORT_WIDTH - 
				Assets.instance.shipsAndShit.redship.get(4).getRegionWidth() * scale.x);*/
		if (position.x <= 0){
			position.x = 0;
		} else if (position.x >= Constants.VIEWPORT_WIDTH - Assets.instance.shipsAndShit.redship.get(4).getRegionWidth() * scale.x){
			position.x = Constants.VIEWPORT_WIDTH - Assets.instance.shipsAndShit.redship.get(4).getRegionWidth() * scale.x;
		}
		hitbox.x = position.x;
		hitbox.y = position.y;
		currentCooldown -= deltaTime;
		shield.update(deltaTime);
	}

	@Override
	public void render(SpriteBatch batch) {
		if (alive){
			batch.draw(Assets.instance.shipsAndShit.redship.get(4), position.x, position.y, 
					origin.x, origin.y, Assets.instance.shipsAndShit.redship.get(4).getRegionWidth(),
					Assets.instance.shipsAndShit.redship.get(4).getRegionHeight(), scale.x, scale.y, rotation);
			if (shield.isShown()) batch.draw(Assets.instance.shipsAndShit.shield, 
					position.x - 0.5f, position.y - 1, origin.x, origin.y, 
					Assets.instance.shipsAndShit.shield.getRegionWidth(), 
					Assets.instance.shipsAndShit.shield.getRegionHeight(), scale.x, scale.y, rotation);
		}
	}
	
	public int getPoints(){
		return points;
	}

	public boolean isStunned() {
		return stunned;
	}
	
	@Override
	public Polygon getHitbox(){
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y,
				hitbox.width + position.x, position.y + hitbox.height * 0.3f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x,hitbox.height * 0.3f});
		return polHitbox;
	}

	public void shapeRender(Camera cam) {
		ShapeRenderer r = new ShapeRenderer();
		r.setProjectionMatrix(cam.combined);
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitbox().getVertices());
		r.end();
	}

	public void kill() {
		die();
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public float getFuelMax() {
		return fuelMax;
	}

	public float getFuel() {
		return currentFuel;
	}

	public void giveFuel() {
		currentFuel += 10;

	}

}
