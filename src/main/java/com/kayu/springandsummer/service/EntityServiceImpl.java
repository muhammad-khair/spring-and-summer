package com.kayu.springandsummer.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kayu.springandsummer.entity.Entity;
import com.kayu.springandsummer.tools.LogsCenter;

@Service
public class EntityServiceImpl implements EntityService {

    private static final Logger LOG = LogsCenter.getLogger(EntityServiceImpl.class);
    private static final String DEFAULT_GREETING_MESSAGE = "Hello %s! "
            + "Sign up now with a balance under POST /saveEntity";
    private static final String ENTITY_TEMPLATE_MESSAGE = "Hello %d-year-old %s! Your balance now is %d";

    @Autowired
    private EntityManager entityManager;

    @Override
    public String generateMessage(String name) {
        if (!entityManager.isPresent(name)) {
            LOG.info("Entity is not present, generating default message");
            return String.format(DEFAULT_GREETING_MESSAGE, name);
        }
        LOG.info(String.format("Generating message for %s", name));
        Entity entity = getEntity(name);
        return String.format(ENTITY_TEMPLATE_MESSAGE, entity.getAge(), entity.getName(), entity.getBalance());
    }

    @Override
    public Entity getEntity(String name) {
        return entityManager.getEntity(name);
    }

    @Override
    public void saveEntity(String name, int age) {
        entityManager.saveEntity(name, age);
    }

    @Override
    public void updateEntity(String name, int balance) {
        entityManager.updateBalance(name, balance);
    }

    @Override
    public void deleteEntity(String name) {
        entityManager.deleteEntity(name);
    }

}
