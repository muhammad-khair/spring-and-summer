package com.kayu.springandsummer.entity;

public interface EntityManager {
    void saveEntity(String name, int age);
    void updateBalance(String name, int balance);
    void deleteEntity(String name);
    boolean isPresent(String name);
    Entity getEntity(String name);
}
