package com.fatraven.vaalipeli.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.fatraven.vaalipeli.game.Assets;

public class MenuScreen extends AbstractScreen{
	private Stage stage;
	private SpriteBatch batch;

	public MenuScreen(final Game game) {
		super(game);
		batch = new SpriteBatch();
		stage = new Stage();
		Table table = new Table();
		table.setFillParent(true);
		Skin skin = new Skin(Gdx.files.internal("GUI/gui.json"),
				new TextureAtlas("GUI/gui.atlas"));
		
		TextButton startButton = new TextButton("Aloita peli", skin);
		TextButton quitButton = new TextButton("Lopeta peli", skin);
		
		startButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)){
					Assets.instance.sounds.buttonEnter.play();
				}
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.sounds.buttonClick.play();
				game.setScreen(new GameScreen(game));
			}
		});
		
		quitButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)){
					Assets.instance.sounds.buttonEnter.play();
				}
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.sounds.buttonClick.play();
				Gdx.app.exit();
			}
		});
		
		startButton.getLabel().setTouchable(Touchable.disabled);
		quitButton.getLabel().setTouchable(Touchable.disabled);
		
		table.add(startButton).width(200).padBottom(20);
		table.row();
		table.add(quitButton).width(200).padBottom(20);
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float deltaTime) {
		Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
		Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
		stage.act(deltaTime);
		batch.begin();
		batch.draw(Assets.instance.grafs.house, 0, 0);
		batch.draw(Assets.instance.grafs.table, 0, 0);
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		//TODO
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
