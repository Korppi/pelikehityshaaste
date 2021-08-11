package com.fatraven.AstroBlasterClone.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.util.Constants;

public class CreditsScreen extends AbstractScreen{
	private Stage stage;
	private Skin skin;
	private Array<Label> labels;
	private Table table;
	private SpriteBatch batch;

	public CreditsScreen(Game game) {
		super(game);
		
	}

	@Override
	public void render(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			game.setScreen(new MenuScreen(game));
		}
		stage.act(deltaTime);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(Assets.instance.decorations.wallpaper, 0, 0);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void show() {
		stage = new Stage(new FitViewport(Constants.VIEWPORT_WIDTH_RES, Constants.VIEWPORT_HEIGHT_RES));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	private void rebuildStage() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal(Constants.GUI_JSON),
				new TextureAtlas(Constants.GUI_NAME));
		labels = new Array<Label>();
		labels.add(new Label("Space wallpaper by vladart", skin));
		labels.add(new Label("Enemy spaceships by Division PLUS", skin));
		labels.add(new Label("Shield by Bonsaiheldin | http://bonsaiheld.org", skin));
		labels.add(new Label("Player ship by MillionthVector (http://millionthvector.blogspot.de)", skin));
		labels.add(new Label("Asteroids by phaelax", skin));
		labels.add(new Label("Explosions by chabull", skin));
		labels.add(new Label("Music by Matthew Pablo http://www.matthewpablo.com", skin));
		labels.add(new Label("Those above assets are all found from http://opengameart.org/", skin));
		labels.add(new Label("Sound effects made with bfxr http://www.bfxr.net/", skin));
		labels.add(new Label("Everything else (buttons, gui, stars etc) made by Korppi", skin));
		labels.add(new Label("", skin));
		labels.add(new Label("PRESS 'ESC' TO RETURN TO MENU!", skin));
		table = new Table();
		table.setFillParent(true);
		batch = new SpriteBatch();
		for (int i = 0; i < labels.size; i++){
			table.add(labels.get(i)).padBottom(10);
			table.row();
		}
		stage.addActor(table);
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
