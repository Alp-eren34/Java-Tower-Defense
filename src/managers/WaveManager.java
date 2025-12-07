package managers;

import events.Wave;
import helps.LoadSave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit ;
    private int enemyIndex,waveIndex;
    private int waveTickLimit = 60 * 15;
    private int waveTick= 0;
    private boolean  waveStartTimer,waveTickTimerOver;

    public WaveManager(Playing playing){
        this.playing = playing;
        createWaves();
    }

    public void update(){
        if(enemySpawnTick < enemySpawnTickLimit){
            enemySpawnTick++;
        }
        if(waveStartTimer){
            waveTick++;
            if(waveTick >= waveTickLimit){
                waveTickTimerOver = true;
            }
        }
    }

    public void increaseWaveIndex(){
        waveIndex++;
        waveTick=0;
        waveStartTimer=false;
        waveStartTimer = false;
    }

    public int getNextEnemy(){
        enemySpawnTick = 0;
        return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves() {
        //dusman id lerine gore wave hazirlama
        //waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,1,0,0,1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,0,0,1,1,0,1,0,2,2))));
    }

    public ArrayList<Wave> getWaves(){
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return waveIndex + 1 < waves.size();
    }

    public void startWaveTimer() {
        waveStartTimer = true;
    }

    public boolean isWaveTimerOver() {

        return waveTickTimerOver;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public float getTimeLeft(){
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft/60.0f;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }

    public void reset(){
        waves.clear();
        createWaves();
        enemyIndex=0;
        waveIndex=0;
        waveStartTimer=false;
        waveTickTimerOver=false;
    }
}
