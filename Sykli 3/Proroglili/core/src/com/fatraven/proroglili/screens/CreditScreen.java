package com.fatraven.proroglili.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.proroglili.ProRogLiLi;
import com.fatraven.proroglili.game.Assets;

public class CreditScreen extends MyScreen{
	private Stage stage;
	private Table table;
	private Array<Label> labels;
	private SpriteBatch batch;
	private TextButton backButton;

	public CreditScreen(final ProRogLiLi game) {
		super(game);
		batch = new SpriteBatch();
		stage = new Stage(new FitViewport(800, 600));
		table = new Table();
		table.setFillParent(true);
		labels = new Array<Label>();
		
		backButton = new TextButton("Back to menu", Assets.instance.misc.skin);
		backButton.getLabel().setTouchable(Touchable.disabled);
		backButton.addListener(new ClickListener(){
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
				if (!Gdx.input.isButtonPressed(Buttons.LEFT)) Assets.instance.mouseSounds.over.play();
			}
			
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
				Assets.instance.mouseSounds.out.play();
			}
			
			public void clicked(InputEvent event, float x, float y){
				Assets.instance.mouseSounds.click.play();
				game.setScreen(game.menuScreen);
			}
		});
		labels = new Array<Label>();
		labels.add(new Label("Menu wallpaper by Ecrivain", Assets.instance.misc.skin));
		labels.add(new Label("Menu music by rezoner (http://soundcloud.com/rezoner/)", Assets.instance.misc.skin));
		labels.add(new Label("Map graphics by Lucid Design (http://luciddesign.tk/)", Assets.instance.misc.skin));
		labels.add(new Label("Everything above were found from http://opengameart.org/", Assets.instance.misc.skin));
		labels.add(new Label("Menu sound effects made with bfxr (http://www.bfxr.net/)", Assets.instance.misc.skin));
		labels.add(new Label("'ProRogLiLi' logo at menu made with supalogo (supalogo.com)" , Assets.instance.misc.skin));
		labels.add(new Label("Menu buttons made by Korppi", Assets.instance.misc.skin));
		
		for (int i = 0; i < labels.size; i++) {
			table.add(labels.get(i)).row();
		}
		
		table.add(backButton).width(200).padTop(20);
		stage.addActor(table);		
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
		// TODO Auto-generated method stub
		
	}

}
