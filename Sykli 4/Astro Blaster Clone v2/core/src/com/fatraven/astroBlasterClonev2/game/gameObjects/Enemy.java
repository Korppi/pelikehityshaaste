package com.fatraven.astroBlasterClonev2.game.gameObjects;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class Enemy extends GameObject {
	private float shootingTime;
	private float shootingTimeElapsed;
	private float stateTime;
	protected Polygon polHitbox;
	protected int selection;
	protected int points;
	
	public Enemy(Animation animation, Level level, int select) {
		super(animation, level);
		init(select);
		spawnPowerup = true;
	}
	
	public void setPoints(int value){
		points = value;
	}
	
	private void init(int select){
		Random random = new Random();
		shootingTime = 1 + random.nextFloat() * ((1 + 10) - 1);
		stateTime = 0;
		alive = true;
		polHitbox = new Polygon();
		selection = select;
	}

	public Enemy(TextureRegion pic, Level level, int select) {
		super(pic, level);
		init(select);
	}

	@Override
	public void update(float deltaTime) {
		if (alive){
			super.update(deltaTime);
			if (animation != null){
				stateTime += deltaTime * slowerFactor;
				frame = animation.getKeyFrame(stateTime);
			}
			shootingTimeElapsed += deltaTime * slowerFactor;
			if (shootingTimeElapsed > shootingTime){
				shootingTimeElapsed = 0;
				level.addBullet(false, position.x, position.y, 1);
			}
		}
	}
	public void draw(SpriteBatch batch){
		batch.draw(frame, position.x, position.y, 0, 0, frame.getRegionWidth(), frame.getRegionHeight(), 1, 1, 0);
		/*batch.end();
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitboxPol().getVertices());
		r.end();
		batch.begin();*/
	}
	
	public void shapeRender() {
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitboxPol().getVertices());
		r.end();
	}

	@Override
	public Polygon getHitboxPol() {
		getHitbox();
		//nojoo, näköjään samat verticet kaikissa mut jätetään jos kerkeän tekee enemmän tai tarkemmiksi nuo
		switch (selection) {
		case 1:
			polHitbox.setVertices(new float[]{
					position.x + hitbox.width * 0.5f,position.y,
					hitbox.width + position.x, position.y + hitbox.height * 0.75f,
					hitbox.width * 0.5f + position.x, hitbox.height + position.y,
					position.x,hitbox.height * 0.75f + position.y});
			break;
		case 2:
			polHitbox.setVertices(new float[]{
					position.x + hitbox.width * 0.5f,position.y,
					hitbox.width + position.x, position.y + hitbox.height * 0.75f,
					hitbox.width * 0.5f + position.x, hitbox.height + position.y,
					position.x,hitbox.height * 0.75f + position.y});
			break;
		case 3:
			polHitbox.setVertices(new float[]{
					position.x + hitbox.width * 0.5f,position.y,
					hitbox.width + position.x, position.y + hitbox.height * 0.75f,
					hitbox.width * 0.5f + position.x, hitbox.height + position.y,
					position.x,hitbox.height * 0.75f + position.y});
			break;
		default:
			break;
		}
		return polHitbox;
	}

	@Override
	public void kill() {
		alive = false;
		
	}

	@Override
	public int getPoints() {
		return points;
	}

}
