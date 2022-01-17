package com.kayu.springandsummer.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kayu.springandsummer.entity.Entity;
import com.kayu.springandsummer.entity.SampleEntities;

@Service
public class EntityManagerImpl implements EntityManager {

    private static final String ENTITY_ALREADY_EXISTS_MESSAGE = "Entity %s already exists.";
    private static final String ENTITY_DOES_NOT_EXIST_MESSAGE = "%s does not exists";
    private static final int BASE_BALANCE = 1000;

    private final List<Entity> entities = SampleEntities.generateEntities();
    private final Random random = new Random();

    private int getRandomBalance() {
        return random.nextInt(BASE_BALANCE);
    }

    @Override
    public void saveEntity(String name, int age) {
        if (isPresent(name)) {
            throw new IllegalArgumentException(String.format(ENTITY_ALREADY_EXISTS_MESSAGE, name));
        }
        Entity newEntity = new Entity(name, age, getRandomBalance());
        entities.add(newEntity);
    }

    @Override
    public boolean isPresent(String name) {
        return entities.stream().anyMatch(e -> e.getName().equals(name));
    }

    @Override
    public Entity getEntity(String name) {
        return entities.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(String.format(ENTITY_DOES_NOT_EXIST_MESSAGE, name))
                );
    }

    @Override
    public void updateBalance(String name, int balance) {
        Entity entity = getEntity(name);
        entity.setBalance(balance);
    }

    @Override
    public void deleteEntity(String name) {
        Entity entity = getEntity(name);
        entities.remove(entity);
    }
}
