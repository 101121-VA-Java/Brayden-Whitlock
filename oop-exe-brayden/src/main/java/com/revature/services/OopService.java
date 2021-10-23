package com.revature.services;

import com.revature.models.BigAnimal;
import com.revature.models.SmallAnimal;

public class OopService {
	
	// Encapsulation is separation of variables within methods and classes 
	public void doEncapsulation() {
		BigAnimal bull = new BigAnimal();
		SmallAnimal bird = new SmallAnimal();
		
		bull.setNumberOfLegs(4);
		bird.setNumberOfLegs(2);
		
		System.out.println("The bull has: " + bull.getNumberOfLegs() + " legs");
		System.out.println("The bird has: " + bird.getNumberOfLegs() + " legs");
	}
	// Inheritance is the passing down of charicturistics (code) form parent to child classes  
	public void doInheritance() {
		BigAnimal ba = new BigAnimal();
		int foodAmount = ba.hungry();
		System.out.println("The hungry animal gets: " + foodAmount + " food");
	}
	// Polymorphism is method overloading and overwriting
	public void doPolymorphism() {
		
		//Overloading 
		BigAnimal a = new BigAnimal();
		SmallAnimal a1 = new SmallAnimal();
		a.setName("Bull");
		a.setType("Mamal");
		a.setNumberOfLegs(4);
		a.setCanFly(false);
		
		System.out.println("This should print toString() from BigAnimal class: " + a);
		System.out.println("This should print toString() from SmallAnimal class: " + a1);
		
		//Overwriting
		String sound = a1.whatDoesTheAnimalSay("Booo");
		
		System.out.println("This should print whatDoesTheAnimalSay() from SmallAnimal class: " + sound);	
	}
	// Abstraction is the process of identifying only the required charictureistics of an object (hiding the parent class)
	public void doAbstraction() {
		BigAnimal ba = new BigAnimal();
		SmallAnimal sa = new SmallAnimal();
		
		String bigNoise = ba.whatDoesTheAnimalSay("Moo");
		String littleNoise = sa.whatDoesTheAnimalSay("Tweet");
		
		System.out.println("The bull says: " + bigNoise);
		System.out.println("The bird says: " + littleNoise);
	}
	
	public void doException() {
		try {
			// put risky code here | code that can throw an exception
			int b = 1/0;
			System.out.println(b);
			
		} catch (Exception e) {
			System.out.println("This is the problem:\n");
			e.printStackTrace();
		}
	}
}
