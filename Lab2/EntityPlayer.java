package com.rpm;

public class EntityPlayer extends Entity {
    private String nickname;
    private int updateCounter = 0;

    public EntityPlayer(String title, double posX, double posZ, int maxHealth, int attackDamage, String nickname) {
        super(title, posX, posZ, false, maxHealth, attackDamage);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void update() {
        super.update();
        updateCounter++;
        if (updateCounter % 2 == 0) {
            if (health < maxHealth) {
                health++;
            }
        }
    }
}
