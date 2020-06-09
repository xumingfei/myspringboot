package com.practice.spring.java.extenddemo;

public class Test {
    public static void main(String[] args) {
        show(new Animal());
        show(new Dog());
        show(new Cat());

        Animal animal = new Cat()//向上转型
                ;
        animal.eat();
        Cat cat = (Cat) animal;//向下转型
        cat.eat();
    }

    public static void show(Animal animal) {
//        animal.eat();
        if (animal instanceof Cat) {
            Cat cat = (Cat) animal;
            cat.eat();
        } else if (animal instanceof Dog) {
            Dog dog = (Dog) animal;
            dog.eat();
        }
    }

}

