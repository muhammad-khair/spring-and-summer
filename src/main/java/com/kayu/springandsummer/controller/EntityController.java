package com.kayu.springandsummer.controller;

import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kayu.springandsummer.entity.BaseEntity;
import com.kayu.springandsummer.entity.UpdateEntity;
import com.kayu.springandsummer.service.EntityService;
import com.kayu.springandsummer.tools.LogsCenter;

@RestController
public class EntityController {

    private static final Logger LOG = LogsCenter.getLogger(EntityController.class);

    @Autowired
    private EntityService entityService;

    @GetMapping("/greet")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        LOG.info(String.format("GET /greet name=%s", name));
        return entityService.generateMessage(name);
    }

    @PostMapping(value = "/saveEntity")
    public String saveEntity(@RequestBody BaseEntity newEntity) {
        LOG.info(String.format("POST /saveEntity %s", newEntity));
        try {
            entityService.saveEntity(newEntity.getName(), newEntity.getAge());
            return greet(newEntity.getName());

        } catch (IllegalArgumentException e) {
            return e.getLocalizedMessage();
        }
    }

    @PutMapping("/updateBalance")
    public String updateBalance(@RequestBody UpdateEntity updatedEntity) {
        LOG.info(String.format("PUT /updateBalance %s", updatedEntity));
        try {
            entityService.updateEntity(updatedEntity.getName(), updatedEntity.getBalance());
            return greet(updatedEntity.getName());

        } catch (IllegalArgumentException e) {
            return e.getLocalizedMessage();
        }
    }

    @DeleteMapping("/deleteEntity")
    public String deleteEntity(@RequestBody Map<String, String> nameMap) {
        LOG.info(String.format("DELETE /deleteEntity %s", nameMap));
        try {
            if (!nameMap.containsKey("name") || nameMap.size() != 1) {
                return greet("World");
            }
            entityService.deleteEntity(nameMap.get("name"));
            return greet("World");

        } catch (IllegalArgumentException e) {
            return e.getLocalizedMessage();
        }
    }
}
