package scenes;

import helps.LoadSave;
import main.Game;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.*;
import java.awt.image.BufferedImage;

public class YouWon extends GameScene implements SceneMethods{
    private MyButton bReplay,bMenu;
    private BufferedImage winImage;
    public YouWon(Game game) {
        super(game);
        initButtons();
        loadImg();
    }
    private void loadImg() {
        winImage = LoadSave.getWinImage();
    }

    private void initButtons() {
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 150;
        int yOffset = 100;

        bMenu = new MyButton("Menu",x,y,w,h);
        bReplay = new MyButton("Replay",x,y + yOffset,w,h);
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(winImage, 0, 0, 640, 800, null);

//        //you won mesaji
//        g.setFont(new Font("Arial",Font.BOLD,50));
//        g.setColor(Color.GREEN);
//        g.drawString("YOU WON!",160,80);

        //dugmeler
        g.setFont(new Font("Arial",Font.BOLD,15));
        bMenu.draw(g);
        bReplay.draw(g);
    }


    private void replayGame() {
        LoadSave.CreateLogFile();
        LoadSave.WriteToLog("KAZANDINIZ.");
        //her seyi sifirla
        game.getPlaying().resetEverything();

        //oyun ekranina gideriz
        SetGameState(PLAYING);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            SetGameState(MENU);//menu ekranina gideriz
            game.getPlaying().resetEverything();
        }else if(bReplay.getBounds().contains(x,y)){
            replayGame();
        }
    }



    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);

        if(bMenu.getBounds().contains(x,y)){
            bMenu.setMouseOver(true);
        }else if(bReplay.getBounds().contains(x,y)){
            bReplay.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(bMenu.getBounds().contains(x,y)){
            bMenu.setMousePressed(true);
        }else if(bReplay.getBounds().contains(x,y)){
            bReplay.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
