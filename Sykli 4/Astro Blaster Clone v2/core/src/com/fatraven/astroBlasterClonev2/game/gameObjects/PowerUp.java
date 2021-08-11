package com.fatraven.astroBlasterClonev2.game.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.fatraven.astroBlasterClonev2.game.levels.Level;

public class PowerUp extends GameObject implements Poolable{
	public int powerUpStyle;
	private Polygon polHitbox;
	private float stateTime;
	
	public PowerUp(Animation animation, Level level) {
		super(animation, level);
		polHitbox = new Polygon();
		stateTime  = 0;
		frame = animation.getKeyFrame(stateTime);
	}

	public void update(float deltaTime) {
		super.update(deltaTime);
		if (alive){
			stateTime += deltaTime;
			frame = animation.getKeyFrame(stateTime);
			position.y -= 80 * deltaTime * slowerFactor;
		}
		
	};
	@Override
	public Polygon getHitboxPol() {
		getHitbox();
		polHitbox.setVertices(new float[]{
				position.x + hitbox.width * 0.5f,position.y,
				hitbox.width + position.x, position.y + hitbox.height * 0.75f,
				hitbox.width * 0.5f + position.x, hitbox.height + position.y,
				position.x,hitbox.height * 0.75f + position.y});
		return polHitbox;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPoints(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill() {
		alive = false;
	}
	
	public void shapeRender() {
		ShapeRenderer r = new ShapeRenderer();
		r.setColor(1,0,0,1);
		r.begin(ShapeType.Line);
		r.polygon(getHitboxPol().getVertices());
		r.end();
	}
	
	public void powerUpPlayer(Player p){
		//1-7: pelaajan nopeus kasvaa 50%
		//8-10: shieldi resetti ja shieldin tehon nosto
		//11: pelaajan ase viilentyy, viilennysnopeus kaksinkertaistuu ja nopeus 1.5 kertaistuu
		//12-13: bensat täyttyy 
		//14-15: tupla aseet yhden hinnalla
		//16-20: pisteitä! 100 kpl!
		powerUpStyle = level.ra.nextInt(20) + 1;
		switch (powerUpStyle) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			p.setSlowerFactor(p.getSlowerFactor() * 1.5f);
			level.upText = "More speed!";
			level.upTextTimer = 5;
			break;
		case 8:
		case 9:
		case 10:
			p.shield.maxEndurance++;
			p.shield.endurance = p.shield.maxEndurance;
			level.upText = "Better shield!";
			level.upTextTimer = 5;
			break;
		case 11:
			p.temperature = 0;
			p.tempCooler *= 2;
			p.shootingTimer *= 0.5f;
			level.upText = "Better cooling and faster weapon!";
			level.upTextTimer = 5;
			break;
		case 12:
		case 13:
			p.gas = p.gasMax;
			level.upText = "Gas!";
			level.upTextTimer = 5;
			break;
		case 14:
		case 15:
			p.doubleGunsEnabled = true;
			level.upText = "More guns!";
			level.upTextTimer = 5;
			break;
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
			p.addPoints(100);
			level.upText = "Extra points!";
			level.upTextTimer = 5;
			break;
		default:
			break;
		}
	}

	@Override
	public void reset() {
		alive = false;
		position.x = 0;
		position.y = 0;
		stateTime = 0;
	}

}
