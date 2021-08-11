package com.fatraven.astroBlasterClonev2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.astroBlasterClonev2.AstroBlaster;
import com.fatraven.astroBlasterClonev2.game.Assets;

public class CreditScreen extends AbstractScreen{
	private Stage stage;
	private Table table;
	private Array<Label> labels;
	private SpriteBatch batch;
	private AstroBlaster game;
	
	public CreditScreen(final AstroBlaster game){
		this.game = game;
		batch = new SpriteBatch();
		stage = new Stage(new FitViewport(800, 600));
		table = new Table();
		table.setFillParent(true);
		labels = new Array<Label>();
		labels.add(new Label("Space wallpaper by vladart", Assets.instance.gui.skin));
		labels.add(new Label("Enemy spaceships by Division PLUS", Assets.instance.gui.skin));
		
		labels.add(new Label("Shield by Bonsaiheldin | http://bonsaiheld.org", Assets.instance.gui.skin));
		labels.add(new Label("Player ship by MillionthVector (http://millionthvector.blogspot.de", Assets.instance.gui.skin));
		labels.add(new Label("Asteroids by phaelax", Assets.instance.gui.skin));
		labels.add(new Label("Explosions and powerups by chabull", Assets.instance.gui.skin));
		labels.add(new Label("Music by Matthew Pablo http://www.matthewpablo.com", Assets.instance.gui.skin));
		labels.add(new Label("Abose assets are all found from http://opengameart.org", Assets.instance.gui.skin));
		labels.add(new Label("Sound effects made with bfxr http://www.bfxr.net/", Assets.instance.gui.skin));
		labels.add(new Label("Menu logo made with http://eu2.flamingtext.com/", Assets.instance.gui.skin));
		labels.add(new Label("Everything else (buttons, gui, stars etc) made by Korppi", Assets.instance.gui.skin));
		//labels.add(new Label("", Assets.instance.gui.skin));
		labels.add(new Label("press ENTER to return to menu", Assets.instance.gui.skin));
		for (Label label : labels){
			table.add(label).padBottom(20).row();
		}
		stage.addActor(table);
	}

	@Override
	public void render(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			game.setScreenByType(ScreenType.MENUSCREEN);
		}
		stage.act(deltaTime);
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.instance.misc.background, 0, 0);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
