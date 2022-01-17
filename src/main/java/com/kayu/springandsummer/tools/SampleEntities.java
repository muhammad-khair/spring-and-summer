package com.kayu.springandsummer.tools;

import java.util.ArrayList;
import java.util.List;

import com.kayu.springandsummer.entity.Entity;

public class SampleEntities {
    
    public static List<Entity> generateEntities() {
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity("Kayu", 24, 123));
        entities.add(new Entity("Sam", 35, 985));
        entities.add(new Entity("Gel", 19, 275));
        return entities;
    }

}
