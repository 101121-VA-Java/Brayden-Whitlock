package com.revature.driver;

import com.revature.models.Human;

public class Driver{
    public static void main(String[] args){
        Human kevin = new Human();
        Human dean = new Human("Dean");
        System.out.println(dean.name);

    }
}