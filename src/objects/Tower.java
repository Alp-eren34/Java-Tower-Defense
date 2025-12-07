package objects;

import helps.Constants;

public class Tower {

    private int x,y,id,towerType,cdTick,damage;
    private float range,coolDown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultCooldown();
        setDefaultDamage();
        setDefaultRange();
    }

    private void setDefaultCooldown() {
        coolDown=Constants.Towers.GetDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range=Constants.Towers.GetDefaultRange(towerType);
    }

    private void setDefaultDamage() {
        damage=Constants.Towers.GetStartDamage(towerType);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public float getRange() {
        return range;
    }

    public int getDamage() {
        return damage;
    }

    public float getCoolDown() {
        return coolDown;
    }

    public boolean isCooldownOver() {
        return cdTick >= coolDown;
    }

    public void resetCooldown() {
        cdTick = 0;
    }
    public void update(){
        cdTick++;
    }
}
