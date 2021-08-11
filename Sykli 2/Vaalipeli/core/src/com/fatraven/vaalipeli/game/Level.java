package com.fatraven.vaalipeli.game;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.AlphaAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.fatraven.vaalipeli.game.objects.Answer;
import com.fatraven.vaalipeli.game.objects.Dialog;
import com.fatraven.vaalipeli.game.objects.Player;
import com.fatraven.vaalipeli.screens.MenuScreen;

public class Level{
	private Array<Dialog> dialogs;
	private Stage stage;
	private Array<TextButton> buttons;
	private Label dialogText;
	private Table table;
	private Skin skin;
	private Dialog activeDialog;
	private Array<Answer> activeAnswers;
	private Player player;
	private AlphaAction alphaFadeIn;
	private AlphaAction alphaFadeOut;
	private int maxPoints;
	private int piirrosTila;
	private Game game;
	
	public Level(final Game game){
		this.game = game;
		init();
	}
	
	private void init() {
		piirrosTila = 0;
		maxPoints = 0;
		player = new Player();
		dialogs = new Array<Dialog>();
		parseXML();
		stage = new Stage(); //testataan vakio viewportilla miten toimii
		table = new Table();
		table.setFillParent(true);
		table.setPosition(0, 50);
		buttons = new Array<TextButton>();
		skin = new Skin(Gdx.files.internal("GUI/gui.json"),
				new TextureAtlas("GUI/gui.atlas"));
		
		dialogText = new Label("lˆl", skin);
		dialogText.setWrap(true);
		table.add(dialogText).width(700).height(400).padBottom(20);
		table.row();
		for (int i = 0; i < 4; i++){
			final int hoo = i;
			buttons.add(new TextButton("lol", skin));
			buttons.get(i).getLabel().setWrap(true);
			buttons.get(i).getLabel().setTouchable(Touchable.disabled);
			buttons.get(i).addListener(new ClickListener(){
				public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
					if (!Gdx.input.isButtonPressed(Buttons.LEFT)){
						Assets.instance.sounds.buttonEnter.play();
					}
				}
				
				public void clicked(InputEvent event, float x, float y)
		        {
					if (activeAnswers.get(hoo).isRightAnswer()){
						player.addPoint();
						Assets.instance.sounds.right.play();
					} else {
						if (activeDialog.getId().equals("d30")) Assets.instance.sounds.right.play();
						else if (
								activeDialog.getId().equals("d1") ||
								activeDialog.getId().equals("d3") ||
								activeDialog.getId().equals("d4") ||
								activeDialog.getId().equals("d6") ||
								activeDialog.getId().equals("d7") ||
								activeDialog.getId().equals("d9") ||
								activeDialog.getId().equals("d10") ||
								activeDialog.getId().equals("d12") ||
								activeDialog.getId().equals("d13") ||
								activeDialog.getId().equals("d15") ||
								activeDialog.getId().equals("d16") ||
								activeDialog.getId().equals("d17") ||
								activeDialog.getId().equals("d19") ||
								activeDialog.getId().equals("d20") ||
								activeDialog.getId().equals("d22") ||
								activeDialog.getId().equals("d23") ||
								activeDialog.getId().equals("d26") ||
								activeDialog.getId().equals("d27") ||
								activeDialog.getId().equals("d29") ||
								activeDialog.getId().equals("d31") ||
								activeDialog.getId().equals("d32") ||
								activeDialog.getId().equals("d33") ||
								activeDialog.getId().equals("d34") ||
								activeDialog.getId().equals("d35")){
							Assets.instance.sounds.buttonClick.play();
						} else {
							Assets.instance.sounds.wrong.play();
						}
					}
					if (activeDialog.getId().equals("d30") || activeDialog.getId().equals("d29")){
						piirrosTila++;
					}
		            if (activeDialog.getId().equals("d31")){
		            	if (player.getPoints() == maxPoints){
		            		loadDialogAndAnswers("d32");
		            	} else if (player.getPoints() / maxPoints >= 0.5f){
		            		loadDialogAndAnswers("d33");
		            	} else if (player.getPoints() == 0){
		            		loadDialogAndAnswers("d35");
		            	} else {
		            		loadDialogAndAnswers("d34");
		            	}
		            	dialogText.setText("Pisteet: " + player.getPoints() + "/" + maxPoints + ".\n" + dialogText.getText());
		            } else if (activeAnswers.get(hoo).getNextDialog().equals("d50")){
		            	init();
		            } else if (activeAnswers.get(hoo).getNextDialog().equals("d51")){
		            	game.setScreen(new MenuScreen(game));
		            } else if (activeAnswers.get(hoo).getNextDialog().equals("d52")){
		            	Gdx.app.exit();
		            } else {
		            	loadDialogAndAnswers(activeAnswers.get(hoo).getNextDialog());
		            }
		            if (activeDialog.getId().equals("d17")){
		            	piirrosTila++;
		            }
		        }
			});
			table.add(buttons.get(i)).padBottom(10).width(700);
			table.row();
		}
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
		alphaFadeIn = new AlphaAction();
		alphaFadeIn.setDuration(0.5f);
		alphaFadeIn.setAlpha(1);
		alphaFadeOut = new AlphaAction();
		alphaFadeOut.setDuration(0);
		alphaFadeOut.setAlpha(0);
		loadDialogAndAnswers("d1");
	}

	private void loadDialogAndAnswers(String dialogId) {
		alphaFadeIn.restart();
		alphaFadeOut.restart();
		for (int i = 0; i < dialogs.size; i++){
			if (dialogs.get(i).getId().equals(dialogId)){
				activeDialog = dialogs.get(i);
				break;
			}
		}
		dialogText.setText(activeDialog.getText());
		activeAnswers = activeDialog.getAnswers();
		for (int i = 0; i < buttons.size; i++){
			if (i >= activeAnswers.size){
				buttons.get(i).setVisible(false);
			} else {
				buttons.get(i).setText(activeAnswers.get(i).getText());
				buttons.get(i).setVisible(true);
				if (buttons.get(i).isChecked()) buttons.get(i).toggle();
			}
		}
		table.addAction(alphaFadeOut);
		table.addAction(alphaFadeIn);
	}

	private void parseXML() {
		XmlReader reader = new XmlReader();
		Dialog tempDialog;
		Answer tempAnswer;
		try {
			Element root = reader.parse(Gdx.files.internal("dialogs/dialogs.xml")); //lukee xml
			Array<Element> dialogs = root.getChildrenByName("dialog"); //kaikki dialogit
			for (Element child : dialogs){
				tempDialog = new Dialog(); //temporary dialogi, revit‰‰n siihen tekstis
				tempDialog.setText(child.getChildByName("text").getText());
				tempDialog.setId(child.getAttribute("id"));
				Array<Element> answers = child.getChildrenByName("answer"); //kaikki vastaukset
				for (Element childAnswer : answers){
					tempAnswer = new Answer();
					tempAnswer.setId(childAnswer.getAttribute("id"));
					tempAnswer.setNextDialog(childAnswer.getAttribute("dialogid"));
					if (childAnswer.getAttribute("status").equals("f")) tempAnswer.setRightAnswer(false);
					else {
						tempAnswer.setRightAnswer(true);
						maxPoints++;
					}
					tempAnswer.setText(childAnswer.getText());
					tempDialog.addAnswer(tempAnswer); //lis‰t‰‰n vastaus dialogiin
				}
				this.dialogs.add(tempDialog); //lis‰t‰‰n dialogi pelin dialogiin
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(SpriteBatch batch){
		if (piirrosTila == 0){
			batch.draw(Assets.instance.grafs.house, -40, -30, 0, 0, 800, 600, 0.1f, 0.1f, 0);
			batch.draw(Assets.instance.grafs.table, -40, -30, 0, 0, 800, 336, 0.1f, 0.1f, 0);
		} else if (piirrosTila == 2){
			batch.draw(Assets.instance.grafs.votingBox, -40, -30, 0, 0, 800, 600, 0.1f, 0.1f, 0);
		} else {
			batch.draw(Assets.instance.grafs.votingHouse, -40, -30, 0, 0, 800, 600, 0.1f, 0.1f, 0);
		}
	}

	public void update(float deltaTime) {
		stage.act(deltaTime);
	}

	public void renderGUI() {
		stage.draw();
	}
	
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}
}
