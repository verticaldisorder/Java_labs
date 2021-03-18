package com.rpm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class World {
    private int id;
    private String worldName;
    protected static List<Entity> entities;

    public World(int id, String worldName, List<Entity> entities) {
        this.id = id;
        this.worldName = worldName;
        this.entities = entities;
    }

    public World() {
    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }
    }

    public static List<Entity> getEntitiesInRegion(double x, double z, double range) {
        List<Entity> entitiesRegionList = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            if ((MathOperations.getDistanceBetweenPoints(entities.get(i).getPosX(), entities.get(i).getPosZ(), x, z)) <= range) {
                entitiesRegionList.add(entities.get(i));
            }
        }
        Collections.sort(entitiesRegionList);
        return entitiesRegionList;
    }

    public static List<Entity> getEntitiesNearEntity(Entity entity, double range) {
        return getEntitiesInRegion(entity.getPosX(), entity.getPosZ(), range);
    }

    public List<Entity> getEntities() {
        return entities;
    }
}
