package com.fatraven.vaalipeli.game.objects;

import com.badlogic.gdx.utils.Array;

public class Dialog {
	private String id;
	private String text;
	private Array<Answer> answers;
	
	public Dialog(){
		answers = new Array<Answer>();
	}
	
	public void addAnswer(Answer answer){
		answers.add(answer);
	}
	
	public void reset(){
		answers.clear();
	}
	
	public Answer getAnswer(String id){
		for (int i = 0; i < answers.size; i++){
			if (answers.get(i).getId().equals(id)) return answers.get(i);
		}
		return null;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getText(){
		return text;
	}
	
	public String getId(){
		return id;
	}

	public Array<Answer> getAnswers() {
		return answers;
	}
}
