package main;

import helps.LoadSave;
import managers.TileManager;
import scenes.*;

import javax.swing.JFrame;
public class Game extends JFrame implements Runnable{

    private GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET =120.0;
    private final double UPS_SET=60.0;



    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Editing editing;
    private GameOver gameOver;
    private YouWon youWon;

    private TileManager tileManager;
    public Game(){
        initClasses();
        createDefaultLevel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Prolab Odevi");
        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void createDefaultLevel() {
        int[] arr = new int[400];
        for(int i=0;i<arr.length;i++) {
            arr[i] = 0;
        }
        LoadSave.CreateLevel("new_level",arr);
    }



    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        editing = new Editing(this);
        gameOver = new GameOver(this);
        youWon = new YouWon(this);

    }



    private void start(){
        gameThread = new Thread(this){};

        gameThread.start();

    }

    private void updateGame(){
        switch (GameStates.gameState){
            case PLAYING -> {
                playing.update();
            }
            case MENU -> {
            }
            case EDIT -> {
                editing.update();
            }
        }
    }
    public static void main(String[] args) {

        Game game = new Game();
        game.gameScreen.initInputs();
        LoadSave.CreateLogFile();
        LoadSave.WriteToLog("Simülasyon Başladı.");
        game.start();

    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();

        int frames = 0;
        int updates =0;

        long now;

        while(true){

            now = System.nanoTime();
            //Render
            if(now-lastFrame >= timePerFrame){
                repaint();
                lastFrame =  now;
                frames++;
            }
            //Update
            if(now - lastUpdate > timePerUpdate){
                updateGame();
                lastUpdate =now;
                updates++;
            }
            if(System.currentTimeMillis() - lastTimeCheck >=1000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates =0;
                lastTimeCheck = System.currentTimeMillis();
            }

        }

    }
    //Getter and setters
    public Render getRender(){
        return render;
    }
    public Menu getMenu() {
        return menu;
    }


    public Playing getPlaying() {
        return playing;
    }


    public Editing getEditor() {
        return editing;
    }
    public TileManager getTileManager() {
        return tileManager;
    }

    public GameOver getGameOver() {
        return gameOver;
    }
    public YouWon getYouWon() {
        return youWon;
    }
}