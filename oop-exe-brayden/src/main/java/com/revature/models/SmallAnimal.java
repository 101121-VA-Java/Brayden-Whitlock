package com.revature.models;

public class SmallAnimal extends Animal  {

	public SmallAnimal() {
		
	}

	@Override
	public String whatDoesTheAnimalSay(String sound) {
		sound = sound.toLowerCase();
		return sound;
	}

}
