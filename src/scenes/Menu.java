package scenes;

import helps.LoadSave;
import main.Game;
import main.GameStates;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{

    private MyButton bPlaying,bEdit,bQuit;
    private BufferedImage backgroundImg;

    public Menu(Game game) {
        super(game);
        loadBackground();
        initButtons();
    }

    private void loadBackground() {
        backgroundImg = LoadSave.getMenuImage();
    }

    private void initButtons() {//butonari tasarlama ve degerler verme(pixel olarak nerede duracagini belirleme)
        int w = 150;
        int h = w / 3;
        int x = 640 / 2 - w / 2;
        int y = 400;
        int yOffset = 70;

        bPlaying = new MyButton("Play",x,y,w,h);
        bEdit = new MyButton("Edit",x,y + yOffset,w,h);
        bQuit = new MyButton("Quit",x,y + yOffset*2,w,h);
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(backgroundImg, 0, 0, 640, 800, null);

        drawButtons(g);

    }

    @Override
    public void mouseClicked(int x, int y) {

        if(bPlaying.getBounds().contains(x,y)){
            SetGameState(PLAYING);
        }else if(bEdit.getBounds().contains(x,y)){
            SetGameState(EDIT);
        }else if(bQuit.getBounds().contains(x,y)){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bQuit.setMouseOver(false);
        bEdit.setMouseOver(false);

        if(bPlaying.getBounds().contains(x,y)){
            bPlaying.setMouseOver(true);
        }else if (bEdit.getBounds().contains(x,y)) {
            bEdit.setMouseOver(true);
        }else if(bQuit.getBounds().contains(x,y)){
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePressed(int x, int y) {

        if(bPlaying.getBounds().contains(x,y)){
            bPlaying.setMousePressed(true);
        }else if(bEdit.getBounds().contains(x,y)){
            bEdit.setMousePressed(true);
        }else if(bQuit.getBounds().contains(x,y)){
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        resetButtons();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void resetButtons() {
        bPlaying.resetBooleans();
        bQuit.resetBooleans();
        bEdit.resetBooleans();
    }


    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bQuit.draw(g);
        bEdit.draw(g);
    }


}
