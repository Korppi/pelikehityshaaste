package com.fatraven.AstroBlasterClone.game.objects.enemies;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.game.Level;
import com.fatraven.AstroBlasterClone.game.objects.GameObject;

public class Asteroid extends GameObject implements IEnemy{
	private Level level;
	private boolean alive;
	private int velocity;
	private float slowerFactor;
	private Animation animation;
	private int pointsForShooting;
	private float delta;
	public boolean isRed;
	
	public Asteroid(Level level, Vector2 pos){
		super();
		this.isRed = false;
		this.level = level;
		position.x = pos.x;
		position.y = pos.y;
		scale.set(0.1f, 0.1f);
		init();
	}
	
	private void init(){
		velocity = 30;
		slowerFactor = 1;
		pointsForShooting = 20;
		alive = true;
		//alternative = false;
		hitbox = new Rectangle(position.x, position.y, 
				Assets.instance.asteroids.brownAsteroids.get(0).getRegionWidth() * scale.x,
				Assets.instance.asteroids.brownAsteroids.get(0).getRegionHeight() * scale.y);
	}
	
	public void addAnimation(Animation a){
		animation = a;
		animation.setPlayMode(PlayMode.LOOP);
	}
	
	@Override
	public boolean isAlive() {
		return alive;
	}

	@Override
	public void addPoint(Vector2 point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void calculateMovement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shapeRender(Camera cam) {
		ShapeRenderer r = new ShapeRenderer();
		r.setProjectionMatrix(cam.combined);
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitbox().getVertices());
		r.end();
	}

	@Override
	public void kill() {
		alive = false;
		if (isRed){
			level.giveFuel();
		}
		Assets.instance.sounds.hurt.play();
		Assets.instance.sounds.explosion.play();
	}

	@Override
	public int getPoints() {
		return pointsForShooting;
	}

	@Override
	public void setSlowTimer(float f) {
		slowerFactor = f;
	}

	@Override
	public float getSlowerTimer() {
		return slowerFactor;
	}

	@Override
	public void setVelocity(int i) {
		velocity = i;
	}

	@Override
	public void setPoints(int i) {
		pointsForShooting = i;
	}

	@Override
	public void render(SpriteBatch batch) {
		if (alive){
			batch.draw(animation.getKeyFrame(delta), 
					position.x, position.y, origin.x, origin.y, 
					Assets.instance.shipsAndShit.enemyOne.getRegionWidth(), 
					Assets.instance.shipsAndShit.enemyOne.getRegionHeight(), scale.x, scale.y, rotation);
			hitbox.x = position.x;
			hitbox.y = position.y;
		}
	}
	
	@Override
	public void update(float deltaTime){
		if (alive){
			delta += deltaTime;
			position.y -= deltaTime * velocity * slowerFactor;
			if (isOutOfLowerScreen()){
				alive = false;
			}
		}		
	}

	private boolean isOutOfLowerScreen() {
		if (position.y < -10) return true;
		return false;
	}

	@Override
	public void useAlternativeGraph(boolean b) {
		 //todo
	}

	@Override
	public Polygon getHitbox(){
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y + hitbox.height * 0.25f,
				hitbox.width * 0.75f + position.x, position.y + hitbox.height * 0.75f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x + hitbox.width * 0.25f,hitbox.height * 0.75f + position.y});
		return polHitbox;
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	
	
}
