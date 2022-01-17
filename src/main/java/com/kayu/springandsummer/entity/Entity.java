package com.kayu.springandsummer.entity;

import java.util.Objects;

import org.springframework.lang.NonNull;

public class Entity {
    
    @NonNull
    private final String name;
    private final int age;
    private int balance;

    public Entity(String name, int age, int balance) {
        this.name = name;
        this.age = age;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return age == entity.age && balance == entity.balance && name.equals(entity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, balance);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                '}';
    }
}
