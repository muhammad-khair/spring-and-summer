package com.kayu.springandsummer.service;

import com.kayu.springandsummer.entity.Entity;

public interface EntityService {
    String generateMessage(String name);
    Entity getEntity(String name);
    void updateEntity(String name, int balance);
    void deleteEntity(String name);
    void saveEntity(String name, int age);
}
