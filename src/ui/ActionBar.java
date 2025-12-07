package ui;

import helps.Constants;
import helps.LoadSave;
import objects.Tower;
import scenes.Playing;
import java.awt.*;
import java.text.DecimalFormat;

import static main.GameStates.*;

public class ActionBar extends Bar{
    private Playing playing;
    private MyButton bMenu;

    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;

    private DecimalFormat formatter;

    private int gold = 200;
    private boolean showTowerCost;
    private int towerCostType;
    private int lives=100;


    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x,y,width,height);
        this.playing = playing;
        formatter = new DecimalFormat("0.0");

        initButtons();
    }
    public void resetEverything() {
        lives=100;
        gold = 200;
        towerCostType=0;
        showTowerCost= false;
        selectedTower=null;
        displayedTower=null;
    }
    public void initButtons(){

        bMenu = new MyButton("Menu",2,642,100,30);
        towerButtons = new MyButton[3];
        int w=50;
        int h=50;
        int xStart=110;
        int yStart=650;
        int xOffset=(int)(w * 1.1f);

        for(int i=0;i <towerButtons.length;i++ ){
            towerButtons[i] = new MyButton("",xStart + xOffset * i,yStart,w,h,i);
        }

    }

    public void removeOneLife(){
        lives-=10;
        if(lives<=0){
            LoadSave.WriteToLog("OYUN BITTI. KAYBETTINIZ. (Kalan Can: 0)");
            SetGameState(GAME_OVER);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);

        for(MyButton b : towerButtons){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(b.x,b.y,b.width,b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()],b.x,b.y,b.width,b.height,null );
            drawButtonFeedBack(g,b);

        }

    }

    public void draw(Graphics g) {
        //Arka Plan
        g.setColor(new Color(80, 94, 7));
        g.fillRect(x, y, width, height);
        //buttons
        drawButtons(g);

        //Kuleler
        drawDisplayedTower(g);

        //Wave Bilgisi(zaman)
        drawWaveInfo(g);
        
        //altin bilgisi
        drawGoldAmount(g);

        //Kule maaliyetini gostermece
        if(showTowerCost){
            drawTowerCost(g);
        }
        //kalan cani gosterme
        g.setColor(Color.BLACK);
        g.drawString("Health: " + lives,110,750);

    }

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(280, 650, 150, 50);
        g.setColor(Color.BLACK);
        g.drawRect(280, 650, 150, 50);

        g.drawString(""+getTowerCostName() ,285,670);
        g.drawString("Cost: "+getTowerCostCost()+"g",285,695);

    }

    private int getTowerCostCost() {
        return Constants.Towers.GetTowerCost(towerCostType);
    }

    private String getTowerCostName() {
        return Constants.Towers.GetName(towerCostType);
    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: "+gold,110,725);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave: "+( current + 1) +" / " + size,425,770);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: "+remaining,425,790);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if(playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedText = formatter.format(timeLeft);
            g.drawString("Time Left: " + formattedText, 425, 750);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if(displayedTower != null){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.BLACK);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420,650,50,50,null);;
            g.setFont(new Font("Arial",Font.BOLD,15));
            g.drawString(""+ Constants.Towers.GetName(displayedTower.getTowerType()),500,660);
            g.drawString("ID: " + displayedTower.getId(),500,675);
            drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);
        }
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawOval(displayedTower.getX()+ 16 -(int) (displayedTower.getRange()*2)/2,displayedTower.getY() + 16 -(int) (displayedTower.getRange()*2)/2,(int) displayedTower.getRange() * 2,(int) displayedTower.getRange() * 2);
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            SetGameState(MENU);
        }else{
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){
                    if(!isGoldEnougForTower(b.getId())){//burada kuleyi olusturmak icin yeterli para var mi yok mu ona bakiyoruz
                        return;
                    }
                    selectedTower = new Tower(0,0,-1,b.getId());
                    playing.setSelectedTower(selectedTower);
                }
            }
        }
    }

    private boolean isGoldEnougForTower(int towerType) {
        return gold >= Constants.Towers.GetTowerCost(towerType);
    }


    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        for(MyButton b : towerButtons){
            b.setMouseOver(false);
        }

        if(bMenu.getBounds().contains(x,y)){
            bMenu.setMouseOver(true);
            showTowerCost = false;
        }else{
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){
                    b.setMouseOver(true);
                    showTowerCost=true;
                    towerCostType = b.getId();
                }
            }
        }
    }


    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            bMenu.setMousePressed(true);
        }else{
            for(MyButton b : towerButtons){
                if(b.getBounds().contains(x,y)){
                    b.setMousePressed(true);
                }
            }
        }
    }


    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for(MyButton b : towerButtons){
            b.resetBooleans();
        }
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    public int getGold(){
        return gold;
    }

    public void payForTower(int towerType) {
        this.gold -= Constants.Towers.GetTowerCost(towerType);
    }

    public void addGold(int getEnemyReward) {
        this.gold += getEnemyReward;
    }
    public int getLives() {
        return lives;
    }

}
