package com.fatraven.astroBlasterClonev2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.fatraven.astroBlasterClonev2.AstroBlaster;
import com.fatraven.astroBlasterClonev2.game.Assets;

public class MenuScreen extends AbstractScreen{
	private Stage stage;
	private Table table;
	private Array<TextButton> buttons;
	private SpriteBatch batch;
	private Image logo;
	private int menuButton;
	private AstroBlaster game;
	
	public MenuScreen(final AstroBlaster game) {
		this.game = game;
		menuButton = 0;
		batch = new SpriteBatch();
		stage = new Stage(new FitViewport(800, 600));
		table = new Table();
		table.setFillParent(true);
		buttons = new Array<TextButton>();
		buttons.add(new TextButton("Start", Assets.instance.gui.skin));
		buttons.add(new TextButton("Credits", Assets.instance.gui.skin));
		buttons.add(new TextButton("Quit", Assets.instance.gui.skin));
		logo = new Image(Assets.instance.misc.logo);
		table.add(logo).padBottom(40);
		table.row();
		for (TextButton button : buttons){
			table.add(button).width(150).padBottom(30);
			table.row();
		}
		stage.addActor(table);
		buttons.get(0).toggle();
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
			menuButton = menuButton % 3;
			buttons.get(menuButton).toggle();
			int i = 0;
			for (TextButton button : buttons){
				if (i != menuButton && button.isChecked()){
					button.toggle();
				}
				i++;
			}
			Assets.instance.sounds.select.play();
		}
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)){
			switch (menuButton) {
			case 0:
				game.setScreenByType(ScreenType.GAMESCREEN);
				break;
			case 1:
				game.setScreenByType(ScreenType.CREDITSCREEN);
				break;
			case 2:
				Gdx.app.exit();
				break;
			default:
				break;
			}
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
