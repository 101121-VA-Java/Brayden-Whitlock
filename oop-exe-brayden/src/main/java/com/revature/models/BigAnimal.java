package com.revature.models;

public class BigAnimal extends Animal {
	int waight = 80;
	public BigAnimal() {
		super();
	}
	@Override
	public String whatDoesTheAnimalSay(String sound) {
		sound = sound.toUpperCase();
		return sound;
	}
	
	public int hungry() {
		int foodAmount = this.food(waight);
		foodAmount += 1;
		return foodAmount;
	}
	
}
