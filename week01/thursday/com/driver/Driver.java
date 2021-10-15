package com.driver;
import com.person.*;

public class Driver{
    public static void main(String[] args){
        Person ben = new Person("Ben", 24, 6, true);
        System.out.println(ben.name);
        System.out.println(ben.age);
        System.out.println(ben.hight);
        System.out.println(ben.isRightHanded);

        System.out.println(ben.collected("Ring"));
        ben.sleep();
    }
}