package enemies;

import helps.Constants;
import helps.LoadSave;
import managers.EnemyManager;

import java.awt.Rectangle;

import static helps.Constants.Direction.*;

public abstract class Enemy {

    protected float x,y;
    protected Rectangle bounds;
    protected int health;
    protected int maxHealth;
    protected int ID;
    protected int enemyType;
    protected int lastDir;
    protected boolean alive =true;
    protected int slowtickLimit=120;
    protected int slowTick = slowtickLimit;
    protected EnemyManager enemyManager;

    public Enemy(float x, float y, int ID, int enemyType, EnemyManager enemyManager){
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.enemyType = enemyType;
        this.enemyManager = enemyManager;
        bounds = new Rectangle((int) x ,(int) y ,32,32);
        lastDir = -1;
        setStartHealth();
    }

    private void setStartHealth(){//dusmanin canini belirliyor cekiyor constants den
        health = Constants.Enemies.GetStartHealth(enemyType);
        maxHealth=health;
    }

    public void hurt(int damage,int projecTileType){
        //ucan  bir dusman olup olmadigi kontrol ediliyor
        if (this.enemyType == Constants.Enemies.BAT &&  projecTileType== Constants.Projectiles.BOMB) {
            return;//eger ucan bir dusmansa hasar vurmadan geri donuyor
        }
        int netDamage;
        String logMesaji="";
        if(Constants.Enemies.GetEnemyArmor(enemyType) != 0){//burada dusmanin zirhina gore hesap yapip ona gore net hasari hesapliyor
            int enemyArmor=Constants.Enemies.GetEnemyArmor(enemyType);
            netDamage =(int) (damage * (1.0f-((float)enemyArmor/(enemyArmor+100))));
            this.health -= netDamage;
            logMesaji = " (Taban Hasar: " + damage + ", Zirh: " + enemyArmor + " -> Net Hasar: " + netDamage + ")";
        }else{
            this.health-=damage;
            logMesaji = " (Net Hasar: " + damage + " - Zirh Yok)";
        }
        LoadSave.WriteToLog(Constants.Enemies.GetEnemyName(enemyType)+", Dusman-ID: " + this.ID+" hasar aldi"+logMesaji+". Kalan Can: "+this.health+"/"+this.maxHealth);
        if(this.health<=0){//dusmanlarin sagligi 0 a inince yok olmasi icin kontrol
            alive = false;
            enemyManager.rewardPlayer(enemyType);
            LoadSave.WriteToLog("Dusman-ID: " + this.ID + " oldu. Odul +" + Constants.Enemies.GetEnemyReward(enemyType)+" gold");
        }
    }



    public void move(float speed, int dir){
        lastDir = dir;

        if(slowTick < slowtickLimit){
            slowTick++;
            speed *= 0.5f;
        }

        switch (dir){
            case LEFT:
                this.x -= speed;
                break;
            case UP:
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
        updateHitbox();
    }

    private void updateHitbox() {
        bounds.x=(int) x;
        bounds.y=(int) y;

    }

    public void setPos(int x, int y){
        //bunu hareket icin kullanma, bu pozisyonu fixlemek icin
        this.x = x;
        this.y = y;
    }

    public float getHealthBarFloat(){
        return health/ (float) maxHealth;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getHealth() {
        return health;
    }

    public int getID() {
        return ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public int getLastDir() {
        return lastDir;
    }
    public boolean isAlive() {
        return alive;
    }

    public void slow() {
        slowTick = 0;
    }
    public boolean isSlow(){
        return slowTick < slowtickLimit;
    }

    public void kill() {
        //dusmalnalri oldurur sona geldiginde vb
        alive = false;
        health = 0;
    }
}
