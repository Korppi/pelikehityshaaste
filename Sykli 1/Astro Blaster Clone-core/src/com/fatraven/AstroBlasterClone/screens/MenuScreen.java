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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.AstroBlasterClone.game.Assets;
import com.fatraven.AstroBlasterClone.util.Constants;
import com.fatraven.AstroBlasterClone.util.GamePreferences;

public class MenuScreen extends AbstractScreen{
	private Stage stage;
	private TextButton btnStart;
	private Skin skin;
	private Table table;
	private TextButton btnExit;
	private SpriteBatch batch;
	private Label text;
	private int menuButton;
	private TextButton btnCredits;

	public MenuScreen(Game game) {
		super(game);
	}
	
	private void rebuildStage(){
		skin = new Skin(Gdx.files.internal(Constants.GUI_JSON),
				new TextureAtlas(Constants.GUI_NAME));
		text = new Label("ABC Game", skin);
		table = new Table();
		table.setFillParent(true);
		batch = new SpriteBatch();
		btnStart = new TextButton("Start", skin);
		table.add(text).padBottom(20).row();
		table.add(btnStart).padBottom(20).width(200);
		table.row();
		//table.setPosition(Constants.VIEWPORT_WIDTH_RES * 0.5f, Constants.VIEWPORT_HEIGHT_RES * 0.5f);
		btnStart.toggle();
		btnCredits = new TextButton("Credits", skin);
		table.add(btnCredits).padBottom(20).width(200);
		table.row();
		btnExit = new TextButton("Exit", skin);
		table.add(btnExit).width(200);
		stage.addActor(table);
		menuButton = 0;
	}

	@Override
	public void render(float deltaTime) {
		if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.DOWN)){
			int k = -1;
			if (Gdx.input.isKeyJustPressed(Keys.DOWN)){
				k = 1;
			}
			menuButton += k;
			if (menuButton < 0) menuButton = 2;
			//Gdx.app.debug("lol", "" + menuButton);
			menuButton = menuButton % 3;
			if (menuButton == 0){
				btnStart.toggle();
				if (btnExit.isChecked()) btnExit.toggle();
				if (btnCredits.isChecked()) btnCredits.toggle();
			}
			if (menuButton == 1){
				btnCredits.toggle();
				if (btnStart.isChecked()) btnStart.toggle();
				if (btnExit.isChecked()) btnExit.toggle();
			}
			if (menuButton == 2){
				btnExit.toggle();
				if (btnStart.isChecked()) btnStart.toggle();
				if (btnCredits.isChecked()) btnCredits.toggle();
			}
			Assets.instance.sounds.select.play();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			if (btnStart.isChecked()) game.setScreen(new GameScreen(game));
			else if (btnCredits.isChecked()){
				game.setScreen(new CreditsScreen(game));
			} else {
				Gdx.app.exit();
			}
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
		GamePreferences.instance.load();
		stage = new Stage(new FitViewport(Constants.VIEWPORT_WIDTH_RES, Constants.VIEWPORT_HEIGHT_RES));
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	@Override
	public void hide() {
		/*batch.dispose();
		stage.dispose();
		dispose();*/
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

}
