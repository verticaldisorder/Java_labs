package com.rpm;

import java.util.Arrays;

public class GameServer {
    private static GameServer instance;
    private String ip;
    private int difficulty;
    private World serverWorld;
    private int serverTicks = 0;

    private Entity[] entities;

    public int getDifficulty() {
        return difficulty;
    }

    public Entity[] getEntities() {
        return entities;
    }

    public void setEntities(Entity[] entities) {
        this.entities = entities;
    }

    public static void main(String[] args) {
        new GameServer("IP", 2);
        World world = new World();
        Entity entity1 = new Entity("Josh", 15, 15, true, 6, 10);
        Entity entity2 = new Entity("Dun", 14, 15, false, 2, 2);
        Entity entity3 = new Entity("Tyler", 2, 7, true, 30, 5);
        Entity entity4 = new Entity("Joseph", 10, 10, true, 15, 3);
        Entity player1 = new EntityPlayer("Dallon", 1, 1, 9, 4, "BOLSHOY LOB");
        Entity player2 = new EntityPlayer("Ryan", 3, 2, 10, 8, "ETO KTO");
        Entity[] entities = {entity1, entity2, entity3, entity4, player1, player2};
        GameServer.instance.setEntities(entities);
        System.out.println(instance);
        for (int i = 0; i < 30; i++) {
            try {
                instance.updateServer();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(instance);
    }

    public static GameServer getInstance() {
        return instance;
    }

    public GameServer() {
        instance = this;
    }

    public GameServer(String ip, int difficulty) {
        this();
        this.ip = ip;
        this.difficulty = difficulty;
    }

    public void updateServer() {
        serverWorld.update();
        serverTicks++;
    }

    private void deleteDeadEntities() {
        for (int i = 0; i < entities.length; ++i) {
            if (entities[i] != null && entities[i].getHealth() <= 0) {
                entities[i] = null;
            }
        }
    }

    public void getBattleResults(Entity entity1, Entity entity2) {
        if (entity1.getHealth() <= 0 && entity2.getHealth() > 0) {
            System.out.println("Entity " + entity2.getTitle() + " killed entity " + entity1.getTitle());
        } else {
            System.out.println("Entity " + entity1.getTitle() + " killed entity " + entity2.getTitle());
        }
    }

    @Override
    public String toString() {
        return "GameServer{" +
                "ip='" + ip + '\'' +
                ", difficulty=" + difficulty +
                ", entities=" + Arrays.toString(entities) +
                '}';
    }
}
