package com.person;

public class Person{
    public String name;
    public int age;
    public int hight;
    public boolean isRightHanded; 
    public Person(){
    }
    public Person(String name, int age, int hight, boolean isRightHanded){
        this.name = name;
        this.age = age;
        this.hight = hight;
        this.isRightHanded = isRightHanded;
    }
    public String collected(String colection){
        return "They found a: " + colection; 
    }
    public void sleep(){
        System.out.println("zzzz");
    }
}


