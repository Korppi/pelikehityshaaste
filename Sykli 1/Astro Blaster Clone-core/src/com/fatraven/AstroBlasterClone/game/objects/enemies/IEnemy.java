package com.fatraven.AstroBlasterClone.game.objects.enemies;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface IEnemy {
	public Vector2 getPosition();
	public void update(float deltaTime);
	public boolean isAlive();
	public void render(SpriteBatch batch);
	public void addPoint(Vector2 point);
	public void calculateMovement();
	public void shapeRender(Camera cam);
	public void kill();
	public int getPoints();
	public void setSlowTimer(float f);
	public float getSlowerTimer();
	public void setVelocity(int i);
	public void setPoints(int i);
	public void useAlternativeGraph(boolean b);
	public void addAnimation(Animation a);
}
