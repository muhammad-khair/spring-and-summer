package com.kayu.springandsummer.service;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.kayu.springandsummer.entity.Entity;
import com.kayu.springandsummer.tools.LogsCenter;
import com.kayu.springandsummer.tools.SampleEntities;

@Service
public class EntityManagerImpl implements EntityManager {

    private static final Logger LOG = LogsCenter.getLogger(EntityManagerImpl.class);
    private static final String ENTITY_ALREADY_EXISTS_MESSAGE = "Entity %s already exists.";
    private static final String ENTITY_DOES_NOT_EXIST_MESSAGE = "%s does not exists";
    private static final String NAME_IS_REQUIRED_MESSAGE = "Name field is required.";
    private static final String UNKNOWN_ERROR_MESSAGE = "Something went wrong, please try again!";
    private static final int BASE_BALANCE = 1000;

    private final List<Entity> entities = SampleEntities.generateEntities();
    private final Random random = new Random();

    private int getRandomBalance() {
        return random.nextInt(BASE_BALANCE);
    }

    private void throwIfNameIsNull(String name) {
        if (name == null) {
            LOG.warning(NAME_IS_REQUIRED_MESSAGE);
            throw new IllegalArgumentException(NAME_IS_REQUIRED_MESSAGE);
        }
    }

    @Override
    public void saveEntity(String name, int age) {
        throwIfNameIsNull(name);
        if (isPresent(name)) {
            LOG.warning(String.format(ENTITY_ALREADY_EXISTS_MESSAGE, name));
            throw new IllegalArgumentException(String.format(ENTITY_ALREADY_EXISTS_MESSAGE, name));
        }

        Entity newEntity = new Entity(name, age, getRandomBalance());
        entities.add(newEntity);
        LOG.info(String.format("%s added", newEntity));
    }

    @Override
    public boolean isPresent(String name) {
        return entities.stream().anyMatch(e -> e.getName().equals(name));
    }

    @Override
    public Entity getEntity(String name) {
        throwIfNameIsNull(name);
        if (!isPresent(name)) {
            LOG.warning(String.format(ENTITY_DOES_NOT_EXIST_MESSAGE, name));
            throw new IllegalArgumentException(String.format(ENTITY_DOES_NOT_EXIST_MESSAGE, name));
        }

        return entities.stream()
                .filter(e -> e.getName().equals(name))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalStateException(UNKNOWN_ERROR_MESSAGE)
                );
    }

    @Override
    public void updateBalance(String name, int balance) {
        throwIfNameIsNull(name);

        Entity entity = getEntity(name);
        int newBalance = Math.max(Math.max(0, balance), entity.getBalance());
        entity.setBalance(newBalance);
        LOG.info(String.format("%s updated", entity));
    }

    @Override
    public void deleteEntity(String name) {
        throwIfNameIsNull(name);

        Entity entity = getEntity(name);
        entities.remove(entity);
        LOG.info(String.format("%s deleted", entity));
    }

}
