package com.rpm;

import java.util.List;

public class Entity implements Comparable<Entity> {
    private static int idCounter = 1;
    protected final long id = idCounter;
    private World world;
    private String title;
    private double posX;
    private double posZ;
    private boolean aggressive;
    protected int maxHealth;
    protected int health;
    private int attackDamage;
    private Entity target;

    public Entity() {
        idCounter++;
    }

    public Entity(String title, double posX, double posZ, boolean aggressive, int maxHealth, int attackDamage) {
        this();
        this.title = title;
        this.posX = posX;
        this.posZ = posZ;
        this.aggressive = aggressive;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackDamage = attackDamage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosZ() {
        return posZ;
    }

    public void setPosZ(double posZ) {
        this.posZ = posZ;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public long getId() {
        return id;
    }

    public void update() {
        if (aggressive) {
            if (target == null) {
                searchTarget();
            }

            if (target != null) {
                moveEntity(target.getPosX(), target.getPosZ());
            }
        }
    }

    public void searchTarget() {
        List<Entity> entitiesList = World.getEntitiesNearEntity(this, 20);
        for (int i = 0; i < entitiesList.size(); i++) {
            if (!entitiesList.get(i).aggressive) {
                target = entitiesList.get(i);
                break;
            }
        }
    }

    public void attack(Entity entity) {
        if (!(entity instanceof EntityPlayer)) {
            damageEntity(entity);
        } else {
            damageEntity(entity);
            if (entity.health > 0) {
                entity.attack(this);
            }
            if (entity.health <= 0) {
                target = null;
            }
            if (this.health <= 0) {
                world.getEntities().remove(this);
            }
        }
    }

    public void damageEntity(Entity entity) {
        entity.health -= this.attackDamage + 0.5 * GameServer.getInstance().getDifficulty();
    }

    private void moveEntity(double posX, double posZ) {
        moveByX(posX);
        moveByZ(posZ);
    }

    private void moveByX (double x) {
        if (this.posX < x) {
            this.posX++;
        } else if (this.posX > x) {
            this.posX--;
        }
    }

    private void moveByZ (double z) {
        if (this.posZ < z) {
            this.posZ++;
        } else if (this.posZ > z) {
            this.posZ--;
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", posX=" + posX +
                ", posZ=" + posZ +
                ", aggressive=" + aggressive +
                ", maxHealth=" + maxHealth +
                ", health=" + health +
                ", attackDamage=" + attackDamage +
                '}' + '\n';
    }

    @Override
    public int compareTo(Entity o) {
        return Double.compare(MathOperations.getPointsAbs(getPosX(), getPosZ()), MathOperations.getPointsAbs(o.getPosX(), o.getPosZ()));
    }
}
