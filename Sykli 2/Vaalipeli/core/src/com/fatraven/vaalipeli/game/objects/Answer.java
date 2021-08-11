package com.fatraven.vaalipeli.game.objects;

public class Answer {
	private String id;
	private String text;
	private boolean rightAnswer;
	private String nextDialog;
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public void setRightAnswer(boolean rightAnswer){
		this.rightAnswer = rightAnswer;
	}
	
	public void setNextDialog(String nextDialog){
		this.nextDialog = nextDialog;
	}
	
	public String getText(){
		return text;
	}
	
	public boolean isRightAnswer(){
		return rightAnswer;
	}
	
	public String getNextDialog(){
		return nextDialog;
	}
	
	public String getId(){
		return id;
	}
}
