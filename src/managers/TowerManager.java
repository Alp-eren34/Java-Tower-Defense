package managers;

import enemies.Bat;
import enemies.Enemy;
import helps.Constants;
import helps.LoadSave;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helps.Constants.Towers.*;

public class TowerManager {

    private Playing playing;
    private BufferedImage[] towerImgs;
    private ArrayList<Tower> towers =  new ArrayList<>();
    private int towerAmount = 0;

    public TowerManager(Playing playing){
        this.playing = playing;

        loadTowerImgs();
    }


    private void loadTowerImgs(){
        BufferedImage atlas = LoadSave.getSpriteAtlas2();
        towerImgs = new BufferedImage[3];
        for(int i = 0; i<3; i++){
            towerImgs[i] = atlas.getSubimage((i)*32, 32, 32, 32);
        }
    }


    public void draw(Graphics g){
        for(Tower t : towers){
            g.drawImage(towerImgs[t.getTowerType()],t.getX(),t.getY(),null);
        }
//        g.drawImage(towerImgs[ARCHER],tower.getX(),tower.getY(),null);
    }

    public void update() {
        for(Tower t : towers){
            t.update();
            attackEnemyIfClose(t);
        }
    }

    //sona ve kurala uyan en yakin dusmana ates etme fonksiyonu
    private void attackEnemyIfClose(Tower t) {
        Enemy bestTarget = null; // Hedeflenecek en iyi düşman
        int minDistanceToEnd = Integer.MAX_VALUE; // En kısa mesafe

        // Bitiş noktasının koordinatlarını almak (Piksel cinsinden)
        int endX = playing.getEnd().getxCoord() * 32;
        int endY = playing.getEnd().getyCoord() * 32;

        for (Enemy e : playing.getEnemyManager().getEnemies()) {
            if (e.isAlive()) {
                // Eğer düşman kulenin menzilindeyse
                if (isEnemyInRange(t, e)) {

                    // Bu düşmanın bitiş noktasına olan uzaklığını hesapla
                    int distToEnd = helps.Utilz.getHypoDistance(e.getX(), e.getY(), endX, endY);

                    // Eğer bu düşman, şu ana kadar bulduğumuz diğer düşmanlardan
                    // bitişe daha yakınsa, yeni hedefimiz bu olsun.
                    if (distToEnd < minDistanceToEnd) {
                        minDistanceToEnd = distToEnd;
                        bestTarget = e;
                    }
                }
            }
        }
        // Eğer uygun bir hedef bulduysak ve kulenin cooldown'ı dolduysa ateş et
        if (bestTarget != null) {
            if (t.isCooldownOver()) {
                playing.shootEnemy(t, bestTarget);
                t.resetCooldown();
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        //Eger tower top ve hedef de yarasa ise bomba yollamayacak
        if(t.getTowerType()==CANON && e.getEnemyType() == Constants.Enemies.BAT){
            return false;
        }

        int range = helps.Utilz.getHypoDistance(t.getX(),t.getY(),e.getX(),e.getY());
        if(range < t.getRange()){
            return true;
        }
        return false;
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }

    public void addTower(Tower selectedTower,int xPos,int yPos) {
        towers.add(new Tower(xPos,yPos,towerAmount++,selectedTower.getTowerType()));
        String kuleAdi = Constants.Towers.GetName(selectedTower.getTowerType());
        LoadSave.WriteToLog(kuleAdi + " kulesi, "+xPos+","+yPos+" konumuna yerlestirildi, maaliyeti: "+Constants.Towers.GetTowerCost(selectedTower.getTowerType()));
    }

    public Tower getTowerAt(int x, int y) {
        for(Tower t : towers){
            if(t.getX() == x) {
                if(t.getY() == y) {
                    return t;
                }
            }
        }
        return null;
    }

    public void reset(){
        towers.clear();
        towerAmount = 0;
    }
}
