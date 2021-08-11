package com.fatraven.proroglili.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.proroglili.ProRogLiLi;
import com.fatraven.proroglili.game.Assets;

public class MenuScreen extends MyScreen{
	private Stage stage;
	private Table table;
	private TextButton startButton;
	private TextButton quitButton;
	private TextButton creditsButton;
	private SpriteBatch batch;
	private Image logo;
	
	public MenuScreen(final ProRogLiLi game) {
		super(game);
		batch = new SpriteBatch();
		stage = new Stage(new FitViewport(800, 600));
		table = new Table();
		table.setFillParent(true);
		startButton = new TextButton("Aloita", Assets.instance.misc.skin);
		creditsButton = new TextButton("Krediitit", Assets.instance.misc.skin);
		quitButton = new TextButton("Lopeta", Assets.instance.misc.skin);
		logo = new Image(Assets.instance.graphics.logo);
		table.add(logo).padBottom(10).row();
		table.add(startButton).width(200).padBottom(20).row();
		table.add(creditsButton).width(200).padBottom(20).row();
		table.add(quitButton).width(200);
		stage.addActor(table);
		
		startButton.getLabel().setTouchable(Touchable.disabled);
		quitButton.getLabel().setTouchable(Touchable.disabled);
		creditsButton.getLabel().setTouchable(Touchable.disabled);
		
		startButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)) Assets.instance.mouseSounds.over.play();
			}
			
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
				Assets.instance.mouseSounds.out.play();
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.mouseSounds.click.play();
				game.setScreen(game.gameScreen);
			}
		});
		
		creditsButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)) Assets.instance.mouseSounds.over.play();
			}
			
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
				Assets.instance.mouseSounds.out.play();
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.mouseSounds.click.play();
				game.setScreen(game.creditScreen);
			}
		});
		
		quitButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)) Assets.instance.mouseSounds.over.play();
			}
			
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
				Assets.instance.mouseSounds.out.play();
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.mouseSounds.click.play();
				Gdx.app.exit();
			}
		});
		Assets.instance.musics.menu.setLooping(true);
		Assets.instance.musics.menu.play();
	}

	@Override
	public void render(float deltaTime) {
		stage.act(deltaTime);
		Gdx.gl20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.begin();
		batch.draw(Assets.instance.graphics.backgroundMenu, 0, 0, 800, 600);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
	}
}
