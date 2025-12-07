package scenes;

import enemies.Enemy;
import helps.Constants;
import helps.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helps.Constants.Tiles.GRASS_TILE;
import static main.GameStates.SetGameState;
import static main.GameStates.YOU_WON;


public class Playing extends GameScene implements SceneMethods{

    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX,mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projectileManager;
    private WaveManager waveManager;
    private PathPoint start,end;
    private Tower selectedTower;
    private int goldTick;

    public Playing(Game game) {
        super(game);

        loadDefaultLevel();
        actionBar = new ActionBar(0,640,640,160,this);

        enemyManager = new EnemyManager(this,start,end);
        towerManager = new TowerManager(this);
        projectileManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);

    }


    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }


    public void update(){
        updateTick();
        waveManager.update();

        //Gold tick
        goldTick++;
        if (goldTick % (60*3)==0){
            actionBar.addGold(1);
        }
        
        if(isAllEnemiesDead()){
            if(isTherehMoreWaves()){
                waveManager.startWaveTimer();
                if(isWaveTomerOver()){
                    waveManager.increaseWaveIndex();
                    enemyManager.getEnemies().clear();
                    waveManager.resetEnemyIndex();
                }
            }else{
                int health = actionBar.getLives();
                int money = actionBar.getGold();

                //Butun dusmanlar olduyse ve baska dalga kalmadiysa you won ekrani acilir
                String win="SON: Tum dalgalar temizlendi. OYUN KAZANILDI! (Kalan Can: " + health + ", Toplam Para: " + money + ")";
                LoadSave.WriteToLog(win);
                SetGameState(YOU_WON);
            }
        }

        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        enemyManager.update();
        towerManager.update();
        projectileManager.update();

    }

    private boolean isWaveTomerOver() {
            return  waveManager.isWaveTimerOver();
    }

    private boolean isTherehMoreWaves() {

        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {
        if(waveManager.isThereMoreEnemiesInWave()){
            return false;
        }
        for(Enemy e : enemyManager.getEnemies()){
            if(e.isAlive()){
                return false;
            }
        }
        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {

        if(waveManager.isTimeForNewEnemy()){
            if(waveManager.isThereMoreEnemiesInWave()){
                return true;
            }
        }
        return false;

    }
    @Override
    public void render(Graphics g) {

        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projectileManager.draw(g);

        drawSelectedTower(g);
        drawHighLight(g);

        drawWaveInfos(g);

    }

    private void drawWaveInfos(Graphics g) {
    }

    private void drawHighLight(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(mouseX,mouseY,32,32);
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null){
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()],mouseX,mouseY, null);
        }
    }

    private void drawLevel(Graphics g) {
        for(int y =0; y < lvl.length;y++){
            for(int x = 0; x < lvl[y].length; x++) {
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x * 32, y * 32, null);
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x * 32, y * 32, null);
                } else
                    g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }


    @Override
    public void mouseClicked(int x, int y) {
        if (y >= 640) {
            actionBar.mouseClicked(x, y);
        }
        else{
            if(selectedTower != null){
                if(isTileGrass(mouseX,mouseY)){
                    if(getTowerAt(mouseX,mouseY)==null){
                        towerManager.addTower(selectedTower,mouseX,mouseY);
                        removeGold(selectedTower.getTowerType());

                        selectedTower = null;
                    }
                }
            }else{
                Tower t = getTowerAt(mouseX,mouseY);
                actionBar.displayTower(t);
            }
        }
    }

    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);
    }


    private boolean isTileGrass(int x, int y) {
        int id = lvl[y/32][x/32];
        int tileType = game.getTileManager().getTile(id).getTileType();

        if(tileType == GRASS_TILE){
            return true;
        }
        return false;
    }

    public void shootEnemy(Tower t, Enemy e) {
        projectileManager.newProjectile(t,e);
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            selectedTower = null;
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if (y >= 640) {
            actionBar.mouseMoved(x, y);
        }else{
            mouseX = (x/32)*32;
            mouseY = (y/32)*32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if (y >= 640) {
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
            actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public void setSelectedTower(Tower selectedTower) {
            this.selectedTower = selectedTower;
    }


    public EnemyManager getEnemyManager(){
        return enemyManager;
    }


    public WaveManager getWaveManager() {
        return waveManager;
    }

    public void rewardPlayer(int enemyType){
        actionBar.addGold(Constants.Enemies.GetEnemyReward(enemyType));
    }

    private Tower getTowerAt(int x, int y) {

        return towerManager.getTowerAt(x,y);
    }

    public int getTileType(int x, int y) {
        int xCord = x / 32;
        int yCord = y / 32;

        if(xCord<0 || xCord >19)
            return 0;
        if(yCord<0 || yCord >19)
            return 0;

        int id = lvl[y/32][x/32];
        return game.getTileManager().getTile(id).getTileType();
    }
    public void setLevel(int[][] lvl){
        this.lvl = lvl;
    }


    public void removeOneLife() {
        actionBar.removeOneLife();
    }

    public void resetEverything() {

        actionBar.resetEverything();
        enemyManager.reset();
        towerManager.reset();
        projectileManager.reset();
        waveManager.reset();
        mouseX = 0;
        mouseY = 0;
        selectedTower = null;
        goldTick=0;
    }

    public PathPoint getEnd(){
        return end;
    }
}
