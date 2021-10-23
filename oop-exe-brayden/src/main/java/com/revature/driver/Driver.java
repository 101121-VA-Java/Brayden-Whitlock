package com.revature.driver;

import java.util.Scanner;
import com.revature.interfaces.*;
import com.revature.services.*;
import com.revature.models.*;
import com.revature.services.OopService;

public class Driver {

	private static Scanner val = new Scanner(System.in);
	public static void main(String[] args) {
		//encapsulation, inheritance, polymorphism, abstraction 
		System.out.println("Enter 1-4: \n" + "1 = encapsulation\n" + "2 = inheritance\n" +
							"3 = polymorphism\n" + "4 = abstraction\n" + "5 = exception\n");
		int value = val.nextInt();
		OopService oop =  new OopService();
		switch(value){
			case 1: // encapsulation
				oop.doEncapsulation();
				break;
			case 2: //inheritance
				oop.doInheritance();
				break;
			case 3: //polymorphism
				oop.doPolymorphism();
				break;
			case 4: //abstraction
				oop.doAbstraction();
				break;
			case 5: //TryCatch
				oop.doException();
				break;
			default:
				break;		
		}
		
		val.close();
	}

}
