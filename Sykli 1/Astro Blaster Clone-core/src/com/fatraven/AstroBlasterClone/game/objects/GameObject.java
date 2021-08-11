package com.fatraven.AstroBlasterClone.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject {
	public Vector2 position;
	protected Vector2 scale;
	protected Vector2 origin;
	protected float rotation;
	protected Rectangle hitbox;
	protected Polygon polHitbox;
	
	public GameObject(){
		position = new Vector2(0,0);
		scale = new Vector2(1,1);
		origin = new Vector2(0,0);
		rotation = 0.0f;
		polHitbox = new Polygon();
	}
	
	public Rectangle getHitboxRectangle(){
		return hitbox;
	}
	
	public boolean testCollision(GameObject object){
		if (Intersector.overlaps(object.getHitboxRectangle(), hitbox)){
			//Gdx.app.debug("gameobject", "hitbox osuma");
			if (Intersector.overlapConvexPolygons(object.getHitbox(), getHitbox())){
				//Gdx.app.debug("gameobject", "polygon osuma");
				return true;
			}
		}
		return false;
	}
	
	public Polygon getHitbox(){
		polHitbox.setVertices(new float[]{
				position.x,position.y,
				hitbox.width + position.x, position.y,
				hitbox.width + position.x, hitbox.height + position.y,
				position.x + hitbox.width,hitbox.height + position.y});
		return polHitbox;
	}
	
	public void update(float deltaTime) {}; //ei oo pakko tehd‰ mit‰‰n jos ei nappaa
	public abstract void render(SpriteBatch batch);
}
