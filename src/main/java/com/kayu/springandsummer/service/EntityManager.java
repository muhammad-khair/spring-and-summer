package com.kayu.springandsummer.service;

import com.kayu.springandsummer.entity.Entity;

public interface EntityManager {
    void saveEntity(String name, int age);
    void updateBalance(String name, int balance);
    void deleteEntity(String name);
    boolean isPresent(String name);
    Entity getEntity(String name);
}
