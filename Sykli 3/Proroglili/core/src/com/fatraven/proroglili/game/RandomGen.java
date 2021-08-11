package com.fatraven.proroglili.game;

import java.util.Random;

public class RandomGen {
	public static final RandomGen instance = new RandomGen();
	private Random random;
	
	private RandomGen(){}
	
	public void init(int seed){
		random = new Random();//(seed);
		System.out.println("seed: " + seed);
	}
	
	public Random getRandom(){
		return random;
	}
	
	public int getInt(int min, int max){
		return random.nextInt((max - min) + 1) + min; 
	}
	
	public int getIntOdd(int min, int max){
		int value = getInt(min, max);
		if (value % 2 == 0){
			if (value == max) value--;
			else value++;
		}
		return value;
	}
}
